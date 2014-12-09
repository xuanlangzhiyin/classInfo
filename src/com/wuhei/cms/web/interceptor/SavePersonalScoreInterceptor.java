package com.wuhei.cms.web.interceptor;

import com.wuhei.cms.action.CativityCMRcreditAction;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

@SuppressWarnings("serial")
public class SavePersonalScoreInterceptor extends MethodFilterInterceptor{

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {

		Object action = invocation.getAction();
		if (action instanceof CativityCMRcreditAction) {
			CativityCMRcreditAction cativityCMRcreditAction = (CativityCMRcreditAction) action;
		    Byte score=cativityCMRcreditAction.getCmcredit().getCredit();
		    String description=cativityCMRcreditAction.getCmcredit().getDescription();
		    try {
		    	
					
					if(score<0||score>100)
					{
						return Action.ERROR;
					}
					if(description.length()>90)
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

}
