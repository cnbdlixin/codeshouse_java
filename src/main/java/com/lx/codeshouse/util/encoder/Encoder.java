package com.lx.codeshouse.util.encoder;

import java.io.UnsupportedEncodingException;

/**
 * ���빤��

 * @author bingjiema
 * 
 */

public class Encoder {

	private final static char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',  'e', 'f'}; 

	public Encoder() {
    }


    /**
     * ���ַ��������GBKתΪISO8859
     *
     * @param s - Ҫת�����ַ���
     * @return - ת������ַ���
     */
    public static String gbk2iso( String s )
    {
        try
        {
            return new String( s.getBytes( "GBK" ), "ISO-8859-1" );
        }
        catch (UnsupportedEncodingException e)
        {
            return s;
        }
        catch (NullPointerException e)
        {
            return "";
        }
    }
    
    /**
     * ���ַ��������UTF-8תΪGBK
     *
     * @param s - Ҫת�����ַ���
     * @return - ת������ַ���
     */
    public static String utf82gbk( String s )
    {
        try
        {
            return new String( s.getBytes( "UTF-8" ), "GBK" );
        }
        catch (UnsupportedEncodingException e)
        {
            return s;
        }
        catch (NullPointerException e)
        {
            return "";
        }
    }
    

    /**
     * ���ַ��������GBKתΪUTF-8
     *
     * @param s - Ҫת�����ַ���
     * @return - ת������ַ���
     */
    public static String gbk2utf8( String s )
    {
        try
        {
            return new String( s.getBytes( "GBK" ), "UTF-8" );
        }
        catch (UnsupportedEncodingException e)
        {
            return s;
        }
        catch (NullPointerException e)
        {
            return "";
        }
    }

    /**
     * ���ַ��������ISO8859תΪGBK
     *
     * @param s - Ҫת�����ַ���
     * @return - ת������ַ���
     */
    public static String iso2gbk( String s )
    {
        try
        {
            return new String( s.getBytes( "ISO-8859-1" ), "GBK" );
        }
        catch (UnsupportedEncodingException e)
        {
            return s;
        }
        catch (NullPointerException e)
        {
            return "";
        }
    }
    
    /**
     * ���ַ��������ISO8859תΪUTF-8
     *
     * @param s - Ҫת�����ַ���
     * @return - ת������ַ���
     */
    public static String iso2utf8( String s )
    {
        try
        {
            return new String( s.getBytes( "ISO-8859-1" ), "UTF-8" );
        }
        catch (UnsupportedEncodingException e)
        {
            return s;
        }
        catch (NullPointerException e)
        {
            return "";
        }
    }
    
    public static String md5( String s )
    {
    	byte[] source = s.getBytes();
    	try
    	{
    		java.security.MessageDigest md = java.security.MessageDigest.getInstance( "MD5" );
    	    md.update( source );
    	    byte tmp[] = md.digest();          	// MD5 �ļ�������һ�� 128 λ�ĳ�������
    	                                       	// ���ֽڱ�ʾ���� 16 ���ֽ�
    	    char str[] = new char[16 * 2];   	// ÿ���ֽ��� 16 ���Ʊ�ʾ�Ļ���ʹ�������ַ���
    	    									// ���Ա�ʾ�� 16 ������Ҫ 32 ���ַ�
    	    int k = 0;                          // ��ʾת������ж�Ӧ���ַ�λ��
    	    for (int i = 0; i < 16; i++) {      // �ӵ�һ���ֽڿ�ʼ���� MD5 ��ÿһ���ֽ�
    	                                        // ת���� 16 �����ַ���ת��
    	     byte byte0 = tmp[i];               // ȡ�� i ���ֽ�
    	     str[k++] = hexDigits[byte0 >>> 4 & 0xf];  // ȡ�ֽ��и� 4 λ������ת��, 
    	                                                             // >>> Ϊ�߼����ƣ�������λһ������
    	     str[k++] = hexDigits[byte0 & 0xf];            // ȡ�ֽ��е� 4 λ������ת��
    	    } 
    	    s = new String(str);                                 // ����Ľ��ת��Ϊ�ַ���
    	    return s;
    	}
    	catch( Exception e )
    	{
    		e.printStackTrace();
    	}
    	return null;
    }
}
