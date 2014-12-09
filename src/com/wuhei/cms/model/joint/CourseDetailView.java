package com.wuhei.cms.model.joint;

import com.wuhei.cms.model.Course;

/**
 * ����γ���ϸ��ͼ
 * ���ظ���γ���ϸҳ��Ľ��
 * ��ʾ����γ���ϸҳ��ʹ��
 *
 */
public class CourseDetailView extends Course
{
	
	
	//�ڿ���ʦ����
    private String teachername;
    //�γ�����
    private String type;
    //�γ����
    private String ccategoryname;
    //����ѧԺ
    private String courseDepartmentname;

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTeachername() {
		return teachername;
	}
	public void setTeachername(String teachername) {
		this.teachername = teachername;
	}
	public String getCcategoryname() {
		return ccategoryname;
	}
	public void setCcategoryname(String ccategoryname) {
		this.ccategoryname = ccategoryname;
	}
	public String getCourseDepartmentname() {
		return courseDepartmentname;
	}
	public void setCourseDepartmentname(String courseDepartmentname) {
		this.courseDepartmentname = courseDepartmentname;
	}
    
	@Override
	public String toString() {
		//���ø���Ĵ�ӡ����
		String string=super.toString();
		//ȡ�����һ����]��
		string.substring(0,string.length()-1);
		string+=", teachername="+teachername+", type="+type+", ccategoryname="+ccategoryname+", courseDepartmentname="+courseDepartmentname;
		return string;
	}
	
    }
