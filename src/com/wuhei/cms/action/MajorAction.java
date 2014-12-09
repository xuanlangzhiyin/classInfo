package com.wuhei.cms.action;

import java.util.List;
import org.apache.log4j.Logger;
import com.wuhei.cms.model.Major;
import com.wuhei.cms.model.joint.MajorDetailView;
import com.wuhei.cms.model.joint.MajorListView;
import com.wuhei.cms.search.MajorSearchMeta;
import com.wuhei.cms.search.result.MajorPageResult;
import com.wuhei.cms.service.basic.MajorService;
import com.wuhei.cms.web.context.CmsWebContext;

/**
 * 
 * רҵ���������
 * 
 * 
 */
@SuppressWarnings("serial")
public class MajorAction extends BaseAction {

	Logger log = Logger.getLogger(MajorAction.class);

	protected MajorService majorService;

	/**
	 * ҳ�����������רҵid
	 */
	protected Integer majorid;

	/**
	 * ҳ���������-רҵ
	 */
	protected Major major;

	/**
	 * ����ҳ������רҵ��ϸ��ͼ
	 */
	protected MajorDetailView majorDetailView;

	/**
	 * ����ҳ������רҵ�б� ǰ̨��ҳ
	 */
	protected List<MajorListView> majors;

	/**
	 * ҳ�����������רҵ�б��ѯ���� �ݲ�ʹ��
	 */
	protected MajorSearchMeta majorSearchMeta;

	/**
	 * ����ҳ������������ҳ��Ϣ��ѧ���б����� �ݲ�ʹ��
	 */
	protected MajorPageResult majorPageResult;

	/**
	 * ��ԱԹ�û����鿴��ѧԺרҵ�б� ��̨��ҳ
	 * 
	 * @return
	 */
	public String listMajor4Acamgr() {
		
		internalGetMajor();
		
		log.info("����Ա�û�:��ѯרҵ�б�");
		
		return SUCCESS;

	}

	/**
	 * ��ʦ�û����鿴��ѧԺרҵ�б� ��̨��ҳ
	 * 
	 * @return
	 */
	public String listMajor4Teacher() {
		
		internalGetMajor();
		
		log.info("��ʦ�û�:��ѯרҵ�б�");
		
		return SUCCESS;

	}

	/**
	 * ѧ���û����鿴��ѧԺרҵ�б� ��̨��ҳ
	 * 
	 * @return
	 */
	public String listMajor4Student() {

		internalGetMajor();
		
		log.info("ѧ���û�:��ѯרҵ�б�");

		return SUCCESS;

	}

	private void internalGetMajor() {
		// ��ȡ��ǰ�û���ѧԺid
		Integer departmentid = CmsWebContext.getCurrentDepartmentId();
		
		// Ĭ�ϲ�ѯ����
		Integer currentPage = 1;
		String keyword = null;
		// ��ȡҳ�������ѯ����
		if (majorSearchMeta != null) {
			currentPage = majorSearchMeta.getCurrentPage();
			keyword = this.getDefaultSearchValue(majorSearchMeta.getKeyword(),
					null);
		}

		// ��ȡ��ѯ���
		majorPageResult = majorService.getMajorByConditions(departmentid,
				currentPage, keyword);

	}

	

	
	/*
	 * getters and setters
	 */

	public Integer getMajorid() {
		return majorid;
	}

	public void setMajorid(Integer majorid) {
		this.majorid = majorid;
	}

	public Major getMajor() {
		return major;
	}

	public void setMajor(Major major) {
		this.major = major;
	}

	public MajorDetailView getMajorDetailView() {
		return majorDetailView;
	}

	public void setMajorDetailView(MajorDetailView majorDetailView) {
		this.majorDetailView = majorDetailView;
	}

	public List<MajorListView> getMajors() {
		return majors;
	}

	public void setMajors(List<MajorListView> majors) {
		this.majors = majors;
	}

	public MajorSearchMeta getMajorSearchMeta() {
		return majorSearchMeta;
	}

	public void setMajorSearchMeta(MajorSearchMeta majorSearchMeta) {
		this.majorSearchMeta = majorSearchMeta;
	}

	public MajorPageResult getMajorPageResult() {
		return majorPageResult;
	}

	public void setMajorPageResult(MajorPageResult majorPageResult) {
		this.majorPageResult = majorPageResult;
	}

	public MajorService getMajorService() {
		return majorService;
	}

	public void setMajorService(MajorService majorService) {
		this.majorService = majorService;
	}

}
