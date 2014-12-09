package com.wuhei.cms.exception;

/** 
 * 异常：数据处理异常
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
