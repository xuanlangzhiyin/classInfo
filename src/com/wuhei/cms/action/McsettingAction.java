package com.wuhei.cms.action;

import java.util.List;

import org.apache.log4j.Logger;

import com.wuhei.cms.model.Ccategory;
import com.wuhei.cms.model.Csetting;
import com.wuhei.cms.model.deletedata.CsettingDeleteResult;
import com.wuhei.cms.model.joint.CsettingDetailView;
import com.wuhei.cms.model.joint.CsettingListView;
import com.wuhei.cms.search.CsettingSearchMeta;
import com.wuhei.cms.search.result.CsettingPageResult;
import com.wuhei.cms.service.basic.CcategoryService;
import com.wuhei.cms.service.basic.CsettingService;

/**
 * רҵ�����רҵ�γ�
 * 
 * Major Course Setting Action
 * 
 * 
 */
@SuppressWarnings("serial")
public class McsettingAction extends MajorAction {

	Logger log = Logger.getLogger(McsettingAction.class);

	/**
	 * רҵ�γ̹���ҵ������ӿ�
	 */
	protected CsettingService csettingService;

	protected CcategoryService ccategoryService;

	/**
	 * ҳ�����������רҵ�γ�id
	 */
	protected Integer csettingid;

	/**
	 * ҳ�����������רҵ�γ�
	 */
	protected Csetting csetting;

	/**
	 * ����ҳ������רҵ�γ���ϸ��ͼ
	 */
	protected CsettingDetailView csettingDetailView;

	/**
	 * ����ҳ������רҵ�γ��б� ǰ̨��ҳ
	 */
	protected List<CsettingListView> csettings;

	/**
	 * ҳ�����������רҵ�γ��б��ѯ���� ������ ��̨��ҳʹ��
	 */
	protected CsettingSearchMeta csettingSearchMeta;

	/**
	 * ����ҳ������������ҳ��Ϣ��רҵ�γ��б����� ��̨��ҳʹ��
	 */
	protected CsettingPageResult csettingPageResult;
	/**
	 * ���ظ�ǰ̨�γ����ͣ���ǰ̨ѡ��
	 */
	protected List<Ccategory> ccategories;

	/**
	 * ҳ���������-Ҫɾ����רҵ�γ̵�id
	 */
	protected List<Integer> csettingId;

	/**
	 * ����ҳ����-ɾ��ʧ�ܵĿγ̵���Ϣ
	 */
	protected CsettingDeleteResult csettingDeleteResult;

	/**
	 * ϵͳ�û�: �鿴��רҵ��רҵ�γ� (����Ա) ��̨��ҳ
	 * 
	 * @return /acamgr/listCsetting.jsp
	 */
	public String listCsetting4Acamgr() {

		internalGetCsettings();

		log.info("����Ա�û�����ѯרҵ�γ��б�");

		return SUCCESS;
	}

	/**
	 * ϵͳ�û�: �鿴��רҵ��רҵ�γ� (��ʦ) ��̨��ҳ
	 * 
	 * @return /teacher/listCsetting.jsp
	 */
	public String listCsetting4Teacher() {

		internalGetCsettings();

		log.info("��ʦ�û�����ѯרҵ�γ��б�");

		return SUCCESS;
	}

	/**
	 * ϵͳ�û�: �鿴��רҵ��רҵ�γ� (ѧ��) ��̨��ҳ
	 * 
	 * @return /student/listCsetting.jsp
	 */
	public String listCsetting4Student() {

		internalGetCsettings();

		log.info("ѧ���û�����ѯרҵ�γ��б�");

		return SUCCESS;
	}

	private void internalGetCsettings() {
		// Ĭ�ϲ�ѯ����
		Integer currentPage = 1;
		String keyword = null;

		// ��ȡҳ�������ѯ����
		if (csettingSearchMeta != null) {
			currentPage = csettingSearchMeta.getCurrentPage();
			keyword = this.getDefaultSearchValue(
					csettingSearchMeta.getKeyword(), null);
		}

		// majorid: ҳ�洫�ݲ�����MajorAction.majorid
		// �����ݿ��ȡ��������רҵ�γ�
		csettingPageResult = csettingService.listCsettingListByConditions(
				majorid, keyword, currentPage);
	}

