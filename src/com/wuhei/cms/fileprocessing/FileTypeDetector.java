package com.wuhei.cms.fileprocessing;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 文件类型判断类
 */

public final class FileTypeDetector {

	/**
	 * Constructor
	 */
	private FileTypeDetector() {
	}

	/**
	 * 将文件头转换成16进制字符串
	 * 
	 * @param 原生byte
	 * @return 16进制字符串
	 */
	private static String bytesToHexString(byte[] src) {

		StringBuilder stringBuilder = new StringBuilder();
		if (src == null || src.length <= 0) {
			return null;
		}
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();
	}

	/**
	 * 获取（文件）输入流头28字节，并转换成十六进制字符串
	 * 
	 * @param inputStream
	 * @return
	 * @throws IOException
	 */
	private static String getFileHeader28Bytes(InputStream inputStream)
			throws IOException {
		byte[] b = new byte[28];
		try {
			inputStream.read(b, 0, 28);
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
					throw e;
				}
			}
		}
		return bytesToHexString(b);
	}

	
	public static FileType getType(File file) throws IOException {

		InputStream inputStream = new FileInputStream(file);
		
		String fileHead = getFileHeader28Bytes(inputStream);

		if (fileHead == null || fileHead.length() == 0) {
			return null;
		}

		fileHead = fileHead.toUpperCase();


		FileType[] fileTypes = FileType.values();

		for (FileType type : fileTypes) {
			if (fileHead.startsWith(type.getValue())) {
				return type;
			}
		}

		return null;
	}
}
