/*package cn.xiaoqiang.utils;

import javax.servlet.http.HttpServletRequest;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;

*//**
 * <p>Copyright 2012 by ShenZhen JinZheng Software corporation,
 * <p>All rights reserved.
 * <p>��Ȩ���У������н�������������޹�˾
 * <p>δ������˾��ɣ��������κη�ʽ���ƻ�ʹ�ñ������κβ��֣�
 * <p>��Ȩ�߽��ܵ�����׷����
 * <p>DERIVED FROM: NONE
 * <p>PURPOSE:		��ȡIP��ַ
 * <p>AUTHOR:   	hyq
 * <p>DATE:         2012-11-23
 * <p>HISTORY:      1.0
*//*
public class GetIPAddress {
	

	*//**
	 * ��ȡ��ǰ����ϵͳ����. return ����ϵͳ���� ����:windows,Linux,Unix��.
	 *//*
	public String getOSName() {
		return System.getProperty("os.name").toLowerCase();
	}
	
	*//**
	 * ��ȡwidnows������ip��ַ.
	 * @return ip��ַ
	 *//*
	public  String getWindowsIPAddress() {
		String ip = null;
		BufferedReader bufferedReader = null;
		Process process = null;
		try {
			
			// windows�µ������ʾ��Ϣ�а�����ip��ַ��Ϣ
			process = Runtime.getRuntime().exec("ipconfig /all");
			bufferedReader = new BufferedReader(new InputStreamReader(process
					.getInputStream()));
			String line = null;
			int index = -1;
			while ((line = bufferedReader.readLine()) != null) {
				
				// Ѱ�ұ�ʾ�ַ���[ip address]
				index = line.toLowerCase().indexOf("ip address");
				if (index != -1) {
					index = line.indexOf(":");
					if (index != -1) {
						//ȡ��ip��ַ��ȥ��2�߿ո�

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
	 * ��ȡLinux������ip��ַ
	 * @return ip��ַ
	 *//*
	public String getLinuxIPAddress() {
		String ip = null;
		BufferedReader bufferedReader = null;
		Process process = null;
		try {
			// linux�µ����һ��ȡeth0��Ϊ���������� ��ʾ��Ϣ�а�����ip��ַ��Ϣ
			
			process = Runtime.getRuntime().exec("ifconfig eth0");
			bufferedReader = new BufferedReader(new InputStreamReader(process
					.getInputStream()));
			String line = null;
			int index = -1;
			while ((line = bufferedReader.readLine()) != null) {
				index = line.toLowerCase().indexOf("IPADDR");
				// �ҵ���
				
				if (index != -1) {
					// ȡ��mac��ַ��ȥ��2�߿ո�
					 
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
	 * ��ȡIP��ַ
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
	 * ��ȡ�ͻ��˵���ʵIP��ַ
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