	/**
	 * ϵͳ�û����鿴רҵ�γ�
	 * 
	 * @return
	 */
	public String viewCsetting() {
		try {

			csettingDetailView = csettingService
					.getCsettingDetailView(csettingid);

			ccategories = ccategoryService.getCcategoryList();

		} catch (Exception e) {
			System.out.println(e);
			// TODO: handle exception
		}

		return SUCCESS;
	}

	

	/**
	 * ����Ա: ����༭רҵ�γ�
	 * @return
	 */
	public String editCsetting4Acamgr() {
		try {

			csettingDetailView = csettingService
					.getCsettingDetailView(csettingid);

			ccategories = ccategoryService.getCcategoryList();

		} catch (Exception e) {
			System.out.println(e);
			// TODO: handle exception
		}

		return SUCCESS;
	}

	/**
	 * ����Ա: ����רҵ�γ�
	 * 
	 * @return
	 */
	public String saveCsetting() {
		try {
			// ҳ������csettingDetailview--csetting
			csettingService.updateCsetting(csetting);
		} catch (Exception e) {
			log.debug(e.getMessage());
		}
		return SUCCESS;
	}

	/**
	 * ����Ա: ɾ��רҵ�γ� 
	 * 
	 * @return
	 */
	public String deleteCsetting() {
		if (csettingId == null){
		}
		else {
			csettingDeleteResult = csettingService.deleteCsetting(csettingId);
		}
		return SUCCESS;
	}

	
	/*
	 * getters and setters
	 */



	public CsettingService getCsettingService() {
		return csettingService;
	}

	public void setCsettingService(CsettingService csettingService) {
		this.csettingService = csettingService;
	}

	public Integer getCsettingid() {
		return csettingid;
	}

	public void setCsettingid(Integer csettingid) {
		this.csettingid = csettingid;
	}

	public Csetting getCsetting() {
		return csetting;
	}

	public void setCsetting(Csetting csetting) {
		this.csetting = csetting;
	}

	public CsettingDetailView getCsettingDetailView() {
		return csettingDetailView;
	}

	public void setCsettingDetailView(CsettingDetailView csettingDetailView) {
		this.csettingDetailView = csettingDetailView;
	}

	public List<CsettingListView> getCsettings() {
		return csettings;
	}

	public void setCsettings(List<CsettingListView> csettings) {
		this.csettings = csettings;
	}

	public CsettingSearchMeta getCsettingSearchMeta() {
		return csettingSearchMeta;
	}

	public void setCsettingSearchMeta(CsettingSearchMeta csettingSearchMeta) {
		this.csettingSearchMeta = csettingSearchMeta;
	}

	public CsettingPageResult getCsettingPageResult() {
		return csettingPageResult;
	}

	public void setCsettingPageResult(CsettingPageResult csettingPageResult) {
		this.csettingPageResult = csettingPageResult;
	}

	public CcategoryService getCcategoryService() {
		return ccategoryService;
	}

	public void setCcategoryService(CcategoryService ccategoryService) {
		this.ccategoryService = ccategoryService;
	}

	public List<Ccategory> getCcategories() {
		return ccategories;
	}

	public void setCcategories(List<Ccategory> ccategories) {
		this.ccategories = ccategories;
	}

	public List<Integer> getCsettingId() {
		return csettingId;
	}

	public void setCsettingId(List<Integer> csettingId) {
		this.csettingId = csettingId;
	}

	public CsettingDeleteResult getCsettingDeleteResult() {
		return csettingDeleteResult;
	}

	public void setCsettingDeleteResult(
			CsettingDeleteResult csettingDeleteResult) {
		this.csettingDeleteResult = csettingDeleteResult;
	}

}
