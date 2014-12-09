package com.wuhei.cms.service.cactivities.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wuhei.cms.dao.basic.CsettingDAO;
import com.wuhei.cms.dao.basic.StudentDAO;
import com.wuhei.cms.dao.cactivities.CgroupDAO;
import com.wuhei.cms.dao.cactivities.CmstudentDAO;
import com.wuhei.cms.dao.cactivities.CourseDAO;
import com.wuhei.cms.dao.cactivities.CstudentDAO;
import com.wuhei.cms.dao.cactivities.CstudentDetailViewDAO;
import com.wuhei.cms.dao.cactivities.CstudentListViewDAO;
import com.wuhei.cms.model.Cgroup;
import com.wuhei.cms.model.Cmstudent;
import com.wuhei.cms.model.Cstudent;
import com.wuhei.cms.model.deletedata.CstudentDeleteResult;
import com.wuhei.cms.model.joint.CstudentDetailView;
import com.wuhei.cms.model.joint.CstudentListView;
import com.wuhei.cms.service.cactivities.CstudentService;

public class CstudentServiceImpl implements CstudentService {
	private CstudentDAO cstudentDAO;
	private CstudentDetailViewDAO cstudentDetailViewDAO;
	private CstudentListViewDAO cstudentListViewDAO;
	private CourseDAO courseDAO;
	private StudentDAO studentDAO;

	private CsettingDAO csettingDAO;
	/**
	 * ʵ��ɾ��ѡ��ѧ��
	 */
	private CmstudentDAO cmstudentDAO;

	private CgroupDAO cgroupDAO;

	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public Cstudent getCstudentById(Integer id) {
		return cstudentDAO.getCstudent(id);
	}
	
