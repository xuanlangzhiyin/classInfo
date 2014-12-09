package com.wuhei.cms.action;

import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.Action;

/**
 * Action������
 * 
 * 
 */


@SuppressWarnings("serial")
public class BaseAction implements Action,  SessionAware,
		ServletRequestAware, Serializable {

	/**
	 * Ĭ�Ϲ��캯��
	 */
	public BaseAction() {
		super();
	}

	@Override
	/**
	 * Action
	 */
	public String execute() throws Exception {
		return SUCCESS;
	}

	public String defaultForward() {
		return SUCCESS;
	}

	public String comingFunction() {
		return SUCCESS;
	}

	/**
	 * ajax������Ҫ�ķ��ؽ��
	 * 
	 * �ɹ�����success
	 * 
	 * ʧ�ܷ���error
	 */
	protected String ajaxResult;

	/**
	 * ��ʱûʹ��
	 * 
	 * HttpSession: auto set by 'SessionAware'�ӿ�����
	 * 
	 * Ϊ��ʹ��action��servlet����
	 */
	protected Map<String, Object> session;

	/**
	 * ��ʱûʹ��
	 * 
	 * HttpServletRequest, auto set by 'ServletRequestAware'
	 * 
	 * Ϊ��ʹ��action��servlet����
	 */
	protected HttpServletRequest request;

	

	/**
	 * �������ΪEmpty����Blank������ΪĬ��ֵ
	 * 
	 * @param value
	 *            ����ֵ
	 * @param defaultValue
	 *            Ĭ��ֵ
	 * @return
	 */
	protected String getDefaultSearchValue(String value, String defaultValue) {
		if (StringUtils.isEmpty(value) || StringUtils.isBlank(value))
			return defaultValue;
		return value;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	

	/*
	 * classInfo ��Ŀ�ļ�����
	 */

	/**
	 * ҳ�������ļ�����
	 * 
	 * ���ﶨ��ı���,һ��Ҫ��JSPҳ���<input type="file" name="file">�е�name���Ե�ֵһ��.
	 * �����ҳ�ж������<input type="file" name="img">,��ô�������Ҫ����File img;
	 */
	protected File file;

	/**
	 * ҳ�������ļ�����-�ļ���
	 * 
	 * ���ﶨ���fileFileNameһ��Ҫ��xxxFileName����ʽ, �����޷�ȡ���ļ����ļ���.
	 * ����xxx���������涨���File���͵ı���һ��, ������涨�����File img,��ô����� ����ΪString imgFileName
	 */
	protected String fileFileName;

	/**
	 * 
	 * ���ﶨ������ļ�������,�������Ҫ��ȡ�ļ����͵Ļ�,���Բ�����.
	 * ���������xxxFileName����,����һ��Ҫ�����xxxContentType��ʽ.
	 */
	protected String fileContentType;

	/**
	 * �����ļ���·��
	 */
	protected String downloadFilePath;

	/**
	 * �����ļ���
	 */
	protected InputStream downloadFile;

	protected String templatePath = File.separator + "file" + File.separator
			+ "template" + File.separator;

	/**
	 * �������������������ļ���
	 */
	protected String newFileName;

	/**
	 * action download�У�<param name="inputName">downloadFile</param>
	 * �����Ҫ�����pulic getDownloadFile ����java reflection���ƣ��������ͱ�����public
	 * http://blog.csdn.net/csh624366188/article/details/6690450
	 * 
	 * @return
	 */
	public InputStream getDownloadFile() throws Exception {
		downloadFile = ServletActionContext.getServletContext()
				.getResourceAsStream(downloadFilePath);
		return downloadFile;
	}

	/*
	 * getters and setters
	 */

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	public String getNewFileName() {
		return newFileName;
	}

	public void setNewFileName(String newFileName) {
		this.newFileName = newFileName;
	}

	public String getTemplatePath() {
		return templatePath;
	}

	public void setTemplatePath(String templatePath) {
		this.templatePath = templatePath;
	}

	public String getDownloadFilePath() {
		return downloadFilePath;
	}

	public void setDownloadFilePath(String downloadFilePath) {
		this.downloadFilePath = downloadFilePath;
	}

	public String getAjaxResult() {
		return ajaxResult;
	}

	public void setAjaxResult(String ajaxResult) {
		this.ajaxResult = ajaxResult;
	}

}
