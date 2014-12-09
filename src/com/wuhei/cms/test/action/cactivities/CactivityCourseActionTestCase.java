package com.wuhei.cms.test.action.cactivities;

import java.util.List;

import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.wuhei.cms.action.CactivityCourseAction;
import com.wuhei.cms.model.joint.CourseListView;
import com.wuhei.cms.test.action.ActionTestCase;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionProxy;

/**
 * 
 * @author ¬��
 *
 */
public class CactivityCourseActionTestCase extends ActionTestCase{
	
	Logger logger = Logger.getLogger(CactivityCourseActionTestCase.class);
	/**
	 * ����Ա�鿴�γ̻�б� 
	 */
	@Test
	public void testGetCourseList4Acamgr(){
		// ��¼����ԱG03031
		loginDefaultACAMGR();
		
		// ��ȡ��Ҫ���Ե�proxy
		ActionProxy proxy = getActionProxy("/acamgr/listCourse.action");
		Assert.assertNotNull(proxy);
		
		//���� ActionContext��session map
		ActionContext actionContext = proxy.getInvocation().getInvocationContext();
		initSession(actionContext);
		
		// ��ȡ��Ҫ���Ե�action
		CactivityCourseAction action = (CactivityCourseAction)proxy.getAction();
		
		
		
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
			//�г��鵽�Ŀγ̻�б?
			List <CourseListView> courses = action.getCoursePageResult().getCourses();
			for(int count=0;count<courses.size();count++){
				System.out.println(courses.get(count).toString()+courses.get(count).getTeachername());
			}			
			Assert.assertEquals(Action.SUCCESS, result);

		} catch (Exception e) {
			logger.info(e.getMessage());
			Assert.fail("�׳��쳣������ʧ��");
		}
	}
	
	/**
	 * ��ʦ�鿴�Լ��Ŀγ̻�б�
	 */
	@Test
	public void testGetCourseList4Tch(){
		// ��¼��ʦ�´���
		loginDefaultTCH();
		
		// ��ȡ��Ҫ���Ե�proxy
		ActionProxy proxy = getActionProxy("/tch/listCourse.action");
		Assert.assertNotNull(proxy);
		
		//���� ActionContext��session map
		ActionContext actionContext = proxy.getInvocation().getInvocationContext();
		initSession(actionContext);
		
		// ��ȡ��Ҫ���Ե�action
		CactivityCourseAction action = (CactivityCourseAction)proxy.getAction();	
						
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
			//�г��鵽�Ŀγ̻�б?
			List <CourseListView> courses = action.getCoursePageResult().getCourses();
			for(int count=0;count<courses.size();count++){
				System.out.println(courses.get(count).toString()+courses.get(count).getTeachername());
			}
			
			Assert.assertEquals(Action.SUCCESS, result);

		} catch (Exception e) {
			logger.info(e.getMessage());
			Assert.fail("�׳��쳣������ʧ��");
		}
	}

	/**
	 * ѧ��鿴�Լ��Ŀγ̻�б�
	 */
	@Test
	public void testGetCourseList4Std(){
		// ��¼ѧ��
		loginDefaultsSTD();
		
		// ��ȡ��Ҫ���Ե�proxy
		ActionProxy proxy = getActionProxy("/std/listCourse.action");
		Assert.assertNotNull(proxy);
		
		//���� ActionContext��session map
		ActionContext actionContext = proxy.getInvocation().getInvocationContext();
		initSession(actionContext);
		
		// ��ȡ��Ҫ���Ե�action
		CactivityCourseAction action = (CactivityCourseAction)proxy.getAction();	
						
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
			//�г��鵽�Ŀγ̻�б?
			List <CourseListView> courses = action.getCoursePageResult().getCourses();
			for(int count=0;count<courses.size();count++){
				System.out.println(courses.get(count).toString()+courses.get(count).getTeachername());
			}
			
			Assert.assertEquals(Action.SUCCESS, result);

		} catch (Exception e) {
			logger.info(e.getMessage());
			Assert.fail("�׳��쳣������ʧ��");
		}
	}	
	
}
