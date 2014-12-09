package com.wuhei.cms.model.joint;

import com.wuhei.cms.model.Cevaluation;

public class CevaluationListView extends Cevaluation 

{
   private String code;
  
   private Integer csettingid;
   
   private Boolean isfinal;
   
   /**
    * getters and setters 
    * @return
    */
public String getCode() {
	return code;
}
public void setCode(String code) {
	this.code = code;
}

public Integer getCsettingid() {
	return csettingid;
}
public void setCsettingid(Integer csettingid) {  
	this.csettingid = csettingid;
}
public Boolean getIsfinal() {
	return isfinal;
}
public void setIsfinal(Boolean isfinal) {
	this.isfinal = isfinal;
}

}