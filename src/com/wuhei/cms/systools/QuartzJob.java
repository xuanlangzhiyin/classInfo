package com.wuhei.cms.systools;


import java.util.Date;

public class QuartzJob {
	
	public void autoRun(){
		Date date = new Date();
		String s = "autoRun∆Ù∂Ø...!"+date;
		System.out.print(s);
	}
}
