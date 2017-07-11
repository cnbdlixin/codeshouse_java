package com.lx.codeshouse.util.network;

import com.lx.codeshouse.util.file.FileUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.*;
import org.apache.log4j.Logger;

import java.io.*;

/**
 * ftp �ļ��ϴ����ظ�������
 * 
 * @author bingjiema
 *
 */
public class FtpHelper {
	// logger
	private static Logger logger = Logger.getLogger(FtpHelper.class);
	
	// ftp client
	private FTPClient ftp;

	// buffer size, default 256KB
	private int bufferSize = 5 * 1024 * 1024;

	private OutputStream outputStream = null;
	
	// remote file path
	private String remotePath;

	// remote file name
	private String remoteName;
	
	private String localPath;
	
	// ftp server ip
	private String ip;
	
	// ftp server user
	private String user;
	
	// ftp server password
	private String password;
	
	public FtpHelper(String ip, String user, String password) {
		super();
		this.ip = ip;
		this.user = user;
		this.password = password;
	}
	
	public FtpHelper(String remotePath, String localPath,
			String ip, String user, String password) {
		super();
		this.remotePath = remotePath;
		this.localPath = localPath;
		this.ip = ip;
		this.user = user;
		this.password = password;
	}

	/**
	 * Զ��ɾ���ļ�
	 * 
	 * @throws Exception
	 */
	public void delete() throws Exception {
		this.getFTPClient();
		String remoteFile = FileUtil.getHttpPath(remotePath) + remoteName;
		ftp.deleteFile(remoteFile);
	}

	/**
	 * Զ��ɾ���ļ�
	 * 
	 * @throws Exception
	 */
	public void delete(String remoteFile) throws Exception {
		this.getFTPClient();
		ftp.deleteFile(remoteFile);
		this.close();
	}
	/**
	 *  �����ļ�������
	 * @param remoteFile �ļ���
	 * @throws Exception 
	 */
	public synchronized void receive(String remoteFile) throws Exception {
		 long start=0,end=0;
		 if(logger.isDebugEnabled()) start = System.currentTimeMillis();
		 
		 this.getFTPClient();
		 OutputStream output = new FileOutputStream(localPath+remoteFile);
         ftp.retrieveFile(remotePath+remoteFile, output);
         output.close();
 		 
 		 if(logger.isDebugEnabled()) { 
			end = System.currentTimeMillis();
		    logger.debug(remoteFile + "���غ�ʱ:" + (end-start));
		}
 		 
	}
	
	public synchronized String getModificationTime(String remoteFile) throws Exception {
		long start=0,end=0;
		if(logger.isDebugEnabled()) start = System.currentTimeMillis(); 
		this.getFTPClient();
		String modifyTime = ftp.getModificationTime(remotePath+remoteFile);
		if(logger.isDebugEnabled()) { 
			end = System.currentTimeMillis();
		    logger.debug(remoteFile+"��ȡ�޸�ʱ���ʱ:"+(end-start));
		}
		return modifyTime;
	}
	/**
	 *  �����ļ�������
	 * @param remoteFile �ļ���
	 * @throws Exception
	 */
	public synchronized void receive(String remoteFile,String localFile) throws Exception {
 		 this.getFTPClient();
	     OutputStream output;
         output = new FileOutputStream(localPath+localFile);
         ftp.retrieveFile(remotePath+remoteFile, output);
         output.close();
	}
	
	/**
	 * �ַ��ļ�
	 * 
	 * @param srcFile
	 *            String ԭ�ļ�
	 * @throws Exception
	 */
	public void send(String srcFile) throws Exception {
		InputStream inputStream = new FileInputStream(srcFile);
		this.send(inputStream);
	}
	
	/**
	 * �ַ��ļ�
	 * 
	 * @param inputStream
	 *            InputStream
	 * @throws Exception
	 */
	public void send(InputStream inputStream) throws Exception {
		long t1 = System.currentTimeMillis();

		ftp = this.getFTPClient();

		mkFtpDirs(ftp, remotePath);
		String remoteFile = remotePath + remoteName;
		boolean ok = ftp.storeFile(remoteFile, inputStream);
		if (!ok) {
			logger.warn("file transfer failed, " );
		}

		long t2 = System.currentTimeMillis();
		logger.info("file " + remoteFile + " transfered to "
				+ " ok, time: " + (t2 - t1));
	}
	

	/**
	 * �ر�����
	 * 
	 * @throws Exception
	 */
	public synchronized void close() throws Exception {
		this.closeFTPClient(ftp);
	}

	/**
	 * ����Զ�̴洢Ŀ¼
	 * 
	 * @param remotePath
	 *            String
	 */
	public void setRemotePath(String remotePath) {
		this.remotePath = remotePath;
	}

	/**
	 * ����Զ���ļ�����
	 * 
	 * @param remoteName
	 *            String
	 */
	public void setRemoteName(String remoteName) {
		this.remoteName = remoteName;
	}

