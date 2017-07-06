package com.lx.codeshouse.util.network;

import com.common.utils.file.FileUtil;
import org.apache.log4j.Logger;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

public class HtmlPageUtil {

	private static Logger log = Logger.getLogger(HtmlPageUtil.class);
	/**
	 * Read the url and return the content as a string.
	 * 
	 * @param url
	 *            Target url.
	 * @return The url Page content string.
	 */
	public static String getHTMLPage(String url) {
		StringBuffer sb = new StringBuffer();
		URL HttpUrl;
		URLConnection con;
		InputStreamReader inReader;
		BufferedReader reader;
		try {
			HttpUrl = new URL(url);
			con = HttpUrl.openConnection();
			inReader = new InputStreamReader(con.getInputStream());
			reader = new BufferedReader(inReader);

			String line = null;
			while ((line = reader.readLine()) != null) {
				if (line.equals(""))
					continue;
				sb.append(line);
				sb.append("\n");
			}
			reader.close();
			inReader.close();
			log.debug("get " + url + " ok!");
		} catch (Exception e) {
			log.error("fail to get " + url + " !");
			e.printStackTrace();

		}
		return sb.toString();
	}
	
	public static String getHTMLPages(String url) {
		StringBuffer sb = new StringBuffer();
		URL HttpUrl;
		URLConnection con;
		InputStreamReader inReader;
		BufferedReader reader;
		try {
			HttpUrl = new URL(url);
			con = HttpUrl.openConnection();
			inReader = new InputStreamReader(con.getInputStream(),"GB2312");
			reader = new BufferedReader(inReader);

			String line = null;
			while ((line = reader.readLine()) != null) {
				if (line.equals(""))
					continue;
				sb.append(line);
				sb.append("\n");
			}
			reader.close();
			inReader.close();
			//log.debug("get " + url + " ok!");
		} catch (Exception e) {
			log.error("fail to get " + url + " !");
			e.printStackTrace();

		}
		return sb.toString();
	}

	public static List<String> getHTMLpageStrList(String url) {
		URLConnection con;
		List<String> reList = null;
		try {
			URL HttpUrl = new URL(url);
			con = HttpUrl.openConnection();
			con.setUseCaches(false);
			reList = FileUtil.readInputStreamLine(new BufferedInputStream(con.getInputStream()));
		} catch (Exception e) {
			log.error("fail to get "+url+" !");
			e.printStackTrace();

		}
		return reList;
	}
	
	/**
	 * zhaoyg ����
	 * @param args
	 */
	public static void main(String[] args) {
		/*List<String> strs = HtmlPageUtil.getHTMLpageStrList("http://220.112.41.13/Sohu/StockQuote.aspx");
		for(String str : strs)
		{
			System.out.println(str);
		}*/
		
		String strs = HtmlPageUtil.getHTMLPage("http://hqk.stock.sohu.com/hk/001/hk_00001-2.html");
		System.out.println("aaa:"+strs);
	}
}
