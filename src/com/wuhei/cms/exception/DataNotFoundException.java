package com.wuhei.cms.exception;

/**
 * �쳣�����ҵ����ݲ�����
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
