package com.wuhei.cms.exception;

/**
 * FatalDataErrorException��
 * 
 * ϵͳ���ش�������Ŀ�������ݼ���ʧ�ܵ�
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