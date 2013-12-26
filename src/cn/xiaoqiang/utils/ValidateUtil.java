package cn.xiaoqiang.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author hyq 
 * create date : 2012-12-12 
 * decription : 验证工具类
 */
public class ValidateUtil {

	/**
	 * 只能为中文
	 * 
	 * @param input
	 *            输入的内容
	 * @param message
	 *            提示信息
	 * @throws Exception
	 */
	public static void checkChinese(String input, String message) {
		if (null == input || input.equals(""))
			return;
		String reg = "[\u4e00-\u9fa5]*";
		Pattern pattern = Pattern.compile(reg);
		if (!pattern.matcher(input).matches())
			throw new NullPointerException(message);
	}

	/**
	 * 只能为数字
	 * 
	 * @param input
	 *            输入的内容
	 * @param message
	 *            提示信息
	 * @throws Exception
	 */
	public static void checkNumber(String input, String message) {
		if (null == input || input.equals(""))
			return;
		String reg = "[0-9]*";
		Pattern pattern = Pattern.compile(reg);
		if (!pattern.matcher(input).matches())
			throw new NullPointerException(message);
	}

	/**
	 * 邮政编码只能为6位数字
	 * 
	 * @param input
	 *            输入的内容
	 * @param message
	 *            提示信息
	 * @throws Exception
	 */
	public static void checkPostalcode(String input, String message) {
		if (null == input || input.equals(""))
			return;
		String reg = "\\d{6}";
		// String reg = "[1-9]\\d{5}(?!\\d)"; //正确
		Pattern pattern = Pattern.compile(reg);
		if (!pattern.matcher(input).matches())
			throw new NullPointerException(message);
	}


	/**
	 * 区号是3位或4位数字，区号和直播号之间用-分割，直播号限制输入1到15位
	 * 
	 * @param input
	 * @param message
	 */
	public static void checkTel(String input, String message) {
		if (null == input || input.equals(""))
			return;
		String reg = "\\d{4}-\\d{1,15}|\\d{3}-\\d{1,15}";
		Pattern pattern = Pattern.compile(reg);
		if (!pattern.matcher(input).matches())
			throw new NullPointerException(message);
	}

	/**
	 * 只能为数字且是3的倍数
	 * 
	 * @param input
	 *            输入的内容
	 * @param message
	 *            提示信息
	 * @throws Exception
	 */
	public static void checkNumberAND3(String input, String message) {
		if (null == input || input.equals(""))
			return;
		String reg = "[0-9]*";
		Pattern pattern = Pattern.compile(reg);
		if ((!pattern.matcher(input).matches()) || (input.length() % 3 != 0))
			throw new NullPointerException(message);
	}

	/**
	 * 检查sql语句的合法性，不能带有% ???
	 * 
	 * @param input
	 *            输入的内容
	 * @param message
	 *            提示信息
	 */
	public static void checkSQL(String input, String message) {
		String reg = "%";
		Pattern pattern = Pattern.compile(reg);
		if ((pattern.matcher(input).find()))
			throw new NullPointerException(message);
	}

	/**
	 * 匹配字符串中有相同字符串的，如找出"java4343 Java6565 34JAVA65"中 含Java的字符串
	 * 
	 * @param input
	 *            输入的内容
	 * @param message
	 *            提示信息
	 */
	public static void checkReplayStr(String input, String message) {
		String reg = "JavA"; // String reg = "^JavA"; ^ 开头的意思
		Pattern pattern = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(input);
		while (matcher.find()) {
			System.out.println(matcher.group());
		}
	}
	
