package com.lx.codeshouse.util.data;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

public class DomUtil {

	public static Document createDoc(String content,String code)
	{
		if(content.equalsIgnoreCase(""))
		{
			return null;
		}
		InputStream input;
		try {
			input = new ByteArrayInputStream(content.getBytes(code));
			DocumentBuilderFactory domfac=DocumentBuilderFactory.newInstance();
			DocumentBuilder dombuilder;
			dombuilder = domfac.newDocumentBuilder();
			Document doc=dombuilder.parse(input);
			return doc;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
