 package com.wuhei.cms.exception;
/**
 * BusinessLogicErrorException��
 * 
 * ҵ�߼�����, �����������˲��ɱ༭����Ͳ������·���
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
