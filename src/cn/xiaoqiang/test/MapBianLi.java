package cn.xiaoqiang.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MapBianLi {
	public static void main(String[] args) {
		 Map<String,String> qu_titileNames = new HashMap<String,String>();
		
		 qu_titileNames.put("1", "项目代码");
		 qu_titileNames.put("1", "项目名称");
		 qu_titileNames.put("3", "概算文号");
		 qu_titileNames.put("4", "概算总额");
		 qu_titileNames.put("5", "计划金额");
		 qu_titileNames.put("6", "唯一编号");
		 qu_titileNames.put("7", "序号");
		 qu_titileNames.put("8", "建设单位");
		 
		 System.out.println(qu_titileNames.size());
		 for(int i= 0 ; i<qu_titileNames.size();i++){
			 System.out.println(qu_titileNames.get(""+i)); 
		 }
		 
		 
		/* for(Iterator it = qu_titileNames.keySet().iterator(); it.hasNext(); ){
			 String key = (String ) it.next(); 
			 String value = (String ) qu_titileNames.get(key);
			 System.out.println(key +":" +value); 
		 }
		 */
		 
		 /*List<String> list = new LinkedList<String>();
		 list.set(1, "项目代码1");
		 list.set(2, "项目代码2");
		 list.set(3, "项目代码3");
		 list.set(4, "项目代码4");
		 System.out.println(list.size());
		 
		 for(Iterator it= list.iterator();it.hasNext();){
			 int key =  (Integer)it.next();
			 String value = (String ) list.get(key);
			 System.out.println(key +":" +value); 
		 }*/
		 
	}
}


