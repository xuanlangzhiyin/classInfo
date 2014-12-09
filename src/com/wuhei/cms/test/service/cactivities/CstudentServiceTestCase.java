package com.wuhei.cms.test.service.cactivities;

import java.util.ArrayList;
import java.util.List;


import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wuhei.cms.model.deletedata.CstudentDeleteResult;
import com.wuhei.cms.model.joint.CstudentDetailView;
import com.wuhei.cms.service.basic.StudentService;
import com.wuhei.cms.service.cactivities.CstudentService;
import com.wuhei.cms.test.service.ServiceTestCase;

public class CstudentServiceTestCase extends ServiceTestCase {

	@Autowired
	public CstudentService cstudentService;

	@Autowired
	public StudentService studentService;

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void  testDeleteCstudentByCondition(){
	    //���췵�ؽ��
		CstudentDeleteResult cstudentDeleteResult = new CstudentDeleteResult();
		//�����������cstudentid���б�
		List<Integer> sourceCstudentid = new ArrayList<Integer>();
		//������Բ�ͬ��������Ҫ���벻ͬ�Ĳ���
		sourceCstudentid.add(18);
		sourceCstudentid.add(19);
		
		cstudentDeleteResult=cstudentService.deleteCstudentByCondition(sourceCstudentid);
		//��������ɿ���action����
		
		List<CstudentDetailView> errorCstudentDetailView = cstudentDeleteResult.getErrorCstudentDetailViews();

		System.out.println("SuccessCount: "
				+ cstudentDeleteResult.getSuccessCount());
		System.out.println("ErrorCount: "
				+ cstudentDeleteResult.getErrorCount());
		
		for (int i = 0; i < errorCstudentDetailView.size(); i++) {
			System.out.println(errorCstudentDetailView.get(i).toString());
		}


	}
}
