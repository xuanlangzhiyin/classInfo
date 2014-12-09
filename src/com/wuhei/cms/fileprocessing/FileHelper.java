package com.wuhei.cms.fileprocessing;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * 
 * 文件Helper类
 * 
 *
 * 
 */


public class FileHelper {

	/**
	 * 获取文件后缀名
	 * 
	 * @param name
	 * @return
	 */
	public static String getSuffixName(String name) {
		return name.substring(name.lastIndexOf(".") + 1);
	}

	/**
	 * 获取文件前缀
	 * 
	 * @param name
	 * @return
	 */
	public static String getPrefixName(String name) {
		return name.substring(0, name.lastIndexOf("."));
	}

	/**
	 * 获取文件名(不包含路径)
	 * 
	 * @param name
	 * @return
	 */
	public static String getFileName(String name) {
		return name.substring(name.lastIndexOf("/") + 1);
	}

	public static void copyFile(String inputFile, String outputFile)
			throws FileNotFoundException {
		File sFile = new File(inputFile);
		File tFile = new File(outputFile);
		FileInputStream fis = new FileInputStream(sFile);
		FileOutputStream fos = new FileOutputStream(tFile);
		int temp = 0;
		try {
			while ((temp = fis.read()) != -1) {
				fos.write(temp);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fis.close();
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}


	/**
	 * 生成一个18位随机的文件名. 生成规则如下: 前面14位由日期时间生成,生成形式为"yyyyMMddHHmmss",
	 * 后4位由伪随机数(0-9999,不足4位则补0)构成
	 * 
	 * @return String 随机文件名
	 */
	public static String randFileName() {
		String result = "";
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		result += sdf.format(date);
		Double rand = Math.random() * 10000;
		if (rand < 10)
			result += "000" + rand.toString().substring(0, 1);
		else if (rand < 100)
			result += "00" + rand.toString().substring(0, 2);
		else if (rand < 1000)
			result += "0" + rand.toString().substring(0, 3);
		else
			result += rand.toString().substring(0, 4);

		return result;
	}
	
	/**
	 * 判断原文件名字节长度，如果小于等于60个字节，返回原文件名，如果大于60个字节，
	 * 在原文件名前取50个字节，后面取7个字节，中间用省略号补充
	 * @param oldName
	 * @return
	 * 
	 */
	public static String setOldname(String oldName) {
		
		// 获取文件名，不包含路径和后缀
		oldName = getFileName(oldName);
		
		String newoldName = null;
		
		if(oldName.length() <= 60)
			return oldName;
		else {
			newoldName = oldName.substring(0, 50) + "..." + oldName.substring(oldName.length() - 7, oldName.length());
		}
		System.out.print(newoldName);
		return newoldName;
	}
	
	/***
	 * 设置文件名通用版本
	 * @param oldName 原始文件名
	 * @param symbol 中间省略符号
	 * @param break_length 后保留字数字数
	 * @param keep_length 前保留字数字数
	 * @return
	 */
	public static String setOldname(String oldName, String symbol, Integer break_length, Integer keep_length) {
		
		// 获取文件名，不包含路径和后缀
		oldName = getFileName(oldName);
		
		String newoldName = null;
		
		if(oldName.length() <= 60)
			return oldName;
		else {
			newoldName = oldName.substring(0, keep_length) + symbol + oldName.substring(oldName.length() - break_length + 6, oldName.length());
		}
		return newoldName;
	}
	
}
