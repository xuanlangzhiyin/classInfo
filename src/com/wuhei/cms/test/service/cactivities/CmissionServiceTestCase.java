package com.wuhei.cms.test.service.cactivities;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wuhei.cms.model.Cmcredit;
import com.wuhei.cms.model.Cmission;
import com.wuhei.cms.model.Cmstudent;
import com.wuhei.cms.model.joint.CmissionListView;
import com.wuhei.cms.service.cactivities.CmissionService;
import com.wuhei.cms.test.service.ServiceTestCase;

public class CmissionServiceTestCase extends ServiceTestCase {

	@Autowired
	private CmissionService cmissionService;
	private Cmission cmission;
	
	private Cmstudent cmstudent;
	private Cmcredit cmcredit;
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void testgetCmissionList4Student() {
		
		Integer courseid = 775;
		Integer cstudentId = 1871;
		
		List <CmissionListView> cmissionListViews ;
		
		try {
			
			cmissionListViews = cmissionService.getCmissionList4Student(courseid, cstudentId);
			
			for(int i = 0;i<cmissionListViews.size();i++){
				System.out.println(cmissionListViews.get(i).getName()+" "+cmissionListViews.get(i).getIsactive()+" "+cmissionListViews.get(i).getIsSubmited());
			    System.out.println("-------------");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.print(e);
		}
		
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void testgetCmission() {
		
		
		try {
			
			cmission = cmissionService.getCmission(3);
					
			    System.out.println(cmission.getName());
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.print(e);
		}
		
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void testsavePersonalScore() {
		
		cmstudent = new Cmstudent();
		cmcredit= new Cmcredit();

		Byte credit=99;
		
		cmstudent.setId(1);
		
		cmcredit.setDescription("");
		cmcredit.setCredit(credit);
		cmcredit.setCmissionid(3);
		cmcredit.setCstudentid(896);

		try {
			
			cmissionService.savePersonalMissionScore(cmstudent, cmcredit);
			   System.out.println(cmission.getName());
				
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.print(e);
		}
		
		
		
	}
	
}
