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
	 * 实现删除选课学生
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
					cstudentDetailView.get(i).getIsevaluated() ? "是" : "否");
		}
		return cstudentDetailView;
	}
	

	public List<CstudentListView> getCstudentListView(int courseid)
	{
		List<CstudentListView> cstudentListViews=cstudentListViewDAO.getCstudentListViewByCourseid(courseid);
		for (int i = 0; i < cstudentListViews.size(); i++) {
			cstudentListViews.get(i).setIsEvaluation(
					cstudentListViews.get(i).getIsevaluated() ? "是" : "否");
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

		// 构造返回结果
		CstudentDeleteResult cstudentDeleteResult = new CstudentDeleteResult();
		Integer successCount = Integer.valueOf(0);
		Integer errorCount = Integer.valueOf(0);
		// 删除不成功选课学生列表
		List<CstudentDetailView> errorCstudentDetailViews = new ArrayList<CstudentDetailView>();

		for (int i = 0; i < sourceCstudentid.size(); i++) {
			// 取一个cstudentid
			Integer cstudentid = sourceCstudentid.get(i);
			// 根据cstudentid数据库中的cstudentDetailView
			CstudentDetailView cstudentDetailView = new CstudentDetailView();
			try {

				// 根据cstudentid数据库中的cstudentDetailView，放在try里面是为了防止用户恶意传来不存在的id导致数据库崩溃
				cstudentDetailView = cstudentDetailViewDAO
						.getCstudentDetailViewById(cstudentid);

				if (cstudentDetailView == null) {
					continue;
				}

				// 数据库中cmstudent，判断若cmstudent存在，则返回删除失败
				List<Cmstudent> cmstudent = new ArrayList<Cmstudent>();
				cmstudent = cmstudentDAO.getCmstudentListByCondition(null,
						null, null, cstudentid);
				if (!cmstudent.isEmpty()) {
					errorCount++;
					errorCstudentDetailViews.add(cstudentDetailView);
					continue;
				}

				// 判断cstudent是否分组
				if (cstudentDetailView.getIsgrouped().equals(true)) {
					// cstudent已分组，创建cgroup载入数据库分组对象
					Cgroup cgroup = new Cgroup();
					cgroup = cgroupDAO.getCgroup(cstudentDetailView
							.getCgroupid());

					// 判断cstudent是否为小组组长
					if (cgroup.getLeaderid().equals(cstudentDetailView.getId())) {
						// 为小组组长
						try {
							cstudentDAO.deleteCstudent(cstudentid); // 删除cstudent，cgroup表中掉leaderid设置为set
																	// null
							// 设置新的小组成员数量，为原有的数量减一
							short newCount = (short) (cgroup.getCount() - 1);
							if (newCount == 0) {
								// 原先小组只有一个人，为cstudent本身，此时应该删除分组
								cgroupDAO.deleteCgroup(cstudentDetailView
										.getCgroupid());
								successCount++;
								continue;
							} else
							// 原先小组不只一个人，删除cstudent本身，应该保持分组
							{
								cgroup.setCount(newCount);
								// 设置一个新的cstudent为组长
								List<Cstudent> leftCstudent = new ArrayList<Cstudent>();
								leftCstudent = cstudentDAO
										.getCStudentListByCondition(true,
												cstudentDetailView
														.getCourseid(),
												cstudentDetailView
														.getCgroupid(), null);
								// 默认选上去第一个cstudent作为组长
								cgroup.setLeaderid(leftCstudent.get(0).getId());
								cgroupDAO.updateCgroup(cgroup);
								successCount++;
								continue;
							}
						} catch (Exception e) {
							// 由于删除cgroup或者更新cgroup异常
							System.out.println(e);
							errorCount++;
							errorCstudentDetailViews.add(cstudentDetailView);
							continue;
						}
					}// 不是组长
					try {
						cstudentDAO.deleteCstudent(cstudentid);
						// 设置新的小组成员数量，为原有的数量减一
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
				}// 未分组
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