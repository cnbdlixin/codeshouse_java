package com.lx.codeshouse.util.network;

import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.*;


public class HttpClientUtil {
	private static Logger logger = Logger.getLogger(HttpClientUtil.class);
	public static String getWebContent(String url) {
		return getWebContent(url,"GB2312");
	}
	
	public static String getWebContent(String url,String code) {
		HttpClient client = new HttpClient();
		GetMethod method = new GetMethod(url);

		method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler(3, false));
		
		method.getParams().setParameter(HttpMethodParams.HTTP_URI_CHARSET,"gbk");
		
		method.getParams().setParameter(HttpMethodParams.SO_TIMEOUT,20000);
		method
				.getParams()
				.setParameter(
						HttpMethodParams.USER_AGENT,
						"Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; .NET CLR 2.0.50727; InfoPath.1; .NET CLR 3.0.04506.30; .NET CLR 3.0.04506.648)");
		try {
			client.executeMethod(method);
			byte[] responseBody = method.getResponseBody();
			return new String(responseBody,code);
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	}

	public static void main(String[] args) {
			System.out.println(HttpClientUtil
					.getWebContent("http://wwer.sdf.com/ph/werw.html"));
	}
	
	/**
	 * �������ӵĳ�ʱʱ��,����Ϊ2��,��ֹ�Է��ķ���������
	 */
	private static final int CONNECTION_TIMEOUT = 4000;

	/**
	 * ��ȡ���ݳ�ʱ��ʱ��,����Ϊ2��
	 */
	private static final int SO_TIMEOUT = 2000;

	public static final String HEADER_User_Agent = "User-Agent";

	private static final Map HEADER_MAP;
	static {
		Map _header_map = new IdentityHashMap();
		_header_map.put(HEADER_User_Agent, HEADER_User_Agent);
		HEADER_MAP = Collections.unmodifiableMap(_header_map);
	}

	public static String wgetString(String weburl, Map params) {
		HttpClientParams clientParams = new HttpClientParams();
		clientParams.setSoTimeout(SO_TIMEOUT);

		HttpClient client = new HttpClient(clientParams);
		HttpConnectionManager cman = client.getHttpConnectionManager();
		cman.getParams().setConnectionTimeout(CONNECTION_TIMEOUT);

		PostMethod method = new PostMethod(weburl);

		String ret = null;
		int status = -1;
		try {
			DefaultMethodRetryHandler retryHandler = new DefaultMethodRetryHandler();
			retryHandler.setRetryCount(1);
			method.setMethodRetryHandler(retryHandler);

			Iterator itr = params.keySet().iterator();
			List nvpsList = new ArrayList(params.size());

			int c = 0;
			while (itr.hasNext()) {
				String name = (String) itr.next();
				String value = (String) params.get(name);
				setRequest(method, nvpsList, name, value);
			}
			NameValuePair[] nvps = new NameValuePair[nvpsList.size()];
			nvpsList.toArray(nvps);
			method.setRequestBody(nvps);
			status = client.executeMethod(method);
			if (status != HttpStatus.SC_OK) {
				logger.warn("wgetString not expected -- url=" + weburl + " status=" + status);
				return null;
			}
			return method.getResponseBodyAsString();
		} catch (Exception e) {
			logger.warn("wgetString not expected -- url=" + weburl + " status=" + status,e);
			return null;
		} finally {
			method.releaseConnection();
		}
	}
	
	private static void setRequest(PostMethod method, List nvpsList, String name, String value) {
		if (HEADER_MAP.containsKey(name)) {
			//���name��һ��Header,���ڴ�����Header
			if(logger.isDebugEnabled()){
				logger.debug("Add header:"+name+" value:"+value);
			}
			method.addRequestHeader(name, value);
		} else {
            NameValuePair nvp = new NameValuePair(name, value);
			nvpsList.add(nvp);
		}
	}

	public static void main(String[] args) {
		System.out.println();
	}
}
