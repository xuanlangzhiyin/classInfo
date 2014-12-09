package com.wuhei.cms.systools;


import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;


public class PasswordHelper {
	
	
		
	
	/**
	 * 根据password和salt生成MD5密码
	 * 
	 * @param password
	 * @param salt
	 * @return
	 */
	public static final String getSaltedMD5Password(String password, Object salt){
		Md5PasswordEncoder md5 = new Md5PasswordEncoder();
		return md5.encodePassword(password, salt);
	}
	
	/**
	 * 根据password和salt生成密码SHA-
	 * 
	 * @param password
	 * @param salt
	 * @return
	 */
	public static final String getSaltedSHAPassword(String password, Object salt){
		ShaPasswordEncoder sha = new ShaPasswordEncoder();
		return sha.encodePassword(password, salt);
	}
}
