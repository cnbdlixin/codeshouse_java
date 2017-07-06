package com.lx.codeshouse.util.network;

import com.common.utils.file.FileUtil;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;


public class TcpClient {

	private static Logger log = Logger.getLogger(HtmlPageUtil.class);

	private static TcpClient instance = null;

	Socket socket = null;

	public static TcpClient getInstance() {
		if (null == instance) {
			instance = new TcpClient();
		}
		return instance;
	}

	public String readFromServer(String host, int port) {
		StringBuffer result = new StringBuffer();
		Socket s = null;
		BufferedReader br = null;
		try {
			s = new Socket(host, port);
			DataOutputStream out = new DataOutputStream(s.getOutputStream());
			out.writeBytes("^");
			DataInputStream in = new DataInputStream(s.getInputStream());
			br = new BufferedReader(new InputStreamReader(in,"GB2312"));
			String line = null;
			while ((line = br.readLine()) != null) {
				if (line.equalsIgnoreCase("^"))
					break;
				result.append(line + "\r\n");
			}
		} catch (UnknownHostException e) {
			if (log.isInfoEnabled()) {
				log.error("����ʶ������[" + host + "]," + e.getMessage());
			}
			return null;
		} catch (EOFException e) {
			if (log.isInfoEnabled()) {
				log.error("EOF:" + e.getMessage());
			}
			return null;
		} catch (IOException e) {
			if (log.isInfoEnabled()) {
				log.error("�������[" + host + "]���ӣ�io�쳣��");
			}
			return null;
		} finally {
			if (null != s)
				try {
					s.close();
				} catch (IOException e) {
					if (log.isInfoEnabled()) {
						log.error("�������[" + host + "]���ӣ�socket���ܹرգ�");
					}
					return "";
				}
		}
		return result.toString();
	}

	public String readQuote() {
		StringBuffer result = new StringBuffer();
		BufferedReader br = null;
		try {
			DataOutputStream out = new DataOutputStream(socket
					.getOutputStream());
			out.writeUTF("^");
			DataInputStream in = new DataInputStream(socket.getInputStream());
			br = new BufferedReader(new InputStreamReader(in));
			String line = null;
			while ((line = br.readLine()) != null) {
				if (line.equalsIgnoreCase("^"))
					break;
				result.append(line + "\r\n");
			}
		} catch (UnknownHostException e) {
			return null;
		} catch (EOFException e) {
			return null;
		} catch (IOException e) {
			return null;
		}
		return result.toString();
	}

	public static void main(String[] args) {
//		String data = new TcpClient().readFromServer("10.1.99.87 ", 20);
//		System.out.println(data);
		
//		StringBuffer s = new StringBuffer();
//		Calendar c = Calendar.getInstance();
//		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
//		long start = System.currentTimeMillis();
//		
////		while(true)
////		{
			String data = new TcpClient().readFromServer("10.1.55.241", 27);
			String[] datas = data.split("\r\n\r\n");


			String[] lines = datas[0].split("\r\n");
			int length = lines.length;
			String tableprop = lines[2];
			String[] props = tableprop.split("\\|");
			String ymd = props[5].trim(); // ������ YYYYMMDD
			String hms = props[1].trim(); // ʱ���� HHmmSS
			if (hms.length() != 6) {
				hms = "0" + hms;
			}

			String date = (ymd + hms).trim(); // ��ǰʱ�� ��ʽΪ��yyyyMMddHHmmss
			
			FileUtil.writeStringToFile(new File("test11/" + date + ".txt"), data,"GB2312");
			
			try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			
//			String[] szlines = datas[1].split("\r\n");
//
//			for (int i = 3; i < szlines.length; i++) {
//				if (null == szlines[i] || szlines[i].equalsIgnoreCase("")) {
//					break;
//				}
//				String[] rows = szlines[i].split("\\|");
//				String code = rows[0];
//
//				if (code.substring(0, 2).equalsIgnoreCase("00") ) {
//					System.out.println("cn_" + rows[0] + ";" + rows[1]);
//				}
//			}
//			
//			
////		}
//		
//		
//
////		String[] shlines = lines[0].split("\r\n");
////
////		for (int i = 3; i < shlines.length; i++) {
////			if (null == shlines[i] || shlines[i].equalsIgnoreCase("")) {
////				break;
////			}
////			String[] rows = shlines[i].split("\\|");
////			String code = rows[0];
////
////			if (code.startsWith("510")) {
////				System.out.println("cn_" + rows[0]);
////			}
////		}
		
//		try {
//			Socket s = new Socket("10.1.99.87",20);
//			
//			DataInputStream in = new DataInputStream(s.getInputStream());
//			BufferedReader br = new BufferedReader(new InputStreamReader(in));
//			String line = null;
//			while ((line = br.readLine()) != null) {
//				System.out.println("##############" + line);
//			}
//		} catch (UnknownHostException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		
	}
}
