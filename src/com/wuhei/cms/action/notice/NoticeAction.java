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
 * ֪ͨ����|����
 * 
 * ��ʱʹ�����ģ�ͣ� 1. ���������Ա����֪ͨ 2. ����ָ֪ͨ���������ʦ������ѧ��������ȫ��
 * 
 * 
 */
@SuppressWarnings("serial")
public class NoticeAction extends BaseAction {

	protected final static Logger log = Logger.getLogger(NoticeAction.class);

	protected NoticeService noticeService;

	/**
	 * �洢֪ͨ������·�� classInfo/file/notice
	 */
	private String noticePath = File.separator + "file" + File.separator
			+ "notice" + File.separator;

	/**
	 * ҳ�����������֪ͨid
	 */
	protected Integer noticeid;

	/**
	 * ҳ�����������֪ͨ����
	 */
	protected Integer readerid;

	/**
	 * ҳ�����������֪ͨ
	 */
	protected Notice notice;

	/**
	 * ֪ͨ�б� ǰ̨��ҳ
	 */
	protected List<Notice> notices;

	/**
	 * ҳ�����������֪ͨ�������� ��̨��ҳ
	 */
	protected NoticeSearchMeta noticeSearchMeta;

	/**
	 * ����ҳ����-�����˷�ҳ��Ϣ��֪ͨ�б� ��̨��ҳ
	 */
	protected NoticePageResult noticePageResult;

	/**
	 * ����Ա�û�����ȡ��ǰ���ÿɲ鿴��֪ͨ
	 * 
	 * @return
	 * 
	 *         SUCCESS��/acamgr/listNotice.jsp
	 */
	public String listNotice4acamgr() {
		// д��־
		log.info("����Ա:��ѯ֪ͨ�б�");

		Boolean isacamgr = Boolean.TRUE;

		internalGetNotices(isacamgr, null, null);
		
		// ���ؽ��
		return SUCCESS;
	}

	/**
	 * ��ʦ�û�����ȡ��ǰ���ÿɲ鿴��֪ͨ
	 * 
	 * @return
	 * 
	 *         SUCCESS��/teacher/listNotice.jsp
	 */
	public String listNotice4Teacher() {

		// д��־
		log.info("��ʦ�û�:��ѯ֪ͨ�б�");

		Boolean isteacher = Boolean.TRUE;
		
		internalGetNotices(null, isteacher, null);

		return SUCCESS;
	}

	/**
	 * ѧ���û�����ȡ��ǰ���ÿɲ鿴��֪ͨ
	 * 
	 * @return
	 * 
	 *         SUCCESS��/student/listNotice.jsp
	 */
	public String listNotice4Student() {
		// д��־
		log.info("ѧ���û�:��ѯ֪ͨ�б�");

		Boolean isstudent = Boolean.TRUE;
		
		internalGetNotices(null, null, isstudent);

		return SUCCESS;
	}

	private void internalGetNotices(Boolean isacamgr, Boolean isteacher,
			Boolean isstudent) {
		// Ĭ�ϲ�ѯ����
		Integer currentPage = 1;
		String keyword = null;

		// ��ȡҳ�������ѯ����
		if (noticeSearchMeta != null) {
			currentPage = noticeSearchMeta.getCurrentPage();
			keyword = this.getDefaultSearchValue(noticeSearchMeta.getKeyword(),
					null);
		}

		// ��ȡ��ѯ���
		noticePageResult = noticeService.getNoticeByConditions(currentPage,
				keyword, isacamgr, isteacher, isstudent);
	
	}

