package com.wuhei.cms.model.joint;

import com.wuhei.cms.model.Csetting;

public class CsettingDetailView extends Csetting 
{
	  

    /**
     * רҵ�γ������γ��������
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
