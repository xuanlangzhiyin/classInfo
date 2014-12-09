package com.wuhei.cms.test.action.basic;

import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.wuhei.cms.action.TeacherAction;
import com.wuhei.cms.test.action.ActionTestCase;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionProxy;

public class TeacherActionTestCase extends ActionTestCase{
	
	Logger logger = Logger.getLogger(TeacherActionTestCase.class);
	
	@Test
	public void testGetTeacherList(){
		// ��¼����ԱG03031
		loginDefaultACAMGR();
		
		// ��ȡ��Ҫ���Ե�proxy
		ActionProxy proxy = getActionProxy("/acamgr/listTeacher.action");
		Assert.assertNotNull(proxy);
		
		//���� ActionContext��session map
		ActionContext actionContext = proxy.getInvocation().getInvocationContext();
		initSession(actionContext);
		
		// ��ȡ��Ҫ���Ե�action
		TeacherAction action = (TeacherAction)proxy.getAction();
		
		// �����������
		
		// ����ҵ�����
		try {
			// ����1
			// ���� proxy.execute()
			// ��ִ�����action��ص�interceptor
			String result = proxy.execute();
			
			// ����2
			// ֱ�ӵ���action�ķ���
			// ����ִ����ص�interceptor
			//String result = action.getTeacherList();
			
			Assert.assertEquals(Action.SUCCESS, result);

		} catch (Exception e) {
			logger.info(e.getMessage());
			Assert.fail("�׳��쳣������ʧ��");
		}
	}

}
