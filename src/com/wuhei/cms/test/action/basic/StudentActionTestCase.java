package com.wuhei.cms.test.action.basic;
import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.wuhei.cms.action.StudentAction;
import com.wuhei.cms.test.action.ActionTestCase;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionProxy;

public class StudentActionTestCase extends ActionTestCase{
	
	Logger logger = Logger.getLogger(StudentActionTestCase.class);
	
	@Test
	public void testGetStudentList(){
		// 登录教务员G03031
		loginDefaultACAMGR();
		
		// 获取需要测试的proxy
		ActionProxy proxy = getActionProxy("/acamgr/listStudent.action");
		Assert.assertNotNull(proxy);
		
		//设置 ActionContext的session map
		ActionContext actionContext = proxy.getInvocation().getInvocationContext();
		initSession(actionContext);
		
		// 获取需要测试的action
		StudentAction action = (StudentAction)proxy.getAction();
		
		// 构造输入参数
		
		// 进行业务测试
		try {
			// 方案1
			// 调用 proxy.execute()
			// 将执行与该action相关的interceptor
			String result = proxy.execute();
			
			// 方案2
			// 直接调用action的方法
			// 将不执行相关的interceptor
			//String result = action.getTeacherList();
			
			Assert.assertEquals(Action.SUCCESS, result);

		} catch (Exception e) {
			logger.info(e.getMessage());
			Assert.fail("抛出异常，测试失败");
		}
	}

}

