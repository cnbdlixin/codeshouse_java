package com.lx.codeshouse.util.file;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

//TODO �ع�FileUtil
public class FileUtil {

	/**
	 * ���ַ���д���ļ�
	 * 
	 * @param filename
	 * @param data
	 */
	public static void writeStringToFile(String filename, String data,
			String coding) {
		try {
			FileUtils.writeStringToFile(new File(filename), data, coding);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void writeStringToFile(String filename,String data) {
		writeStringToFile(filename, data,"GBK");
	}
	

	public static void writeStringToFile(File file, String data, String coding) {
		try {
			FileUtils.writeStringToFile(file, data, coding);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/** ��Դ�ļ����Ƶ�Ŀ���ļ��� */
	public static boolean copyFile(String source, String aim) {
		boolean isOk = false;
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		File sourceFile,aimFile;
		try {
			sourceFile = new File(source);
			aimFile = new File(aim);
			if (sourceFile.compareTo(aimFile) == 0) {
				isOk = true;
			} else {
				bis = new BufferedInputStream(new FileInputStream(sourceFile));
				bos = new BufferedOutputStream(new FileOutputStream(aimFile));
				int c;
				while ((c = bis.read()) != -1) {
					bos.write(c);
				}
				isOk = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (bis!=null) bis.close();
				bis = null;
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (bos!=null) bos.close();
				bos = null;
			} catch (Exception e) {
				e.printStackTrace();
			}
			sourceFile=null;
			aimFile=null;
		}
		return isOk;
	}
	
	/**
	 * ��ȡ�ļ�������
	 * 
	 * @param filename
	 * @param coding
	 * @return
	 */
	public static String getFileContent(File filename, String coding) {
		if (!filename.exists()) {
			try {
				filename.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			return FileUtils.readFileToString(filename, coding);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * ��ȡ�ļ�������
	 * 
	 * @param filename
	 * @param coding
	 * @return
	 */
	public static String read(String filename) {
		File file = new File(filename);
		/*if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}*/
		if(!file.exists()) return "";
		try {
			return FileUtils.readFileToString(file, "GBK");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	
	
	/**
	 * ���ļ��е�����һ���ж�������List�� fullFileName //��·�����ļ���
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static List<String> readFileLine(String fullFileName)
			throws FileNotFoundException, IOException {
		BufferedReader infile = null;
		List<String> recordList = new ArrayList<String>();
		try {
			infile = new BufferedReader(new FileReader(fullFileName));
			String item;
			while ((item = infile.readLine()) != null) {
				if (!item.trim().equalsIgnoreCase("")) {
					recordList.add(item);
				}

			}
		} finally {
			if (infile != null) {
				infile.close();
			}
		}
		return recordList;
	}

	public static List<String> readFileLine(File file)
			throws FileNotFoundException, IOException {
		BufferedReader infile = null;
		List<String> recordList = new ArrayList<String>();
		try {
			infile = new BufferedReader(new FileReader(file));
			String item;
			while ((item = infile.readLine()) != null) {
				if (!item.trim().equalsIgnoreCase("")) {
					recordList.add(item);
				}

			}
		} finally {
			if (infile != null) {
				infile.close();
			}
		}
		return recordList;
	}

	public static List<String> readInputStreamLine(InputStream stream)
			throws FileNotFoundException, IOException {
		BufferedReader infile = null;
		List<String> recordList = new ArrayList<String>();
		try {
			infile = new BufferedReader(new InputStreamReader(stream));
			String item;
			while ((item = infile.readLine()) != null) {
				if (!item.trim().equalsIgnoreCase("")) {
					recordList.add(item);
				}

			}
		} finally {
			if (infile != null) {
				infile.close();
			}
			if (stream != null) {
				stream.close();
			}
		}
		return recordList;
	}

	public static void writeLine(File file, int linenum, String line) {
		try {
			RandomAccessFile raf = new RandomAccessFile(file, "rw");
			raf.seek(0);
			raf.writeBytes(line + "\n");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void appendTextToFile(File file,String content,String code)
	{
		if(null == file)
		{
			return;
		}
		RandomAccessFile raf = null;
		try {
			if(!file.exists())
			{
				file.createNewFile();
			}
			raf = new RandomAccessFile(file, "rw");
			raf.seek(raf.length());
			raf.write(content.getBytes(code));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			try {
				raf.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	  /**
	   * ת��·��Ϊhttp·����·���е�"\"�ַ�����ת��Ϊ"/"�ַ�, ·������ַ�Ϊ"/",
	   * �磺����"\abc"��ת����ʽΪ��"/abc/"
	   * @param path String
	   * @return String
	   */
	  public static String getHttpPath(String path) {
	    if (path != null) {
	      path = path.replace('\\', '/');
	      if (!path.endsWith("/")) {
	        path = path + "/";
	      }
	    }
	    return path;
	  }

	  /**
	   * ��ȡ�ļ�������
	   * @param dir
	   * @return
	   */
	  public static String[] getDirs(String dir) {
		    int start = 0, end = dir.length();
		    if (dir.startsWith("/")) {
		      start = 1;
		    }
		    if (dir.endsWith("/")) {
		      end = end - 1;
		    }
		    if (start != 0 || end != dir.length()) {
		      dir = dir.substring(start, end);
		    }
		    String[] dirs= dir.split("/");
		    return dirs;
	 }

	public static void main(String[] args) {
		 File file = new File("testdir/aa.txt");
 
		 FileUtil.appendTextToFile(file,"�л����񹲺͹�\r\n","GB2312");
		 FileUtil.appendTextToFile(file,"�л����񹲺͹�\r\n","GB2312");
		 FileUtil.appendTextToFile(file,"�л����񹲺͹�\r\n","GB2312");
		 
		// System.out.println(data.length());
		//		
		//		
		// long start = System.nanoTime();
		// FileUtil.writeStringToFile("testfile/aa.txt", data, "GB2312");
		// System.out.println(System.nanoTime() - start);
		//
		// long start1 = System.nanoTime();
		// FileUtil.writeStringToFile1("testfile/bb.txt", data, "GB2312");
		// System.out.println(System.nanoTime() - start1);

		// long start1 = System.nanoTime();
		// StringBuffer sb1 = new StringBuffer(400);
		//		
		// for(int i=0;i<50;i++)
		// {
		// sb1.append("�л����񹲺͹�");
		// }
		// System.out.println(System.nanoTime() - start1);
		//		
		//		
		// long start = System.nanoTime();
		// StringBuffer sb = new StringBuffer();
		//		
		// for(int i=0;i<50;i++)
		// {
		// sb.append("�л����񹲺͹�");
		// }
		// System.out.println(System.nanoTime() - start);

	}
}
