package cn.xiaoqiang.utils;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * 
 * 获取客户端MAC地址
 * 
 *
 */
public class MacAddress {
	
	public static String hexByte(byte b) {
		String s = "000000" + Integer.toHexString(b);
		return s.substring(s.length() - 2);
	}

/*	*//**
	 * jdk1.6下API获取mac地址
	 * @return
	 *//*
	public static String getMAC() {
		Enumeration<NetworkInterface> el;
		String mac_s = "";
		try {
			el = NetworkInterface.getNetworkInterfaces();
			while (el.hasMoreElements()) {
				byte[] mac = el.nextElement().getHardwareAddress();
				if (mac == null)
					continue;
				mac_s = hexByte(mac[0]) + "-" + hexByte(mac[1]) + "-"
						+ hexByte(mac[2]) + "-" + hexByte(mac[3]) + "-"
						+ hexByte(mac[4]) + "-" + hexByte(mac[5]);
				System.out.println(mac_s);

			}
		} catch (SocketException e1) {
			e1.printStackTrace();
		}
		return mac_s;
	}*/
	
	/**
	 * 获取widnows网卡的mac地址.
	 * @return mac地址
	 */
	public static String getWindowsMACAddress() {
		String mac = null;
		BufferedReader bufferedReader = null;
		Process process = null;
		try {
			
			// windows下的命令，显示信息中包含有mac地址信息
			process = Runtime.getRuntime().exec("ipconfig /all");
			bufferedReader = new BufferedReader(new InputStreamReader(process
					.getInputStream()));
			String line = null;
			int index = -1;
			while ((line = bufferedReader.readLine()) != null) {
				if(line.indexOf("本地连接") != -1)     //排除有虚拟网卡的情况
					continue;
				
				// 寻找标示字符串[physical address]
				index = line.toLowerCase().indexOf("physical address");
				if (index != -1) {
					index = line.indexOf(":");
					if (index != -1) {
						//取出mac地址并去除2边空格

						mac = line.substring(index + 1).trim();
						System.out.println(mac);
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

		return mac;
	}
	
	
	public static void main(String[] args) {
		//getMAC();
	}
}
