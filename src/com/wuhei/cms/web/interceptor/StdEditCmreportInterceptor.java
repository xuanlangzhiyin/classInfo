package com.wuhei.cms.web.interceptor;

import com.wuhei.cms.action.CativityCMreportAction;
import com.wuhei.cms.dao.cactivities.CmissionDAO;
import com.wuhei.cms.model.Cmission;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

@SuppressWarnings("serial")
public class StdEditCmreportInterceptor extends MethodFilterInterceptor{

	
	private CmissionDAO cmissionDAO;
	
	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		// TODO Auto-generated method stub
		
		Object action = invocation.getAction();
		if (action instanceof CativityCMreportAction){
			
			CativityCMreportAction cativityCMreportAction=(CativityCMreportAction) action;
					
			 try {
			    	
				    Integer cmissionid = cativityCMreportAction.getCmissionid();
				    
				    Cmission cmission=cmissionDAO.getCmission(cmissionid);
				    
					if(cmission.getIsactive().equals("不可提交"))
					{
						return Action.ERROR;
					}
					
					
			} catch (Exception e) {
				return Action.ERROR;
				// TODO: handle exception
			}

		}
		return invocation.invoke();
	}

	public CmissionDAO getCmissionDAO() {
		return cmissionDAO;
	}

	public void setCmissionDAO(CmissionDAO cmissionDAO) {
		this.cmissionDAO = cmissionDAO;
	}

	
}
