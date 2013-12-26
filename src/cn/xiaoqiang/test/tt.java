package cn.xiaoqiang.test;

import java.security.MessageDigest;

import cn.xiaoqiang.utils.SHA1;

public class tt {
	
	public static String getMD5Str(String paramString)
	  {
		    MessageDigest localMessageDigest = null;
		    StringBuffer localStringBuffer = null;
		    try
		    {
		      localMessageDigest = MessageDigest.getInstance("MD5");
		      localMessageDigest.reset();
		      localMessageDigest.update(paramString.getBytes("UTF-8"));
		      byte[] arrayOfByte = localMessageDigest.digest();
		      System.out.println(arrayOfByte.length);
		      localStringBuffer = new StringBuffer();
		      for (int i = 0;i<arrayOfByte.length; i++)
		      {
		        if (Integer.toHexString(0xFF & arrayOfByte[i]).length() != 1){
		        	localStringBuffer.append("0").append(Integer.toHexString(0xFF & arrayOfByte[i]));
		        }
		      }
		    }
		    catch (Exception e)
		    {
		    	e.printStackTrace();
		    }
		    
		    return localStringBuffer.toString();
	  }
	
	
	public static void main(String[] args) {
		
		
		/*Loop :for(int i=0; i<3;i++){
			System.out.println("i="+i);
			for(int j=0;j<3;j++){
				if(1!=2){
					break Loop;
				}
			}
		}*/
		
		/*SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.printf(sdf.format(new Date()));*/
		//String aa="1";
		//System.out.printf((Integer.parseInt(aa)+1)+"");
		
		//System.out.println(SHA1.str_sha1("000102050403060708"));
		//System.out.println(SHA1.str_sha1("3a0249c3c76e8"));
		System.out.println(getMD5Str("000102050403060708"));
	}
	

}


