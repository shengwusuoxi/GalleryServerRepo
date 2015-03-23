package com.cangqu.gallery.server.base.utils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * 管理配置文件的类 
 * 主要用于从配置文件获得配置信息
 * @author tlj
 *
 */
public class PropertyMgr {

	private static Properties props = new Properties();
	
	static {
		try {
			File file = new File(System.getProperty("user.dir") + File.separator + "config.properties");
			if(file.exists()){
				props.load(new FileReader(file));
			}else{
				props.load(PropertyMgr.class.getClassLoader().getResourceAsStream("config.properties"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 默认构造函数设为私有 以防止该类被实例化
	 */
	
	private PropertyMgr(){}
	
	/**
	 * 从配置文件中获取某个属性值
	 * @param key 要获得的属性的对应键
	 * @return 返回的属性值（String类型）
	 */
	public static String getProperty(String key)
	{
		return props.getProperty(key);
	}
	
	/**
	 * 从配置文件中获取某个属性值
	 * @param key 要获得的属性的对应键
	 * @return 返回的属性值（String类型）
	 */
	public static int getPropertyInt(String key)
	{
		return Integer.parseInt(props.getProperty(key));
	}
		
	/**
	 * 从配置文件中获取某个属性值
	 * @param key 要获得的属性的对应键
	 * @return 返回的属性值（String类型）
	 */
	public static boolean getPropertyBoolean(String key)
	{
		return Boolean.parseBoolean(props.getProperty(key));
	}
	
	public static void main(String[] agrs){
		System.out.println(PropertyMgr.getPropertyBoolean("debugFlag"));
	}
}
