package com.wuhei.cms.service.basic;

import java.util.List;

import com.wuhei.cms.model.Department;

public interface DepartmentService {

	/**
	 * �������Ժϵid������
	 * ������ʦʱ����Ҫѡ��ý�ʦ������Ժϵ��ʹ��������
	 * @return ���ؽ�ʦ��Ϣ�б�
	 */
	public List<Department> getDepartmentList();
}
