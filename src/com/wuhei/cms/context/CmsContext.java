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
	 * ����ģʽ
	 */

	private static CmsContext CmsContext = new CmsContext();

	public static final CmsContext getEveContext() {
		return CmsContext.CmsContext;
	}

	/**
	 * ��ȡclassInfo��Ŀ���ò���
	 */
	public static CmsConfigs getCmsConfigs() {
		return CmsConfigs.getCmsConfigs();
	}

	/**
	 * Ĭ��δ��ʼ�� ���δ�ܳɹ���ʼ������ô���ܾ���Ӧ�û����κ�����
	 * 
	 */
	private static Boolean isInitialised = false;

	/**
	 * ϵͳ��ǰ���л������ݣ�ѧУ�б� < Integer, University>: < University.id, University>
	 */
	private static ConcurrentHashMap<Integer, University> universityMap = new ConcurrentHashMap<Integer, University>();

	/**
	 * ϵͳ��ǰ���л������ݣ�Ժϵ�б�
	 * 
	 * < Integer, < Integer, Department>>:< University.id, < Department.id,
	 * Department>>
	 */
	private static ConcurrentHashMap<Integer, ConcurrentHashMap<Integer, Department>> departmentMap = new ConcurrentHashMap<Integer, ConcurrentHashMap<Integer, Department>>();

	/**
	 * ϵͳ��ǰ���л������ݣ�רҵ�б� < Integer, < Integer, Major>>:< Department.id, <
	 * Major.id, Major>>
	 */
	private static ConcurrentHashMap<Integer, ConcurrentHashMap<Integer, Major>> majorMap = new ConcurrentHashMap<Integer, ConcurrentHashMap<Integer, Major>>();

	/**
	 * ϵͳ��ǰ���л������ݣ�רҵ�γ̣��ֳƿγ����ã����� < Integer, Ccategory>:< Ccategory.id,
	 * Ccategory>
	 */
	private static ConcurrentHashMap<Integer, Ccategory> ccategoryMap = new ConcurrentHashMap<Integer, Ccategory>();

	/**
	 * ϵͳ��ǰ���л������ݣ�רҵ�γ��б� < Integer, < Integer, Csetting>>:< Major.id, <
	 * Csetting.id, Csetting>>
	 */
	private static ConcurrentHashMap<Integer, ConcurrentHashMap<Integer, Csetting>> csettingMap = new ConcurrentHashMap<Integer, ConcurrentHashMap<Integer, Csetting>>();


	/**
	 * ϵͳ��ǰѧ���б�
	 */
	private static List<String>years=new ArrayList<String>(); 
	
	/**
	 * ϵͳ��ǰѧ��
	 */
	private static String year;
	
	/**
	 * ϵͳ��ǰѧ��
	 */
	private static String term;
	
	
	/**
	 * Spring��context
	 */
	private static ApplicationContext applicationContext = new FileSystemXmlApplicationContext(
			"classpath:configs/spring/application-context.xml");

	
	/**
	 * ��ʼ��classInfo��Ŀ������
	 */
	public static void initialise(Properties prop) {
		// TODO��classInfo��Ŀ�ĳ�ʼ��
		// TODO �����ݿ��ж�ȡ�������ݣ��������������currentUniversityMap��
		// TODO ���У�ConcurrentHashMap��ʵ�����̰߳�ȫ��
		// TODO ConcurrentHashMap�е�key:String��Ϊ��Ӧ���ݿ���������Integer��

		try {


			log.info("��ʼ��ʼ��classInfo��Ŀ��������");

			if (!isInitialised) {

				// �����û��ʼ�������г�ʼ����ȷ������ʼ��һ��
				// ��ȡ��Ӧ��serviceImpl����Mapperʵ�ֶ����ݿ�ķ���

				// ����ѧУuniversityMap
				UniversityDAO universityDAO = (UniversityDAO) applicationContext
						.getBean("universityDAO");
				List<University> universityListAll = universityDAO
						.getUniversityList();

				for (int i = 0; i < universityListAll.size(); i++) {
					universityMap.put(universityListAll.get(i).getId(),
							universityListAll.get(i));
				}

				//���ϵͳ��ǰ���ڣ�
			        Calendar c=Calendar.getInstance();//���ϵͳ��ǰ����
			        int annum =c.get(Calendar.YEAR);
			        int month=c.get(Calendar.MONTH)+1;//ϵͳ���ڴ�0��ʼ����
			        int day=c.get(Calendar.DAY_OF_MONTH);
			     //���ϵͳ��ǰѧ��   
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
			        
			     //���ϵͳ��ǰѧ���б�
			    annum=annum-3;
			    for(int i=0;i<7;i++)
			    {
			    	years.add(annum+"-"+(annum+1));
			    	annum++;
			    }
			    
			    // ����ѧԺdepartmentMap
				// ���ĳѧУ����û��ѧԺ�б���(key=��ѧУ��id,value=һ��û�м�¼����map)��
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

				// ����רҵmajoyMap
				// ���ĳѧԺ����û��רҵ�б���(key=��ѧԺ��id,value=һ��û�м�¼����map)
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

				// ���ÿγ���� ccategoryMap
				CcategoryDAO ccategoryDAO = (CcategoryDAO) applicationContext
						.getBean("ccategoryDAO");
				List<Ccategory> ccategoryListAll = ccategoryDAO
						.getCcategoryList();
				for (int i = 0; i < ccategoryListAll.size(); i++) {
					ccategoryMap.put(ccategoryListAll.get(i).getId(),
							ccategoryListAll.get(i));
				}

				// ����רҵ�γ�csettingMap
				// ���ĳרҵ����û�пγ������б���(key=��רҵ��id,value=һ��û�м�¼����map)
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

				// ���ó�ʼ����־
				isInitialised = true;
			}

		} catch (Exception e) {
			System.out.println(e);
			log.info("��ʼ��classInfo��Ŀ��������ʧ��");
			log.debug(e.getMessage());
		}
	}
	
	/**
	 * ��ȡϵͳ��ǰѧ��
	 * 
	 * @param 
	 * @return
	 */
	public static final String getCurrentYear() {
		return year;
	}
	
	
	
	/**
	 * ��ȡϵͳ��ǰѧ��
	 * 
	 * @param 
	 * @return
	 */
	public static final String getCurrentTerm() {
		return term;
	}
	
	/**
	 * ��ȡϵͳ��ǰѧ���б�
	 * 
	 * @param 
	 * @return
	 */
	public static final List<String> getCurrentYearList() {
		return years;
	}
	
	/**
	 * ��ȡѧУ
	 * 
	 * @param universityId
	 * @return
	 */
	public static final University getUniversity(Integer universityId) {
		return universityMap.get(universityId);
	}

	/**
	 * ��ȡѧУ�µ�Ժϵ
	 * 
	 * @param universityId
	 * @return
	 */
	public static final Department getDepartment(Integer universityId,
			Integer departmentId) {
		return departmentMap.get(universityId).get(departmentId);
	}

	/**
	 * ��ȡԺϵ�µ�רҵ
	 * 
	 * @param universityId
	 * @return
	 */
	public static final Major getMajor(Integer departmentId, Integer majorId) {
		return majorMap.get(departmentId).get(majorId);
	}

	/**
	 * ��ȡרҵ�γ�
	 * 
	 * @param majorId
	 *            רҵ������
	 * @param csettingId
	 *            רҵ�γ̵�����
	 * @return רҵ�γ�
	 */
	public static final Csetting getCsetting(Integer majorId, Integer csettingId) {
		return csettingMap.get(majorId).get(csettingId);
	}

	public static final ConcurrentHashMap<Integer, Csetting> getCsettingMap(
			Integer majorId) {
		return (ConcurrentHashMap) csettingMap.get(majorId);
	}

	/**
	 * ��ȡ�γ����
	 */
	public static final ConcurrentHashMap<Integer, Ccategory> getCcategoryMap() {
		return ccategoryMap;
	}

	
	
	
	/**
	 * 
	 * ���ظ�ѧԺ������רҵMap
	 * 
	 * @param departmentid
	 * @return
	 */
	public static final ConcurrentHashMap<Integer, Major> getMajorMapByDepartmentid(
			Integer departmentid) {
		return majorMap.get(departmentid);
	}

}