	/**
	 * ϵͳ�û�������֪ͨ�ĸ���
	 * 
	 * @return
	 */
	public String downloadNotice() {
		// д��־
		log.info("ϵͳ�û�:����֪ͨ����");

		try {
			// ��ȡ֪ͨ
			Notice temp = this.noticeService.getNotice(noticeid);

			// ��ȡ֪ͨ�����ļ���:(֧������)
			this.fileFileName = new String(temp.getFilename().getBytes(),
					"ISO-8859-1");
			
			downloadFilePath = noticePath + fileFileName;
			// �ø�����������
			InputStream inputStream = ServletActionContext.getServletContext()
					.getResourceAsStream(downloadFilePath);
			
			// ���֪ͨ����������
			if (inputStream == null) {
				return ERROR;
			}
		} catch (Exception e) {
			// ��ȡ���̷�������noticeid�����ڻ��ߡ�ISO��8859-1�����벻֧��
			log.error(e.getMessage());
			return ERROR;
		}
		return SUCCESS;
	}



	/**
	 * ����Ա���������֪ͨҳ��
	 * 
	 * @return
	 */
	public String newNotice() {
		// д��־
		log.info("����Ա�û�:׼������֪ͨ");

		// Ĭ�Ͽ�֪ͨ
		internalGetNotice();

		// ���ؽ��
		return SUCCESS;
	}

	/**
	 * ����Ա������֪ͨ
	 * 
	 * @return
	 */
	public String saveNotice() {
		// д��־
		log.info("����Ա�û�:�ύ��֪ͨ");

		//modified by czf �������ͼ��
		try {
			boolean type = false;
			
			// ���������ϴ��ĺ�׺����
			String allowsuffix[] = { "pdf", "doc", "docx", "xls", "xlsx",
					"rar", "zip" };

				// ��ȡ�ļ�����
				FileType fileType = FileTypeDetector.getType(file);
				String suffix = fileType.getSuffix();

				// �ж��ļ������Ƿ���ȷ
				for (String n : allowsuffix)
					if (suffix == n) {
						type = true;
						break;
					}

				// ����ȷ���ش���
				if (type == false) {
					return ERROR;
				}
	
			// �ļ��ı���·����WebContent/fileĿ¼��
			String realpath = ServletActionContext.getServletContext()
					.getRealPath(noticePath);

			// �ļ��ĺ�׺ bug�����û�к�׺��
			System.out.print(fileFileName);

			// �ϴ��Ժ�,���������ļ�������,��������Ϊȫ�������ֵ��ļ���,��ֹ���ܳ��ֵ�����.
			newFileName = FileHelper.randFileName() + suffix;

			File savefile = new File(new File(realpath), newFileName);

			// ��������·��������,���½�
			if (!savefile.getParentFile().exists())
				savefile.getParentFile().mkdirs();

			// �����ļ�
			FileUtils.copyFile(file, savefile);

			// ����notice��
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
			log.error("����Ա�û����ύ��֪ͨʧ��");
			return ERROR;
		}

		// д��־
		log.info("����Ա�û�:�ύ��֪ͨ�ɹ�");
		// ׼������ҳ���ѯ����
		NoticeSearchMeta meta = internalGetNoticeSearchMeta();
		meta.setKeyword(notice.getTitle());
		return SUCCESS;
	}

	/**
	 * ����Ա��ɾ��֪ͨ
	 * 
	 * @return
	 */
	public String deleteNotice() {
		log.info("����Ա�û�:ɾ��֪ͨ");
		try {
			// ��ȡ֪ͨ
			Notice temp = this.noticeService.getNotice(noticeid);
			// ֪ͨ�����ļ���
			fileFileName = new String(temp.getFilename().getBytes(),
					"ISO-8859-1");
			String realpath = ServletActionContext.getServletContext()
					.getRealPath(noticePath);
			try {
				File deletefile = new File(realpath + fileFileName);
				// ɾ��֪ͨ�ļ�
				if (deletefile.isFile() && deletefile.exists()) {
					deletefile.delete();
				}
			} catch (Exception e) {
				// do nothing
			}
			// ɾ�����ݿ��¼
			this.noticeService.deleteNotice(noticeid);
		} catch (Exception e) {
			// noticeid �����ڵ�
			log.info("����Ա:ɾ��֪ͨʧ��");
			return ERROR;
		}
		// д��־
		log.info("����Ա:ɾ��֪ͨ�ɹ�");
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