	public List<Cstudent> getcstudentByCondition(Integer studentid, Integer courseid)
	{
		return cstudentDAO.getCStudentListByCondition(null, courseid, null, studentid);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public CstudentDetailView getCstudentDetailViewById(Integer id) {
		return cstudentDetailViewDAO.getCstudentDetailViewById(id);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void newCstudent(Cstudent cstudent) {
		cstudentDAO.insertCstudent(cstudent);
	}



	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void deleteCstudent(Integer cstudentid) {
		cstudentDAO.deleteCstudent(cstudentid);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public List<Cstudent> getCstudentList(int courseid) {
		return cstudentDAO.getCStudentListByCondition(null, courseid, null,
				null);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public List<CstudentDetailView> getCstudentDetailViewList(int courseid) {
		List<CstudentDetailView> cstudentDetailView = cstudentDetailViewDAO
				.listCstudentDetailViewByCourseid(courseid);
		for (int i = 0; i < cstudentDetailView.size(); i++) {
			cstudentDetailView.get(i).setIsEvaluation(
					cstudentDetailView.get(i).getIsevaluated() ? "��" : "��");
		}
		return cstudentDetailView;
	}
	

	public List<CstudentListView> getCstudentListView(int courseid)
	{
		List<CstudentListView> cstudentListViews=cstudentListViewDAO.getCstudentListViewByCourseid(courseid);
		for (int i = 0; i < cstudentListViews.size(); i++) {
			cstudentListViews.get(i).setIsEvaluation(
					cstudentListViews.get(i).getIsevaluated() ? "��" : "��");
		}
		return cstudentListViews;
	}

	   
	   @Override
		@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	   public void updateCstudent(Cstudent cstudent)
	   {
		   cstudentDAO.updateCstudent(cstudent);
	   }
	
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public CstudentDeleteResult deleteCstudentByCondition(
			List<Integer> sourceCstudentid) {

		// ���췵�ؽ��
		CstudentDeleteResult cstudentDeleteResult = new CstudentDeleteResult();
		Integer successCount = Integer.valueOf(0);
		Integer errorCount = Integer.valueOf(0);
		// ɾ�����ɹ�ѡ��ѧ���б�
		List<CstudentDetailView> errorCstudentDetailViews = new ArrayList<CstudentDetailView>();

		for (int i = 0; i < sourceCstudentid.size(); i++) {
			// ȡһ��cstudentid
			Integer cstudentid = sourceCstudentid.get(i);
			// ����cstudentid���ݿ��е�cstudentDetailView
			CstudentDetailView cstudentDetailView = new CstudentDetailView();
			try {

				// ����cstudentid���ݿ��е�cstudentDetailView������try������Ϊ�˷�ֹ�û����⴫�������ڵ�id�������ݿ����
				cstudentDetailView = cstudentDetailViewDAO
						.getCstudentDetailViewById(cstudentid);

				if (cstudentDetailView == null) {
					continue;
				}

				// ���ݿ���cmstudent���ж���cmstudent���ڣ��򷵻�ɾ��ʧ��
				List<Cmstudent> cmstudent = new ArrayList<Cmstudent>();
				cmstudent = cmstudentDAO.getCmstudentListByCondition(null,
						null, null, cstudentid);
				if (!cmstudent.isEmpty()) {
					errorCount++;
					errorCstudentDetailViews.add(cstudentDetailView);
					continue;
				}

				// �ж�cstudent�Ƿ����
				if (cstudentDetailView.getIsgrouped().equals(true)) {
					// cstudent�ѷ��飬����cgroup�������ݿ�������
					Cgroup cgroup = new Cgroup();
					cgroup = cgroupDAO.getCgroup(cstudentDetailView
							.getCgroupid());

					// �ж�cstudent�Ƿ�ΪС���鳤
					if (cgroup.getLeaderid().equals(cstudentDetailView.getId())) {
						// ΪС���鳤
						try {
							cstudentDAO.deleteCstudent(cstudentid); // ɾ��cstudent��cgroup���е�leaderid����Ϊset
																	// null
							// �����µ�С���Ա������Ϊԭ�е�������һ
							short newCount = (short) (cgroup.getCount() - 1);
							if (newCount == 0) {
								// ԭ��С��ֻ��һ���ˣ�Ϊcstudent������ʱӦ��ɾ������
								cgroupDAO.deleteCgroup(cstudentDetailView
										.getCgroupid());
								successCount++;
								continue;
							} else
							// ԭ��С�鲻ֻһ���ˣ�ɾ��cstudent����Ӧ�ñ��ַ���
							{
								cgroup.setCount(newCount);
								// ����һ���µ�cstudentΪ�鳤
								List<Cstudent> leftCstudent = new ArrayList<Cstudent>();
								leftCstudent = cstudentDAO
										.getCStudentListByCondition(true,
												cstudentDetailView
														.getCourseid(),
												cstudentDetailView
														.getCgroupid(), null);
								// Ĭ��ѡ��ȥ��һ��cstudent��Ϊ�鳤
								cgroup.setLeaderid(leftCstudent.get(0).getId());
								cgroupDAO.updateCgroup(cgroup);
								successCount++;
								continue;
							}
						} catch (Exception e) {
							// ����ɾ��cgroup���߸���cgroup�쳣
							System.out.println(e);
							errorCount++;
							errorCstudentDetailViews.add(cstudentDetailView);
							continue;
						}
					}// �����鳤
					try {
						cstudentDAO.deleteCstudent(cstudentid);
						// �����µ�С���Ա������Ϊԭ�е�������һ
						short newCount = (short) (cgroup.getCount() - 1);
						cgroup.setCount(newCount);
						cgroupDAO.updateCgroup(cgroup);
						successCount++;
						continue;
					} catch (Exception e) {
						System.out.println(e);
						errorCount++;
						errorCstudentDetailViews.add(cstudentDetailView);
						continue;
					}
				}// δ����
				try {
					cstudentDAO.deleteCstudent(cstudentid);
					successCount++;
					continue;
				} catch (Exception e) {
					System.out.println(e);
					errorCount++;
					errorCstudentDetailViews.add(cstudentDetailView);
					continue;
				}
			} catch (Exception e) {
				System.out.println(e);
				errorCount++;
				errorCstudentDetailViews.add(cstudentDetailView);
				continue;
			}
		}
		cstudentDeleteResult.setErrorCount(errorCount);
		cstudentDeleteResult.setSuccessCount(successCount);
		cstudentDeleteResult
				.setErrorCstudentDetailViews(errorCstudentDetailViews);

		return cstudentDeleteResult;

	}

	/**
	 * getter and setter
	 */
	public CstudentDAO getCstudentDAO() {
		return cstudentDAO;
	}

	public void setCstudentDAO(CstudentDAO cstudentDAO) {
		this.cstudentDAO = cstudentDAO;
	}

	public CstudentDetailViewDAO getCstudentDetailViewDAO() {
		return cstudentDetailViewDAO;
	}

	public void setCstudentDetailViewDAO(
			CstudentDetailViewDAO cstudentDetailViewDAO) {
		this.cstudentDetailViewDAO = cstudentDetailViewDAO;
	}

	public CourseDAO getCourseDAO() {
		return courseDAO;
	}

	public void setCourseDAO(CourseDAO courseDAO) {
		this.courseDAO = courseDAO;
	}

	public StudentDAO getStudentDAO() {
		return studentDAO;
	}

	public void setStudentDAO(StudentDAO studentDAO) {
		this.studentDAO = studentDAO;
	}

	public CmstudentDAO getCmstudentDAO() {
		return cmstudentDAO;
	}

	public void setCmstudentDAO(CmstudentDAO cmstudentDAO) {
		this.cmstudentDAO = cmstudentDAO;
	}

	public CgroupDAO getCgroupDAO() {
		return cgroupDAO;
	}

	public void setCgroupDAO(CgroupDAO cgroupDAO) {
		this.cgroupDAO = cgroupDAO;
	}

	public CsettingDAO getCsettingDAO() {
		return csettingDAO;
	}

	public void setCsettingDAO(CsettingDAO csettingDAO) {
		this.csettingDAO = csettingDAO;
	}

	public CstudentListViewDAO getCstudentListViewDAO() {
		return cstudentListViewDAO;
	}

	public void setCstudentListViewDAO(CstudentListViewDAO cstudentListViewDAO) {
		this.cstudentListViewDAO = cstudentListViewDAO;
	}
	
	

}