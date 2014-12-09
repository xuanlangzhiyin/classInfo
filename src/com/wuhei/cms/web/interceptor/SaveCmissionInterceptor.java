package com.wuhei.cms.web.interceptor;

import com.wuhei.cms.action.CactivityCmissionAction;
import com.wuhei.cms.model.Cmission;
import com.wuhei.cms.utils.Utils;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

@SuppressWarnings("serial")
public class SaveCmissionInterceptor extends MethodFilterInterceptor {

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {

		Object action = invocation.getAction();
		if (action instanceof CactivityCmissionAction) {
			CactivityCmissionAction cmissionAction = (CactivityCmissionAction) action;
			Cmission cmission = cmissionAction.getCmission();
			String requirement = cmission.getRequirement();
			if (!Utils.isRequiredStringParamOK(requirement, 1, 450)) {
				return Action.INPUT;
			}
		}
		return invocation.invoke();
	}

}
