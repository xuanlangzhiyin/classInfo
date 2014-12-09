package com.wuhei.cms.exception;

/**
 * FatalDataErrorException：
 * 
 * 系统严重错误，如项目基础数据加载失败等
 * 
 
 *
 */
public class FatalDataErrorException extends EvaException{

	public FatalDataErrorException(String msg)  
	{  
		super(msg);  
	} 
	
	public FatalDataErrorException (String msg, Throwable t){
		super(msg, t);
	}
}