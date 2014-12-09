package com.wuhei.cms.fileprocessing;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.wuhei.cms.fileprocessing.FileHelper;
import com.wuhei.cms.fileprocessing.FileType;
import com.wuhei.cms.fileprocessing.FileTypeDetector;


/**
 * �����ı��棬���أ�ɾ��
 * 
 *
 */
public class AttachProcessing {
	
	public static String saveAttach (String attachPath, String[] allowsuffix, String fileFileName, File file) {
		
		// �����ĳ�ʼ��
		boolean type = false;
		String newFileName = null;
		String suffix = null;
		try {
			
			// ��ȡ�ļ�����
			FileType filetype = FileTypeDetector.getType(file);
			
			try {
				// ��ȡ��׺��(��: .doc)
				suffix = filetype.getSuffix();
			} catch (Exception e) {
				
				return "error";
				
			}
			
			
			// ƥ��������ϴ����ļ���׺����, �ɹ�type=true
			for(String n: allowsuffix)
				if(suffix == n) {
					type = true;
					break;
				}
			
			// ���ʹ���, �����ַ���error
			if(type == false)
				return "error";
			
			// ������ȷ, ���Ȼ�ȡʵ�ʱ���·��
			String realpath = ServletActionContext.getServletContext().getRealPath(attachPath);
			
			// �������޷�����(doc��xls)����(docx��xlsx), ����ֱ�ӻ�ȡ�ļ��ĺ�׺��
			if(suffix == "docx" || suffix == "doc")
				suffix = fileFileName.substring(fileFileName
						.lastIndexOf(".") + 1);
			
			// �����µ��ļ���, ��ֹ�ϴ������ļ������⹥��
			newFileName = FileHelper.randFileName() + "." + suffix;
			
			// �����ļ�����
			File savefile = new File(new File(realpath), newFileName);
			
			// �ļ��в��������½��ļ���
			if (!savefile.getParentFile().exists())
				savefile.getParentFile().mkdirs();
			
			// �ļ��ı���			
			FileUtils.copyFile(file, savefile);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			// �������ַ���error
			return "error";
		}
		
		// �ɹ����淵���µ�ʱ����ļ���
		return newFileName;
		
	}
	
	public boolean deleteAttach(String fileFileName, String attachPath) {
		
		String realpath = ServletActionContext.getServletContext()
				.getRealPath(attachPath);
		
		try {
			
			File deletefile = new File(realpath + fileFileName);
			
			if (deletefile.isFile() && deletefile.exists()) {
				deletefile.delete();
			}
			else
				return false;
			
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		
		return true;
		
	}

}
