package com.wuhei.cms.exception;


/**
 * 异常：数据库访问异常，实现对org.springframework.dao.DataAccessException的封装
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
