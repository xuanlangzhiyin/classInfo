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
 * Action｜基类
 * 
 * 
 */


@SuppressWarnings("serial")
public class BaseAction implements Action,  SessionAware,
		ServletRequestAware, Serializable {

	/**
	 * 默认构造函数
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
	 * ajax请求需要的返回结果
	 * 
	 * 成功返回success
	 * 
	 * 失败返回error
	 */
	protected String ajaxResult;

	/**
	 * 暂时没使用
	 * 
	 * HttpSession: auto set by 'SessionAware'接口设置
	 * 
	 * 为了使得action与servlet隔离
	 */
	protected Map<String, Object> session;

	/**
	 * 暂时没使用
	 * 
	 * HttpServletRequest, auto set by 'ServletRequestAware'
	 * 
	 * 为了使得action与servlet隔离
	 */
	protected HttpServletRequest request;

	

	/**
	 * 如果输入为Empty或者Blank，设置为默认值
	 * 
	 * @param value
	 *            输入值
	 * @param defaultValue
	 *            默认值
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
	 * classInfo 项目文件操作
	 */

	/**
	 * 页面输入文件变量
	 * 
	 * 这里定义的变量,一定要跟JSP页面的<input type="file" name="file">中的name属性的值一致.
	 * 如果网页中定义的是<input type="file" name="img">,那么在这里就要定义File img;
	 */
	protected File file;

	/**
	 * 页面输入文件变量-文件名
	 * 
	 * 这里定义的fileFileName一定要是xxxFileName的形式, 否则无法取到文件的文件名.
	 * 其中xxx必须与上面定义的File类型的变量一致, 如果上面定义的是File img,那么这里就 定义为String imgFileName
	 */
	protected String fileFileName;

	/**
	 * 
	 * 这里定义的是文件的类型,如果不需要获取文件类型的话,可以不定义.
	 * 命名规则跟xxxFileName类似,这里一定要定义成xxxContentType形式.
	 */
	protected String fileContentType;

	/**
	 * 下载文件的路径
	 */
	protected String downloadFilePath;

	/**
	 * 下载文件流
	 */
	protected InputStream downloadFile;

	protected String templatePath = File.separator + "file" + File.separator
			+ "template" + File.separator;

	/**
	 * 这个变量是重命名后的文件名
	 */
	protected String newFileName;

	/**
	 * action download中，<param name="inputName">downloadFile</param>
	 * 因此需要定义此pulic getDownloadFile 利用java reflection机制，函数类型必须是public
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
