package com.wuhei.cms.systools;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SysDataManager {
	
	public static SqlSessionFactory sqlSessionFactory;
	
	protected void setup() throws IOException{
		//  SqlSessionFactory
		Reader reader = Resources
				.getResourceAsReader("configs/mybatis/mybatis-config-test.xml");
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		reader.close();
	}
	
}
