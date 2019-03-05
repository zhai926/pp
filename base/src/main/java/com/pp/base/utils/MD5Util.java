package com.pp.base.utils;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Slf4j
public class MD5Util {
	public static String getMD5Str2(String str) {
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.reset();
			messageDigest.update(str.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		byte[] byteArray = messageDigest.digest();

		StringBuffer md5StrBuff = new StringBuffer();

		for (int i = 0; i < byteArray.length; i++) {
			if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
				md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
			else {
				md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
			}
		}
		return md5StrBuff.toString();
	}

	public static String getMD5Str(String sMsg) throws UnsupportedEncodingException {
		byte[] msg = sMsg.getBytes("UTF-8");
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.reset();
			messageDigest.update(msg);
		} catch (NoSuchAlgorithmException localNoSuchAlgorithmException) {
		}
		byte[] b = messageDigest.digest();
		return new String(Base64.encodeBase64(b));
	}
	
	public static String getMD5Str3(String sMsg) throws UnsupportedEncodingException {
		byte[] msg = sMsg.getBytes("UTF-8");
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.reset();
			messageDigest.update(msg);
		} catch (NoSuchAlgorithmException localNoSuchAlgorithmException) {
		}
		byte[] b = messageDigest.digest();
		return new String(b).toUpperCase();
	}
	
	public static void main(String[] args) {
		
		try {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("queryCodeType", "AREA_CODE");
			String sign = getMD5Str2(jsonObject.toJSONString() + "ABCDEFGHILGKLMKDFD");
			
			log.info("sign=" + sign);
			
		} catch (Exception ex) {
			ex.printStackTrace();
			
		}
		
	}
}