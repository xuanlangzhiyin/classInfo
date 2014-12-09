package com.wuhei.cms.model.joint;

import com.wuhei.cms.model.Cmgroup;

public class CmgroupDetailView extends Cmgroup 
{
	 
    /**
     * 组长名字
     */
    private String leaderName;
    
    /**
	 * 是否已提交报告中文
	 */
	private String isSubmitChinese;
    
    private int ismarked;
    
    
	public String getLeadername() {
		return leaderName;
	}

	public String getLeaderName() {
		return leaderName;
	}

	public void setLeaderName(String leaderName) {
		this.leaderName = leaderName;
	}

	public int getIsmarked() {
		return ismarked;
	}

	public void setIsmarked(int ismarked) {
		this.ismarked = ismarked;
	}

	public String getIsSubmitChinese() {
		return isSubmitChinese;
	}

	public void setIsSubmitChinese(String isSubmitChinese) {
		this.isSubmitChinese = isSubmitChinese;
	}

	


	
    
}
