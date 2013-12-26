/*package cn.xiaoqiang.utils;

import javax.servlet.http.HttpServletRequest;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;

*//**
 * <p>Copyright 2012 by ShenZhen JinZheng Software corporation,
 * <p>All rights reserved.
 * <p>版权所有：深圳市金政软件技术有限公司
 * <p>未经本公司许可，不得以任何方式复制或使用本程序任何部分，
 * <p>侵权者将受到法律追究。
 * <p>DERIVED FROM: NONE
 * <p>PURPOSE:		获取IP地址
 * <p>AUTHOR:   	hyq
 * <p>DATE:         2012-11-23
 * <p>HISTORY:      1.0
*//*
public class GetIPAddress {
	

	*//**
	 * 获取当前操作系统名称. return 操作系统名称 例如:windows,Linux,Unix等.
	 *//*
	public String getOSName() {
		return System.getProperty("os.name").toLowerCase();
	}
	
	*//**
	 * 获取widnows网卡的ip地址.
	 * @return ip地址
	 *//*
	public  String getWindowsIPAddress() {
		String ip = null;
		BufferedReader bufferedReader = null;
		Process process = null;
		try {
			
			// windows下的命令，显示信息中包含有ip地址信息
			process = Runtime.getRuntime().exec("ipconfig /all");
			bufferedReader = new BufferedReader(new InputStreamReader(process
					.getInputStream()));
			String line = null;
			int index = -1;
			while ((line = bufferedReader.readLine()) != null) {
				
				// 寻找标示字符串[ip address]
				index = line.toLowerCase().indexOf("ip address");
				if (index != -1) {
					index = line.indexOf(":");
					if (index != -1) {
						//取出ip地址并去除2边空格

						ip = line.substring(index + 1).trim();
					}
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bufferedReader != null) {
					bufferedReader.close();
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			bufferedReader = null;
			process = null;
		}

		return ip;
	}
	
	*//**
	 * 获取Linux网卡的ip地址
	 * @return ip地址
	 *//*
	public String getLinuxIPAddress() {
		String ip = null;
		BufferedReader bufferedReader = null;
		Process process = null;
		try {
			// linux下的命令，一般取eth0作为本地主网卡 显示信息中包含有ip地址信息
			
			process = Runtime.getRuntime().exec("ifconfig eth0");
			bufferedReader = new BufferedReader(new InputStreamReader(process
					.getInputStream()));
			String line = null;
			int index = -1;
			while ((line = bufferedReader.readLine()) != null) {
				index = line.toLowerCase().indexOf("IPADDR");
				// 找到了
				
				if (index != -1) {
					// 取出mac地址并去除2边空格
					 
					ip = line.substring(index + 4).trim();
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bufferedReader != null) {
					bufferedReader.close();
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			bufferedReader = null;
			process = null;
		}

		return ip;
	}
	
	
	*//**
	 * 获取IP地址
	 *//*
	public String getIPAddress(){
		String os = getOSName();
		String ip = null;
		if (os.startsWith("windows")) {
			ip = getWindowsIPAddress();
		} else if (os.startsWith("linux")) {
			ip = getLinuxIPAddress();
		} else {
		}
		return ip;
	}
	
	
	*//**
	 * 获取客户端的真实IP地址
	 *//*
	public static String getRealIP(HttpServletRequest request){
		 String ip = request.getHeader("x-forwarded-for");
	       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	           ip = request.getHeader("Proxy-Client-IP");
	       }
	       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	           ip = request.getHeader("WL-Proxy-Client-IP");
	       }
	       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	           ip = request.getRemoteAddr();
	       }
	       return ip;

	}
	
	
}
*/