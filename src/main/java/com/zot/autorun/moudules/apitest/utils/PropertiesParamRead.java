package com.zot.autorun.moudules.apitest.utils;

import java.io.InputStream;
import java.util.Properties;

public class PropertiesParamRead {
	/**
	 * 读取properties 配置文件
	 */
	private static String sg_token;
	private static String sg_client_secret;
	private static String sg_client_id;
	private static String openapi_url;
	private static String sg_cn_url;
	private static String sg_us_url;
	private static String sg_eu_url;
	private static String sg_au_url;
	
		
	public static String getSg_client_id() {
		return sg_client_id;
	}

	public static void setSg_client_id(String sg_client_id) {
		PropertiesParamRead.sg_client_id = sg_client_id;
	}

	public static String getSg_client_secret() {
		return sg_client_secret;
	}

	public static void setSg_client_secret(String sg_client_secret) {
		PropertiesParamRead.sg_client_secret = sg_client_secret;
	}

	public static String getSg_token() {
		return sg_token;
	}

	public static void setSg_token(String sg_token) {
		PropertiesParamRead.sg_token = sg_token;
	}

	public static String getSg_cn_url() {
		return sg_cn_url;
	}

	public static void setSg_cn_url(String sg_cn_url) {
		PropertiesParamRead.sg_cn_url = sg_cn_url;
	}

	public static String getSg_us_url() {
		return sg_us_url;
	}

	public static void setSg_us_url(String sg_us_url) {
		PropertiesParamRead.sg_us_url = sg_us_url;
	}

	public static String getSg_eu_url() {
		return sg_eu_url;
	}

	public static void setSg_eu_url(String sg_eu_url) {
		PropertiesParamRead.sg_eu_url = sg_eu_url;
	}

	
	
	public static String getSg_au_url() {
		return sg_au_url;
	}

	public static void setSg_au_url(String sg_au_url) {
		PropertiesParamRead.sg_au_url = sg_au_url;
	}


	public static String getOpenapi_url() {
		return openapi_url;
	}

	public static void setOpenapi_url(String openapi_url) {
		PropertiesParamRead.openapi_url = openapi_url;
	}
	

	// 读取测试环境数据
	 static  {
		Properties prop = new Properties();
			
		InputStream is = PropertiesParamRead.class.getClassLoader().getResourceAsStream("resources/environment.properties");
		try {
			prop.load(is);
			//is.close();
			
            sg_token = prop.getProperty("sg.token");
            sg_client_secret = prop.getProperty("sg.client_secret");
            sg_client_id = prop.getProperty("sg.client_id");
            openapi_url = prop.getProperty("openapi.url");
            sg_cn_url = prop.getProperty("sg.cn.url");
            sg_eu_url = prop.getProperty("sg.eu.url");
            sg_us_url = prop.getProperty("sg.us.url");
            sg_au_url = prop.getProperty("sg.au.url");
            
           // System.out.println("获取配置文件的数据为："+"ip="+ip +"openpai_port="+openapi_port +"sg_port" + sg_port);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				is.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	 
//	 public static void main(String[] args) {
//		 
//		 System.out.println("sg_token---->"+PropertiesParamRead.getSg_token());
//		 System.out.println("sg_client_secret---->"+PropertiesParamRead.getSg_client_secret());
//		 System.out.println("sg_client_id---->"+PropertiesParamRead.getSg_client_id());
//		 System.out.println("openapi_url---->"+PropertiesParamRead.getOpenapi_url());
//		 System.out.println("sg_cn_url---->"+PropertiesParamRead.getSg_cn_url());
//		 System.out.println("sg_eu_url---->"+PropertiesParamRead.getSg_eu_url());
//		 System.out.println("sg_us_url---->"+PropertiesParamRead.getSg_us_url());
//		 System.out.println("sg_au_url---->"+PropertiesParamRead.getSg_au_url());
//		 
//	 }
}