	/**
	 * ����Ŀ¼
	 * 
	 * @param ftp
	 *            FTPClient
	 * @param destPath
	 *            String
	 * @throws Exception
	 */
	private void mkFtpDirs(FTPClient ftp, String destPath) throws Exception {
		String[] dirs = FileUtil.getDirs(destPath);
		StringBuilder dir = new StringBuilder();
		for (int i = 0; i < dirs.length; i++) {
			dir.append('/').append(dirs[i]);
			boolean ok = ftp.makeDirectory(dir.toString());
			if (logger.isDebugEnabled()) {
				logger.debug("dir " + dir.toString() + ": " + ok);
			}
		}
	}
	/**
	 * ȡ��ftp�ͻ���ʵ�� ע�⣺��ʱʹ�ã�֮��ʹ�����ӳع���
	 * 
	 * @return FTPClient
	 */
	public FTPClient getFTPClient() throws Exception {
		if (ftp == null) {
			ftp = this.createFtpClient();
		}
		return ftp;
	}

	/**
	 * ����Ftp client
	 * 
	 * @return FTPClient
	 */
	private synchronized FTPClient createFtpClient() throws Exception {
		FTPClient ftpClient = null;
		if (ftp == null) {
			ftpClient = new FTPClient();
			ftpClient.setBufferSize(bufferSize);
			
			ftpClient.addProtocolCommandListener(new PrintCommandListener(
					new PrintWriter(System.out)));

			int port = 21;
			
			try {
				int reply;

				ftpClient.connect(ip, port);
				logger.info("Connected to " + ip + ".");

				// After connection attempt, you should check the reply code to
				// verify success.
				reply = ftpClient.getReplyCode();
				if (!FTPReply.isPositiveCompletion(reply)) {
					ftpClient.disconnect();
					logger.warn("FTP server " + ip
							+ " refused connection.");
					throw new Exception("FTP server " + ip
							+ " refused connection.");
				}
				boolean ok = ftpClient.login(user, password);
				if (!ok) {
					ftpClient.logout();
					throw new Exception("login failed. server: " + ip);
				}
				logger.info("logged in to " + ip + " ok.");
				//ftpClient.setDataTimeout(5000);
				ftpClient.setConnectTimeout(5000);

				// set file type
				ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
				// enter passive mode
				// ftpClient.enterLocalPassiveMode();
				//��������ģʽ�����ÿͻ��˵Ķ˿�
				ftpClient.enterLocalActiveMode();
				
				

			} catch (IOException ex) {
				if (ftpClient.isConnected()) {
					try {
						ftpClient.disconnect();
					} catch (IOException f) {
						// do nothing
					}
				}
				logger.warn("Could not connect to server " + ip
						+ " , " + ex);
				throw ex;
			}
		}
		return ftpClient;
	}

	/**
	 * �ر�ftp�ͻ���
	 * 
	 * @param ftpClient
	 *            FTPClient
	 * @throws Exception
	 */
	private void closeFTPClient(FTPClient ftpClient) throws Exception {
		if (ftpClient != null) {
			try {
				if (outputStream != null) {
					outputStream.close();
					if (!ftpClient.completePendingCommand()) {
						ftpClient.logout();
					}
				} else {
					ftpClient.logout();
				}
			} finally {
				if (ftpClient.isConnected()) {
					ftpClient.disconnect();
				}
			}
		}
	}
	
	public static void main(String args[]) {
		/*FtpDemo ftpDemo = new FtpDemo();
		try {
			//ftpDemo.receive(ftpDemo.remoteName);
			ftpDemo.send(ftpDemo.localPath+ftpDemo.remoteName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		//long t1=0,t2=0,t3=0,t4=0;
		
		 /*FtpHelper ftpHelper1 = new FtpHelper(AppConfig.getProperty("ftp.remotePath"),AppConfig.getProperty("ftp.localPath"),
					AppConfig.getProperty("ftp.server"),AppConfig.getProperty("ftp.user"),AppConfig.getProperty("ftp.password"));
		 FtpHelper ftpHelper2 = new FtpHelper(AppConfig.getProperty("ftp.remotePath"),AppConfig.getProperty("ftp.localPath"),
					AppConfig.getProperty("ftp.server"),AppConfig.getProperty("ftp.user"),AppConfig.getProperty("ftp.password"));*/
		 /*for(int i=0;i<100;i++) {
			 t1 = System.currentTimeMillis();
			 ftpHelper1.receive("rt_nasdaq.txt");
			 t2 = System.currentTimeMillis();
			 ftpHelper2.receive("rt_amex.txt");
			 t3 = System.currentTimeMillis();
			 logger.info("ftpHelper1 "+(t2-t1)+",ftpHelper2 "+(t3-t2));
		 }*/
	
		
	}

	public void setLocalPath(String localPath) {
		this.localPath = localPath;
	}
	
	
	
	
}
