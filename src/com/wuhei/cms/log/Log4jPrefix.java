package com.wuhei.cms.log;

import org.apache.log4j.MDC;

/**
 * 
 *
 */
public class Log4jPrefix {

	public static final void initialise(String userName, String userIP) {
		MDC.put("userName", userName);
		MDC.put("userIP", userIP);
	}
}
