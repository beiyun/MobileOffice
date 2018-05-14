package com.beiyun.workers.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
	

	public static String getMD5(String str){
		StringBuffer buffer = null;
		try {
			buffer = new StringBuffer();
			MessageDigest digest = MessageDigest.getInstance("MD5");
			byte[] bs = digest.digest(str.getBytes());
			for (int i = 0; i < bs.length; i++) {
				int result = bs[i]&0xff;
				String s = Integer.toHexString(result);
				if(s.length()<2){
					s = "0"+s;
				}
				buffer.append(s);
			}
			return buffer.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
}
