package com.wuhei.cms.exception;

/** 
 * �쳣�����ݴ����쳣
 * 
 * 
 *
 */
public class FileProcessException extends EvaException {
	public FileProcessException(String msg) {
		super(msg);
	}
	public FileProcessException (String msg, Throwable t){
		super(msg, t);
	}
}
