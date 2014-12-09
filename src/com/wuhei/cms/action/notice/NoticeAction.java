package com.wuhei.cms.action.notice;

import java.io.File;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.wuhei.cms.action.BaseAction;
import com.wuhei.cms.fileprocessing.FileHelper;
import com.wuhei.cms.fileprocessing.FileType;
import com.wuhei.cms.fileprocessing.FileTypeDetector;
import com.wuhei.cms.login.LoginUser;
import com.wuhei.cms.model.Notice;
import com.wuhei.cms.search.NoticeSearchMeta;
import com.wuhei.cms.search.result.NoticePageResult;
import com.wuhei.cms.service.notice.NoticeService;
import com.wuhei.cms.web.context.CmsWebContext;

/**
 * 通知管理|基类
 * 
 * 暂时使用最简单模型： 1. 仅允许教务员发布通知 2. 发布通知指定：面向教师｜面向学生｜面向全体
 * 
 * 
 */
@SuppressWarnings("serial")
public class NoticeAction extends BaseAction {

	protected final static Logger log = Logger.getLogger(NoticeAction.class);

	protected NoticeService noticeService;

	/**
	 * 存储通知附件的路径 classInfo/file/notice
	 */
	private String noticePath = File.separator + "file" + File.separator
			+ "notice" + File.separator;

	/**
	 * 页面输入参数－通知id
	 */
	protected Integer noticeid;

	/**
	 * 页面输入参数：通知对象
	 */
	protected Integer readerid;

	/**
	 * 页面输入参数－通知
	 */
	protected Notice notice;

	/**
	 * 通知列表 前台分页
	 */
	protected List<Notice> notices;

	/**
	 * 页面输入参数－通知搜索条件 后台分页
	 */
	protected NoticeSearchMeta noticeSearchMeta;

	/**
	 * 返回页面结果-包含了分页信息的通知列表 后台分页
	 */
	protected NoticePageResult noticePageResult;

	/**
	 * 教务员用户：获取当前户用可查看的通知
	 * 
	 * @return
	 * 
	 *         SUCCESS：/acamgr/listNotice.jsp
	 */
	public String listNotice4acamgr() {
		// 写日志
		log.info("教务员:查询通知列表");

		Boolean isacamgr = Boolean.TRUE;

		internalGetNotices(isacamgr, null, null);
		
		// 返回结果
		return SUCCESS;
	}

	/**
	 * 教师用户：获取当前户用可查看的通知
	 * 
	 * @return
	 * 
	 *         SUCCESS：/teacher/listNotice.jsp
	 */
	public String listNotice4Teacher() {

		// 写日志
		log.info("教师用户:查询通知列表");

		Boolean isteacher = Boolean.TRUE;
		
		internalGetNotices(null, isteacher, null);

		return SUCCESS;
	}

	/**
	 * 学生用户：获取当前户用可查看的通知
	 * 
	 * @return
	 * 
	 *         SUCCESS：/student/listNotice.jsp
	 */
	public String listNotice4Student() {
		// 写日志
		log.info("学生用户:查询通知列表");

		Boolean isstudent = Boolean.TRUE;
		
		internalGetNotices(null, null, isstudent);

		return SUCCESS;
	}

	private void internalGetNotices(Boolean isacamgr, Boolean isteacher,
			Boolean isstudent) {
		// 默认查询条件
		Integer currentPage = 1;
		String keyword = null;

		// 获取页面输入查询条件
		if (noticeSearchMeta != null) {
			currentPage = noticeSearchMeta.getCurrentPage();
			keyword = this.getDefaultSearchValue(noticeSearchMeta.getKeyword(),
					null);
		}

		// 获取查询结果
		noticePageResult = noticeService.getNoticeByConditions(currentPage,
				keyword, isacamgr, isteacher, isstudent);
	
	}

	/**
	 * 系统用户：下载通知的附件
	 * 
	 * @return
	 */
	public String downloadNotice() {
		// 写日志
		log.info("系统用户:下载通知附件");

		try {
			// 获取通知
			Notice temp = this.noticeService.getNotice(noticeid);

			// 获取通知附件文件名:(支持中文)
			this.fileFileName = new String(temp.getFilename().getBytes(),
					"ISO-8859-1");
			
			downloadFilePath = noticePath + fileFileName;
			// 该附件的输入流
			InputStream inputStream = ServletActionContext.getServletContext()
					.getResourceAsStream(downloadFilePath);
			
			// 如果通知附件不存在
			if (inputStream == null) {
				return ERROR;
			}
		} catch (Exception e) {
			// 获取过程发生错误，noticeid不存在或者“ISO－8859-1”编码不支持
			log.error(e.getMessage());
			return ERROR;
		}
		return SUCCESS;
	}



	/**
	 * 教务员：进入添加通知页面
	 * 
	 * @return
	 */
	public String newNotice() {
		// 写日志
		log.info("教务员用户:准备新增通知");

		// 默认空通知
		internalGetNotice();

		// 返回结果
		return SUCCESS;
	}

