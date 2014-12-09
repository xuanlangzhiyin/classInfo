package com.wuhei.cms.fileprocessing;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.wuhei.cms.fileprocessing.FileHelper;
import com.wuhei.cms.fileprocessing.FileType;
import com.wuhei.cms.fileprocessing.FileTypeDetector;


/**
 * 附件的保存，下载，删除
 * 
 *
 */
public class AttachProcessing {
	
	public static String saveAttach (String attachPath, String[] allowsuffix, String fileFileName, File file) {
		
		// 变量的初始化
		boolean type = false;
		String newFileName = null;
		String suffix = null;
		try {
			
			// 获取文件类型
			FileType filetype = FileTypeDetector.getType(file);
			
			try {
				// 获取后缀名(例: .doc)
				suffix = filetype.getSuffix();
			} catch (Exception e) {
				
				return "error";
				
			}
			
			
			// 匹配可允许上传的文件后缀类型, 成功type=true
			for(String n: allowsuffix)
				if(suffix == n) {
					type = true;
					break;
				}
			
			// 类型错误, 返回字符串error
			if(type == false)
				return "error";
			
			// 类型正确, 首先获取实际保存路径
			String realpath = ServletActionContext.getServletContext().getRealPath(attachPath);
			
			// 技术上无法区分(doc和xls)还有(docx和xlsx), 所以直接获取文件的后缀名
			if(suffix == "docx" || suffix == "doc")
				suffix = fileFileName.substring(fileFileName
						.lastIndexOf(".") + 1);
			
			// 生成新的文件名, 防止上传超长文件名恶意攻击
			newFileName = FileHelper.randFileName() + "." + suffix;
			
			// 声明文件变量
			File savefile = new File(new File(realpath), newFileName);
			
			// 文件夹不存在则新建文件夹
			if (!savefile.getParentFile().exists())
				savefile.getParentFile().mkdirs();
			
			// 文件的保存			
			FileUtils.copyFile(file, savefile);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			// 出错返回字符串error
			return "error";
		}
		
		// 成功保存返回新的时间戳文件名
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
