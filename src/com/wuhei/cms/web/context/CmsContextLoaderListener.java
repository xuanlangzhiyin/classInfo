package com.wuhei.cms.web.context;

import java.io.File;
import java.util.Properties;

import javax.servlet.ServletContext;

import org.springframework.web.context.ConfigurableWebApplicationContext;
import org.springframework.web.context.ContextLoaderListener;


/**
 * http://www.journaldev.com/1945/servlet-listener-example-
 * servletcontextlistener-httpsessionlistener-and-servletrequestlistener
 * 
 * 
 * 
 */
public class CmsContextLoaderListener extends ContextLoaderListener {

	public CmsContextLoaderListener() {
		super();
	}
  
	@Override
	protected void customizeContext(ServletContext servletContext,
			ConfigurableWebApplicationContext applicationContext) {

		super.customizeContext(servletContext, applicationContext);

		

		// "/usr/local/apache-tomcat-7.0.53/webapps"
		// System.out.println("realPath: "+servletContext.getRealPath(File.separator));

		String realPath = servletContext.getRealPath(File.separator);
		String contextPath = servletContext.getContextPath();


		// *.properties 操作
		// http://www.cnblogs.com/panjun-Donet/archive/2009/07/17/1525597.html
		Properties prop = new Properties();
       
		try {
			
			prop.put("REAL_PATH", realPath);
			prop.put("CONTEXT_PATH", contextPath);
			
			// 进行classInfo web基础配置信息的初始化
			CmsWebContext.initialise(prop);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
	}

}