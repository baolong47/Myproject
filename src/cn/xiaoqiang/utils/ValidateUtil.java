package cn.xiaoqiang.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author hyq 
 * create date : 2012-12-12 
 * decription : ��֤������
 */
public class ValidateUtil {

	/**
	 * ֻ��Ϊ����
	 * 
	 * @param input
	 *            ���������
	 * @param message
	 *            ��ʾ��Ϣ
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
	 * ֻ��Ϊ����
	 * 
	 * @param input
	 *            ���������
	 * @param message
	 *            ��ʾ��Ϣ
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
	 * ��������ֻ��Ϊ6λ����
	 * 
	 * @param input
	 *            ���������
	 * @param message
	 *            ��ʾ��Ϣ
	 * @throws Exception
	 */
	public static void checkPostalcode(String input, String message) {
		if (null == input || input.equals(""))
			return;
		String reg = "\\d{6}";
		// String reg = "[1-9]\\d{5}(?!\\d)"; //��ȷ
		Pattern pattern = Pattern.compile(reg);
		if (!pattern.matcher(input).matches())
			throw new NullPointerException(message);
	}


	/**
	 * ������3λ��4λ���֣����ź�ֱ����֮����-�ָֱ������������1��15λ
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
	 * ֻ��Ϊ��������3�ı���
	 * 
	 * @param input
	 *            ���������
	 * @param message
	 *            ��ʾ��Ϣ
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
	 * ���sql���ĺϷ��ԣ����ܴ���% ???
	 * 
	 * @param input
	 *            ���������
	 * @param message
	 *            ��ʾ��Ϣ
	 */
	public static void checkSQL(String input, String message) {
		String reg = "%";
		Pattern pattern = Pattern.compile(reg);
		if ((pattern.matcher(input).find()))
			throw new NullPointerException(message);
	}

	/**
	 * ƥ���ַ���������ͬ�ַ����ģ����ҳ�"java4343 Java6565 34JAVA65"�� ��Java���ַ���
	 * 
	 * @param input
	 *            ���������
	 * @param message
	 *            ��ʾ��Ϣ
	 */
	public static void checkReplayStr(String input, String message) {
		String reg = "JavA"; // String reg = "^JavA"; ^ ��ͷ����˼
		Pattern pattern = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(input);
		while (matcher.find()) {
			System.out.println(matcher.group());
		}
	}
	
    /**
     * ƥ���ַ����Ƿ�Ϊ���ڸ�ʽ
     * 
	 * @param input
	 *            ���������
	 * @param message
	 *            ��ʾ��Ϣ
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
     * ƥ����ź��� 133 153 189
	 * @param input
	 *            ���������   
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
     * ƥ��ip��ַ
	 * @param input
	 *            ���������   
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
     * ���ַ�����ĳ��Ԫ���滻�ɴ�д
     * @param input     ���磺 Here is my project
     * @return
     */
    public static boolean replayStr(String input){
    	//input = input.replaceAll("[aeiou]", "?"); //�ַ����е�����Ԫ���滻Ϊ ?
    	//input = input.replaceFirst("[aeiuo]", "?");	//�ַ����еĵ�һ��Ԫ���滻Ϊ ?
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
     * [0-9]{3}-[0-9]{5}  ��ʾǰ3�������� ����4�����ӷ�������5��������
     * [0-9]{3}-?[0-9]{5}  ������ϣ��ʺ� �ͱ�ʾ �Ǳ���ƥ�����ӷ�
	 * @param input
	 *            ���������   �� 010-55522
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
     * [^A]*   ��ʾ��һ���ַ���������A,   ^��ʾ �����˼
	 * @param input
	 *            ���������   
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
     * 	\\d{1,2}\\s\\d{1,2},\\d{4}  ���� \s��ʾ����Ŀո�        ,��ʾ����Ķ���
     * 	
     *   ƥ���ʽ�� 12 12,2012  
	 * @param input
	 *            ���������   
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
    
    
    
    
    
    
    
	// [0-9A-Za-z_]* ֻ��������||��ĸ || �»��ߣ�������λ��
	// [0-9A-Za-z_]{7,30}? ֻ��������||��ĸ || �»��ߣ���������7��30λ
	// Pattern.CASE_INSENSITIVE ��Сд������
    
    //[0-9]{4}[A-Z]{2} ��ʾǰ4�������֣�����������ĸ
    
    //[0-9]{3}-[0-9]{5}  ��ʾǰ3�������� ����4�����ӷ�������5��������
    
    // ���÷���
    //		����		�ȼ۵�������ʽ
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
