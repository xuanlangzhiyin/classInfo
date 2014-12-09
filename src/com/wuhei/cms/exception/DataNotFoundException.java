package com.wuhei.cms.exception;

/**
 * 异常：查找的数据不存在
 * 
 *
 */
public class DataNotFoundException extends EvaException{

	public DataNotFoundException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}

	public DataNotFoundException (String msg, Throwable t){
		super(msg, t);
	}
}
