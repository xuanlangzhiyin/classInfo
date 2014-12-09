package com.wuhei.cms.test.service.cactivities;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.wuhei.cms.service.cactivities.CgroupService;
import com.wuhei.cms.test.service.ServiceTestCase;

public class CgroupServiceTestCase extends ServiceTestCase{
	@Autowired
	private CgroupService cgroupService;
	@Test
	public void testdeleteCgroupMember() {
		Integer cstudentid=897;
		Integer cgroupid=1108;
		
		cgroupService.deleteCgroupMember(cstudentid, cgroupid);
	}
}
