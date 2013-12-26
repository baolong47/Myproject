package cn.xiaoqiang.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

/**
 * java中获取系统属性以及环境变量
 * 
 * @author 黄宇强
 * @date 2012-12-9 下午07:52:16
 * @description System.getEnv（）和System.getProperties（）的区别 从概念上讲，系统属性 和环境变量
 *              都是名称与值之间的映射。两种机制都能用来将用户定义的信息传递给 Java 进程。
 *              环境变量产生更多的全局效应，因为它们不仅对Java 子进程可见，而且对于定义它们的进程的所有子进程都是可见的。
 *              在不同的操作系统上，它们的语义有细微的差别，比如，不区分大小写。因为这些原因，环境变量更可能有意料不到的副作用。
 *              最好在可能的地方使用系统属性。环境变量应该在需要全局效应的时候使用，或者在外部系统接口要求使用环境变量时使用（比如 PATH）。
 */
public class SystemUtil {
	
	public static void getEnv(){
		/*Map m = System.getenv();
		for (Iterator it = m.keySet().iterator(); it.hasNext();) {
			String key = (String) it.next();
			String value = (String) m.get(key);
			if(key.equals("windir")){
				System.out.println(value);
			}
			//System.out.println(key + ":" + value);
		}*/
	}
	
	public static void getProPerties(){
		  Properties p = System.getProperties();
		  for ( Iterator it = p.keySet().iterator(); it.hasNext(); ) { String
		  key = (String ) it.next(); String value = (String ) p.get(key);
		  System.out.println(key +":" +value); }
	}
	
	/**
	 * jdk1.4获取系统命令路径 ：SystemRoot=C:\WINDOWS
	 * @return
	 */
	public static String getSystemRoot(){
		String cmd = null;
		String os = null;
		String result = null;
		String envName = "windir";
		
		os = System.getProperty("os.name").toLowerCase();

		if (os.startsWith("windows")) {
			cmd = "cmd /c SET";
		} else {
			cmd = "env";
		}

		try {
			Process p = Runtime.getRuntime().exec(cmd);
			InputStreamReader isr = new InputStreamReader(p.getInputStream());
			BufferedReader commandResult = new BufferedReader(isr);
			String line = null;
			while ((line = commandResult.readLine()) != null) {
				System.out.println(line);
				if (line.indexOf(envName) > -1) {
					result =  line.substring(line.indexOf(envName)
							+ envName.length() + 1);
					return result;
				}
			}
		} catch (Exception e) {
			System.out.println("OSEnvironment.class error: " + cmd + ":" + e);
		}
		return null;
	}
	
	public static void main(String[] args) {
		//getEnv();
		//getProPerties();
		String systemRootPath = getSystemRoot()+"/system32/ipconfig /all";
		System.out.println(systemRootPath);
	}
	
}
