package com.wuhei.cms.web.interceptor;

import java.util.List;

import com.wuhei.cms.action.CativityCMRcreditAction;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class SaveGroupScoreInterceptor extends MethodFilterInterceptor {

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {

		Object action = invocation.getAction();
		if (action instanceof CativityCMRcreditAction) {
			CativityCMRcreditAction cativityCMRcreditAction = (CativityCMRcreditAction) action;
		    List<Byte> cmScoreList=cativityCMRcreditAction.getCmScoreList();
		    List<String> cmDescriptionList=cativityCMRcreditAction.getCmDescriptionList();
		    try {
		    	for(int i=0;i<cmScoreList.size();i++)
				{
					Byte score=(Byte)cmScoreList.get(i);
					if(score<0||score>100)
					{
						break;
					}				
				}
				
		    	for(int i=0;i<cmDescriptionList.size();i++)
		    	{
		    		String description=(String)cmDescriptionList.get(i);
		    		if(description.length()>90)
					{
		    			return Action.ERROR;					
		    		}
		    	}
		    
			} catch (Exception e) {
				return Action.ERROR;
				// TODO: handle exception
			}

		}
		return invocation.invoke();
	}

}
