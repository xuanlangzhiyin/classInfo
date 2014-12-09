package com.wuhei.cms.model.deletedata;

import java.util.List;

import com.wuhei.cms.model.Csetting;


public class CsettingDeleteResult extends DeleteResult {

	List<Csetting> errorCsetting;

	public List<Csetting> getErrorCsetting() {
		return errorCsetting;
	}

	public void setErrorCsetting(List<Csetting> errorCsetting) {
		this.errorCsetting = errorCsetting;
	}

}
