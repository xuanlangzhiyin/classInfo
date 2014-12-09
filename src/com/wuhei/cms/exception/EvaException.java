package com.wuhei.cms.exception;

/**
 * classInfo项目的exception基类
 * 
 *
 */
public class EvaException extends RuntimeException{
	
	public EvaException(String msg)  
	{  
		super(msg);  
	} 
	
	public EvaException (String msg, Throwable t){
		super(msg, t);
	}
}
