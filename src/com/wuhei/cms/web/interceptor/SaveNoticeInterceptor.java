package com.wuhei.cms.web.interceptor;

import com.wuhei.cms.action.notice.NoticeAction;
import com.wuhei.cms.model.Notice;
import com.wuhei.cms.utils.Utils;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;


public class SaveNoticeInterceptor extends MethodFilterInterceptor {

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		
		Object action = invocation.getAction();
		if (action instanceof NoticeAction){
			NoticeAction noticeAction = (NoticeAction)action;
			Notice notice = noticeAction.getNotice();
			if(!Utils.isRequiredStringParamOK(notice.getTitle(), 1, 25)){
				return Action.ERROR;
			}
			if(noticeAction.getFile()==null){
				return Action.ERROR;
			}
		}
		
		return invocation.invoke();
	}

}
