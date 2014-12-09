package com.wuhei.cms.test.service.cactivities;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.wuhei.cms.model.Cteacher;
import com.wuhei.cms.service.cactivities.CteacherService;
import com.wuhei.cms.test.service.ServiceTestCase;

public class CteacherServiceTestCase extends ServiceTestCase {

	@Autowired
	public CteacherService cteacherService;

	Integer courseid = Integer.valueOf(550);
	Integer teacherid = Integer.valueOf(778);

	@Test
	public void testInsertCteacher() {

		Cteacher cteacher = new Cteacher();
		boolean isdominate = false;
		cteacher.setCourseid(courseid);
		cteacher.setTeacherid(teacherid);
		cteacher.setIsdominate(isdominate);

		cteacherService.insertCteacher(cteacher);
	}
	@Test
	public void testUpdateCteacher() {

		Cteacher cteacher = new Cteacher();
		boolean isdominate = true;
		cteacher.setCourseid(courseid);
		cteacher.setTeacherid(teacherid);
		cteacher.setIsdominate(isdominate);

		cteacherService.updateCteacher(cteacher);
	}
	@Test
	public void testDeleteCteacher() {

		cteacherService.deleteCteacher(courseid);

	}
	@Test
	public void testGetCteacher() {

		List<Cteacher> cteacher = new ArrayList<Cteacher>();

		cteacher = cteacherService.getCteacherByCourseid(568);

		for (int i = 0; i < cteacher.size(); i++) {
			System.out.println(cteacher.get(i).getTeacherid());

		}
	}

}