    /**
     * 匹配字符串是否为日期格式
     * 
	 * @param input
	 *            输入的内容
	 * @param message
	 *            提示信息
     */
    public static boolean checkDate(String input, String message) {
    	String reg = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$"; 
        Pattern pattern = Pattern.compile(reg);
        Matcher m = pattern.matcher(input);
        if (m.matches()) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * 匹配电信号码 133 153 189
	 * @param input
	 *            输入的内容   
     */
    public static boolean checkPhone(String input) {
    	String reg = "(133|153|189)\\d{8}";
        Pattern pattern = Pattern.compile(reg);
        Matcher m = pattern.matcher(input);
        if (m.matches()) {
            return true;
        } else {
            return false;
        }
    }
    
    
    /**
     * 匹配ip地址
	 * @param input
	 *            输入的内容   
     */
    public static boolean checkIPaddr(String input) {
    	String reg = "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";
        Pattern pattern = Pattern.compile(reg);
        Matcher m = pattern.matcher(input);
        if (m.matches()) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * 把字符串的某个元音替换成大写
     * @param input     例如： Here is my project
     * @return
     */
    public static boolean replayStr(String input){
    	//input = input.replaceAll("[aeiou]", "?"); //字符串中的所有元音替换为 ?
    	//input = input.replaceFirst("[aeiuo]", "?");	//字符串中的第一个元音替换为 ?
    	//System.out.println(input);
    	StringBuffer stb = new StringBuffer();
    	Pattern p = Pattern.compile("[aeiou]");
    	Matcher m = p.matcher(input);
    	while(m.find()){
    		m.appendReplacement(stb, m.group().toUpperCase());
    		
    	}
    	System.out.println(stb);
    	return true;
    }
    
    
    /**
     * [0-9]{3}-[0-9]{5}  表示前3个是数字 ，第4个连接符，后面5个是数字
     * [0-9]{3}-?[0-9]{5}  如果加上？问号 就表示 非必须匹配连接符
	 * @param input
	 *            输入的内容   如 010-55522
     */
    public static boolean checkRule01(String input) {
    	String reg = "[0-9]{3}-?[0-9]{5}";
        Pattern pattern = Pattern.compile(reg);
        Matcher m = pattern.matcher(input);
        if (m.matches()) {
            return true;
        } else {
            return false;
        }
    }
    
    
    /**
     * [^A]*   表示第一个字符不是能是A,   ^表示 否的意思
	 * @param input
	 *            输入的内容   
     */
    public static boolean checkRule02(String input) {
    	String reg = "[^A]*";
        Pattern pattern = Pattern.compile(reg);
        Matcher m = pattern.matcher(input);
        if (m.matches()) {
            return true;
        } else {
            return false;
        }
    }
    
    
    /**
     * 	\\d{1,2}\\s\\d{1,2},\\d{4}  其中 \s表示必须的空格        ,表示必须的逗号
     * 	
     *   匹配格式如 12 12,2012  
	 * @param input
	 *            输入的内容   
     */
    public static boolean checkRule03(String input) {
    	String reg = "\\d{1,2}\\s\\d{1,2},\\d{4}";
        Pattern pattern = Pattern.compile(reg);
        Matcher m = pattern.matcher(input);
        if (m.matches()) {
            return true;
        } else {
            return false;
        }
    }
    
    
    
    
    
    
    
	// [0-9A-Za-z_]* 只能是数字||字母 || 下划线，无限制位数
	// [0-9A-Za-z_]{7,30}? 只能是数字||字母 || 下划线，限制输入7到30位
	// Pattern.CASE_INSENSITIVE 大小写不敏感
    
    //[0-9]{4}[A-Z]{2} 表示前4个是数字，后两个是字母
    
    //[0-9]{3}-[0-9]{5}  表示前3个是数字 ，第4个连接符，后面5个是数字
    
    // 常用符号
    //		符号		等价的正则表达式
    //		\d		[0-9]
    //		\D		[^0-9]
    //		\w		[A-Z0-9]
    //		\w		[^A-Z0-9]
    //		\s		[\t\n\r\f]
    //		\S		[^\t\n\r\f]
       

	public static void main(String[] args) {
		//checkSQL("java4343 Java6565 34JAVA65", "OK");
		if(checkIPaddr("192.68.0.29")){
			System.out.println("OK!");
		}else{
			System.out.println("dead");
		}
		
	}

}
