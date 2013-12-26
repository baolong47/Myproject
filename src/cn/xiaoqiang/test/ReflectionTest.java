package cn.xiaoqiang.test;

import java.lang.reflect.Field;

public class ReflectionTest {
	public int i = 1;
	
	public static void main(String[] args) {
		try {
			Class cls = Class.forName("cn.xiaoqiang.test.ReflectionTest");
			Field fld = cls.getField("i");
			ReflectionTest rt = new ReflectionTest();
			System.out.println("before:"+rt.i);
			
			fld.setInt(rt, 55);
			System.out.println("after:"+rt.i);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}


