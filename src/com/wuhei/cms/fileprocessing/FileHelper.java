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
 * �ļ�Helper��
 * 
 *
 * 
 */


public class FileHelper {

	/**
	 * ��ȡ�ļ���׺��
	 * 
	 * @param name
	 * @return
	 */
	public static String getSuffixName(String name) {
		return name.substring(name.lastIndexOf(".") + 1);
	}

	/**
	 * ��ȡ�ļ�ǰ׺
	 * 
	 * @param name
	 * @return
	 */
	public static String getPrefixName(String name) {
		return name.substring(0, name.lastIndexOf("."));
	}

	/**
	 * ��ȡ�ļ���(������·��)
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
	 * ����һ��18λ������ļ���. ���ɹ�������: ǰ��14λ������ʱ������,������ʽΪ"yyyyMMddHHmmss",
	 * ��4λ��α�����(0-9999,����4λ��0)����
	 * 
	 * @return String ����ļ���
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
	 * �ж�ԭ�ļ����ֽڳ��ȣ����С�ڵ���60���ֽڣ�����ԭ�ļ������������60���ֽڣ�
	 * ��ԭ�ļ���ǰȡ50���ֽڣ�����ȡ7���ֽڣ��м���ʡ�ԺŲ���
	 * @param oldName
	 * @return
	 * 
	 */
	public static String setOldname(String oldName) {
		
		// ��ȡ�ļ�����������·���ͺ�׺
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
	 * �����ļ���ͨ�ð汾
	 * @param oldName ԭʼ�ļ���
	 * @param symbol �м�ʡ�Է���
	 * @param break_length ������������
	 * @param keep_length ǰ������������
	 * @return
	 */
	public static String setOldname(String oldName, String symbol, Integer break_length, Integer keep_length) {
		
		// ��ȡ�ļ�����������·���ͺ�׺
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
