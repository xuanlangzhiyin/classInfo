package com.wuhei.cms.context;


import java.util.ArrayList;
import java.util.Calendar;

import java.util.List;

import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.wuhei.cms.dao.basic.CcategoryDAO;
import com.wuhei.cms.dao.basic.CsettingDAO;
import com.wuhei.cms.dao.basic.DepartmentDAO;

import com.wuhei.cms.dao.basic.MajorDAO;
import com.wuhei.cms.dao.basic.UniversityDAO;
import com.wuhei.cms.model.Ccategory;
import com.wuhei.cms.model.Csetting;
import com.wuhei.cms.model.Department;
import com.wuhei.cms.model.Major;

import com.wuhei.cms.model.University;


public class CmsContext {

	private static Logger log = Logger.getLogger(CmsContext.class);

	/**
	 * 单例模式
	 */

	private static CmsContext CmsContext = new CmsContext();

	public static final CmsContext getEveContext() {
		return CmsContext.CmsContext;
	}

	/**
	 * 获取classInfo项目配置参数
	 */
	public static CmsConfigs getCmsConfigs() {
		return CmsConfigs.getCmsConfigs();
	}

	/**
	 * 默认未初始化 如果未能成功初始化，那么将拒绝响应用户的任何请求
	 * 
	 */
	private static Boolean isInitialised = false;

	/**
	 * 系统当前运行基础数据：学校列表 < Integer, University>: < University.id, University>
	 */
	private static ConcurrentHashMap<Integer, University> universityMap = new ConcurrentHashMap<Integer, University>();

	/**
	 * 系统当前运行基础数据：院系列表
	 * 
	 * < Integer, < Integer, Department>>:< University.id, < Department.id,
	 * Department>>
	 */
	private static ConcurrentHashMap<Integer, ConcurrentHashMap<Integer, Department>> departmentMap = new ConcurrentHashMap<Integer, ConcurrentHashMap<Integer, Department>>();

	/**
	 * 系统当前运行基础数据：专业列表 < Integer, < Integer, Major>>:< Department.id, <
	 * Major.id, Major>>
	 */
	private static ConcurrentHashMap<Integer, ConcurrentHashMap<Integer, Major>> majorMap = new ConcurrentHashMap<Integer, ConcurrentHashMap<Integer, Major>>();

	/**
	 * 系统当前运行基础数据：专业课程（又称课程设置）类型 < Integer, Ccategory>:< Ccategory.id,
	 * Ccategory>
	 */
	private static ConcurrentHashMap<Integer, Ccategory> ccategoryMap = new ConcurrentHashMap<Integer, Ccategory>();

	/**
	 * 系统当前运行基础数据：专业课程列表 < Integer, < Integer, Csetting>>:< Major.id, <
	 * Csetting.id, Csetting>>
	 */
	private static ConcurrentHashMap<Integer, ConcurrentHashMap<Integer, Csetting>> csettingMap = new ConcurrentHashMap<Integer, ConcurrentHashMap<Integer, Csetting>>();


	/**
	 * 系统当前学年列表
	 */
	private static List<String>years=new ArrayList<String>(); 
	
	/**
	 * 系统当前学年
	 */
	private static String year;
	
