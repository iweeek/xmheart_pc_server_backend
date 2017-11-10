package com.xmheart.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MessageDigestUtil {
	
	static private MessageDigest md = null;

	static public String Md5(String msg) {
		
		try {
			md = java.security.MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		md.update(msg.getBytes());
		
		byte byteData[] = md.digest();
		
		//convert the byte to hex format method 1
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
//         System.out.println("byteData: " + byteData[i]);
        }
        
//        System.out.println("sb: " + sb.toString());
        return sb.toString();
	}
	public static void main(String[] args) {
		String a = "123456";
//		System.out.println(MessageDigestUtil.Md5(a));
	}

}
