package com.wuhei.cms.dao.cactivities;

import java.util.List;

import com.wuhei.cms.model.Cteacher;

public interface CteacherDAO 
{
	/**
	 * ����cteacher
	 */
   public void  insertCteacher (Cteacher cteacher);
   
   /**
    * ͨ��courseid����cteacher
    */
   public void updateCteacherByCourseid (Cteacher cteacher);
   
   /**
    *ͨ��courseidɾ��ѡ��cteacher
    */
   public void deleteCteacherByCourseid (Integer courseid);
   
   /**
    * ͨ��courseid��ȡ���ν�ʦ�б�
    */
   public List<Cteacher> getCteacherByCourseid(Integer courseid);
   

   /**
    * ͨ��teacherid��ȡ����cteacher���еĸ���
    */
   public int countCteacherByTeacherid(Integer teacherid);
   
   /**
    * �ҳ���ǰ��ѯ�γ̵Ŀ�����ʦid
    */
  
   public List<Integer> getTeacheridsByCourseid(Integer courseid);
   
   
}
