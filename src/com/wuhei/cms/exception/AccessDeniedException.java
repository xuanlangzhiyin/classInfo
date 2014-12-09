 package com.wuhei.cms.exception;
/**
 * BusinessLogicErrorException：
 * 
 * 业逻辑错误, 如任务设置了不可编辑任务就不能重新分组
 * 
 *
 */
public class AccessDeniedException extends EvaException {
	
	public AccessDeniedException(String msg)  
	{  
		super(msg);  
	} 
	
	public AccessDeniedException (String msg, Throwable t){
		super(msg, t);
	}
}