	/**
	 * 教务员：保存通知
	 * 
	 * @return
	 */
	public String saveNotice() {
		// 写日志
		log.info("教务员用户:提交新通知");

		//modified by czf 增加类型检测
		try {
			boolean type = false;
			
			// 设置允许上传的后缀类型
			String allowsuffix[] = { "pdf", "doc", "docx", "xls", "xlsx",
					"rar", "zip" };

				// 获取文件类型
				FileType fileType = FileTypeDetector.getType(file);
				String suffix = fileType.getSuffix();

				// 判断文件类型是否正确
				for (String n : allowsuffix)
					if (suffix == n) {
						type = true;
						break;
					}

				// 不正确返回错误
				if (type == false) {
					return ERROR;
				}
	
			// 文件的保存路径是WebContent/file目录下
			String realpath = ServletActionContext.getServletContext()
					.getRealPath(noticePath);

			// 文件的后缀 bug，如果没有后缀？
			System.out.print(fileFileName);

			// 上传以后,会重命名文件的名称,将其命名为全部是数字的文件名,防止可能出现的乱码.
			newFileName = FileHelper.randFileName() + suffix;

			File savefile = new File(new File(realpath), newFileName);

			// 如果保存的路径不存在,则新建
			if (!savefile.getParentFile().exists())
				savefile.getParentFile().mkdirs();

			// 复制文件
			FileUtils.copyFile(file, savefile);

			// 更新notice表
			LoginUser user = CmsWebContext.getCurrentUser();
			Integer publisherid = user.getId();
			Integer universityid = user.getUniversityid();
			Integer departmentid = user.getDepartmentid();
			String name = user.getShowname();
			notice.setPublisherid(publisherid);
			notice.setUniversityid(universityid);
			notice.setDepartmentid(departmentid);
			notice.setName(name);
			notice.setFilename(newFileName);
			notice.setReleasetime(new Date());
			if (readerid == 0) { // for all
				notice.set4Department();
			} else if (readerid == 1) {
				notice.set4TCH();
			} else if (readerid == 2) {
				notice.set4STD();
			} else {
				notice.set4TCHandSTD();
			}
			this.noticeService.insertNotice(notice);
		} catch (Exception e) {
			log.error("教务员用户：提交新通知失败");
			return ERROR;
		}

		// 写日志
		log.info("教务员用户:提交新通知成功");
		// 准备返回页面查询条件
		NoticeSearchMeta meta = internalGetNoticeSearchMeta();
		meta.setKeyword(notice.getTitle());
		return SUCCESS;
	}

	/**
	 * 教务员：删除通知
	 * 
	 * @return
	 */
	public String deleteNotice() {
		log.info("教务员用户:删除通知");
		try {
			// 获取通知
			Notice temp = this.noticeService.getNotice(noticeid);
			// 通知附件文件名
			fileFileName = new String(temp.getFilename().getBytes(),
					"ISO-8859-1");
			String realpath = ServletActionContext.getServletContext()
					.getRealPath(noticePath);
			try {
				File deletefile = new File(realpath + fileFileName);
				// 删除通知文件
				if (deletefile.isFile() && deletefile.exists()) {
					deletefile.delete();
				}
			} catch (Exception e) {
				// do nothing
			}
			// 删除数据库记录
			this.noticeService.deleteNotice(noticeid);
		} catch (Exception e) {
			// noticeid 不存在等
			log.info("教务员:删除通知失败");
			return ERROR;
		}
		// 写日志
		log.info("教务员:删除通知成功");
		return SUCCESS;
	}

	/*
	 * internal methods
	 */

	private Notice internalGetNotice() {
		if (notice == null)
			notice = new Notice();
		return notice;
	}

	private NoticeSearchMeta internalGetNoticeSearchMeta() {
		if (noticeSearchMeta == null)
			noticeSearchMeta = new NoticeSearchMeta();
		return noticeSearchMeta;
	}

	/*
	 * getters and setters
	 */

	public NoticeService getNoticeService() {
		return noticeService;
	}

	public void setNoticeService(NoticeService noticeService) {
		this.noticeService = noticeService;
	}

	public Integer getNoticeid() {
		return noticeid;
	}

	public void setNoticeid(Integer noticeid) {
		this.noticeid = noticeid;
	}

	public Notice getNotice() {
		return notice;
	}

	public void setNotice(Notice notice) {
		this.notice = notice;
	}

	public List<Notice> getNotices() {
		return notices;
	}

	public void setNotices(List<Notice> notices) {
		this.notices = notices;
	}

	public NoticeSearchMeta getNoticeSearchMeta() {
		return noticeSearchMeta;
	}

	public void setNoticeSearchMeta(NoticeSearchMeta noticeSearchMeta) {
		this.noticeSearchMeta = noticeSearchMeta;
	}

	public NoticePageResult getNoticePageResult() {
		return noticePageResult;
	}

	public void setNoticePageResult(NoticePageResult noticePageResult) {
		this.noticePageResult = noticePageResult;
	}

	public Integer getReaderid() {
		return readerid;
	}

	public void setReaderid(Integer readerid) {
		this.readerid = readerid;
	}

}
