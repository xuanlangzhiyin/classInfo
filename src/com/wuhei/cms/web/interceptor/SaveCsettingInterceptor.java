package com.wuhei.cms.web.interceptor;

import com.wuhei.cms.action.McsettingAction;
import com.wuhei.cms.model.Csetting;
import com.wuhei.cms.utils.Utils;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

@SuppressWarnings("serial")
public class SaveCsettingInterceptor extends MethodFilterInterceptor {

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {

		Object action = invocation.getAction();
		if (action instanceof McsettingAction) {
			McsettingAction mcsettingAction = (McsettingAction) action;
			Csetting csetting = mcsettingAction.getCsetting();
			// csetting.name
			if (!Utils.isRequiredStringParamOK(csetting.getName(), 1, 30)) {
				return Action.ERROR;
			}

		}
		return invocation.invoke();
	}

}