	/**
	 * 系统当前学期
	 */
	private static String term;
	
	
	/**
	 * Spring的context
	 */
	private static ApplicationContext applicationContext = new FileSystemXmlApplicationContext(
			"classpath:configs/spring/application-context.xml");

	
	/**
	 * 初始化classInfo项目上下文
	 */
	public static void initialise(Properties prop) {
		// TODO　classInfo项目的初始化
		// TODO 从数据库中读取基础数据，构造上述定义的currentUniversityMap等
		// TODO 其中，ConcurrentHashMap的实现是线程安全的
		// TODO ConcurrentHashMap中的key:String均为对应数据库表的主键（Integer）

		try {


			log.info("开始初始化classInfo项目基础数据");

			if (!isInitialised) {

				// 如果还没初始化，进行初始化；确保仅初始化一次
				// 获取相应的serviceImpl或者Mapper实现对数据库的访问

				// 设置学校universityMap
				UniversityDAO universityDAO = (UniversityDAO) applicationContext
						.getBean("universityDAO");
				List<University> universityListAll = universityDAO
						.getUniversityList();

				for (int i = 0; i < universityListAll.size(); i++) {
					universityMap.put(universityListAll.get(i).getId(),
							universityListAll.get(i));
				}

				//获得系统当前日期：
			        Calendar c=Calendar.getInstance();//获得系统当前日期
			        int annum =c.get(Calendar.YEAR);
			        int month=c.get(Calendar.MONTH)+1;//系统日期从0开始算起
			        int day=c.get(Calendar.DAY_OF_MONTH);
			     //获得系统当前学期   
			        if(month<9)
			        {
			        	year=(annum-1)+"-"+annum;
			        	term="2";
	     	        }
			        else
			        {
			        	year=annum+"-"+(annum+1);
			        	term="1";
	     	        }
			        
			     //获得系统当前学期列表
			    annum=annum-3;
			    for(int i=0;i<7;i++)
			    {
			    	years.add(annum+"-"+(annum+1));
			    	annum++;
			    }
			    
			    // 设置学院departmentMap
				// 如果某学校下面没有学院列表，则(key=该学校的id,value=一个没有记录的子map)。
				DepartmentDAO departmentDAO = (DepartmentDAO) applicationContext
						.getBean("departmentDAO");
				for (int i = 0; i < universityListAll.size(); i++) {
					List<Department> departmentList = departmentDAO
							.getDepartmentListByUniversityId(universityListAll
									.get(i).getId());
					ConcurrentHashMap<Integer, Department> departmentMap4University = new ConcurrentHashMap<Integer, Department>();
					for (int j = 0; j < departmentList.size(); j++) {
						departmentMap4University.put(departmentList.get(j)
								.getId(), departmentList.get(j));
					}
					departmentMap.put(universityListAll.get(i).getId(),
							departmentMap4University);
				}

				// 设置专业majoyMap
				// 如果某学院下面没有专业列表，则(key=该学院的id,value=一个没有记录的子map)
				MajorDAO majorDAO = (MajorDAO) applicationContext
						.getBean("majorDAO");
				List<Department> departmentListAll = departmentDAO
						.getDepartmentList();
				for (int i = 0; i < departmentListAll.size(); i++) {
					List<Major> majorList = majorDAO
							.getMajorListByDepartmentId(departmentListAll
									.get(i).getId());
					ConcurrentHashMap<Integer, Major> majorMap4Department = new ConcurrentHashMap<Integer, Major>();
					for (int j = 0; j < majorList.size(); j++) {
						majorMap4Department.put(majorList.get(j).getId(),
								majorList.get(j));
					}
					majorMap.put(departmentListAll.get(i).getId(),
							majorMap4Department);
				}

				// 设置课程类别 ccategoryMap
				CcategoryDAO ccategoryDAO = (CcategoryDAO) applicationContext
						.getBean("ccategoryDAO");
				List<Ccategory> ccategoryListAll = ccategoryDAO
						.getCcategoryList();
				for (int i = 0; i < ccategoryListAll.size(); i++) {
					ccategoryMap.put(ccategoryListAll.get(i).getId(),
							ccategoryListAll.get(i));
				}

				// 设置专业课程csettingMap
				// 如果某专业下面没有课程设置列表，则(key=该专业的id,value=一个没有记录的子map)
				CsettingDAO csettingDAO = (CsettingDAO) applicationContext
						.getBean("csettingDAO");
				List<Major> majorListAll = majorDAO.getMajorList();
				for (int i = 0; i < majorListAll.size(); i++) {
					ConcurrentHashMap<Integer, Csetting> csettingMap4Major = new ConcurrentHashMap<Integer, Csetting>();
					List<Csetting> csettingList = csettingDAO
							.getCsettingListByMajorId(majorListAll.get(i)
									.getId());
					for (int j = 0; j < csettingList.size(); j++) {
						csettingMap4Major.put(csettingList.get(j).getId(),
								csettingList.get(j));
					}
					csettingMap.put(majorListAll.get(i).getId(),
							csettingMap4Major);
				}

				// 设置初始化标志
				isInitialised = true;
			}

		} catch (Exception e) {
			System.out.println(e);
			log.info("初始化classInfo项目基础数据失败");
			log.debug(e.getMessage());
		}
	}
	
	/**
	 * 获取系统当前学年
	 * 
	 * @param 
	 * @return
	 */
	public static final String getCurrentYear() {
		return year;
	}
	
	
	
	/**
	 * 获取系统当前学期
	 * 
	 * @param 
	 * @return
	 */
	public static final String getCurrentTerm() {
		return term;
	}
	
	/**
	 * 获取系统当前学年列表
	 * 
	 * @param 
	 * @return
	 */
	public static final List<String> getCurrentYearList() {
		return years;
	}
	
	/**
	 * 获取学校
	 * 
	 * @param universityId
	 * @return
	 */
	public static final University getUniversity(Integer universityId) {
		return universityMap.get(universityId);
	}

	/**
	 * 获取学校下的院系
	 * 
	 * @param universityId
	 * @return
	 */
	public static final Department getDepartment(Integer universityId,
			Integer departmentId) {
		return departmentMap.get(universityId).get(departmentId);
	}

	/**
	 * 获取院系下的专业
	 * 
	 * @param universityId
	 * @return
	 */
	public static final Major getMajor(Integer departmentId, Integer majorId) {
		return majorMap.get(departmentId).get(majorId);
	}

	/**
	 * 获取专业课程
	 * 
	 * @param majorId
	 *            专业的主键
	 * @param csettingId
	 *            专业课程的主键
	 * @return 专业课程
	 */
	public static final Csetting getCsetting(Integer majorId, Integer csettingId) {
		return csettingMap.get(majorId).get(csettingId);
	}

	public static final ConcurrentHashMap<Integer, Csetting> getCsettingMap(
			Integer majorId) {
		return (ConcurrentHashMap) csettingMap.get(majorId);
	}

	/**
	 * 获取课程类别
	 */
	public static final ConcurrentHashMap<Integer, Ccategory> getCcategoryMap() {
		return ccategoryMap;
	}

	
	
	
	/**
	 * 
	 * 返回该学院的所有专业Map
	 * 
	 * @param departmentid
	 * @return
	 */
	public static final ConcurrentHashMap<Integer, Major> getMajorMapByDepartmentid(
			Integer departmentid) {
		return majorMap.get(departmentid);
	}

}
