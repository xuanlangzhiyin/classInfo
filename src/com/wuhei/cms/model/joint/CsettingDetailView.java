package com.wuhei.cms.model.joint;

import com.wuhei.cms.model.Csetting;

public class CsettingDetailView extends Csetting 
{
	  

    /**
     * 专业课程所属课程类别名称
     */
    private String ccategoryname;
    
    private String majorname;
    
    
    public String getMajorname() {
		return majorname;
	}

	public void setMajorname(String majorname) {
		this.majorname = majorname;
	}


	public String getCcategoryname() {
		return ccategoryname;
	}

	public void setCcategoryname(String ccategoryname) {
		this.ccategoryname = ccategoryname;
	}

		public String toString() {
			return "CsettingDetailView [code=" + super.getCode() + ", name=" + super.getName()
					  + ", ccategory=" + ccategoryname
					 + ", level=" + super.getLevel() + "]";
	}

	
}
