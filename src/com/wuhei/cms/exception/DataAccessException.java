package com.wuhei.cms.exception;


/**
 * �쳣�����ݿ�����쳣��ʵ�ֶ�org.springframework.dao.DataAccessException�ķ�װ
 * 
 *
 */
public class DataAccessException extends EvaException {

	public DataAccessException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}

	public DataAccessException (String msg, Throwable t){
		super(msg, t);
	}
}
