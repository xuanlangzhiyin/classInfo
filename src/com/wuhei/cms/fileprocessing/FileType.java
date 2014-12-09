package com.wuhei.cms.fileprocessing;


/**
 * 文件类型枚取
 */
public enum FileType {
	
	/**
	 * JEPG.
	 */
	JPEG("FFD8FF","jpeg"),
	
	/**
	 * PNG.
	 */
	PNG("89504E47","png"),
	
	/**
	 * GIF.
	 */
	GIF("47494638","gif"),
	
	/**
	 * TIFF.
	 */
	TIFF("49492A00","tiff"),
	
	/**
	 * Windows Bitmap.
	 */
	BMP("424D","bmp"),
	
	/**
	 * CAD.
	 */
	DWG("41433130","dwg"),
	
	/**
	 * Adobe Photoshop.
	 */
	PSD("38425053","psd"),
	
	/**
	 * Rich Text Format.
	 */
	RTF("7B5C727466","rtf"),
	
	/**
	 * XML.
	 */
	XML("3C3F786D6C","xml"),
	
	/**
	 * HTML.
	 */
	HTML("68746D6C3E","html"),
	
	/**
	 * Email [thorough only].
	 */
	EML("44656C69766572792D646174653A","eml"),
	
	/**
	 * Outlook Express.
	 */
	DBX("CFAD12FEC5FD746F","dbx"),
	
	/**
	 * Outlook (pst).
	 */
	PST("2142444E","pst"),
	
	/**
	 * MS Word [doc]/Excel[xls].
	 */
	XLS_DOC("D0CF11E0A1B11AE100","doc"),
	
	/**
	 * MS Word [docx]/Excel[xlsx].
	 */
	XLSX_DOCX("504B03041400060008","docx"),
	
	/**
	 * MS Access.
	 */
	MDB("5374616E64617264204A","mdb"),
	
	/**
	 * WordPerfect.
	 */
	WPD("FF575043","wpd"),
	
	/**
	 * Postscript.
	 */
	EPS("252150532D41646F6265","eps"),
	
	/**
	 * Adobe Acrobat.
	 */
	PDF("25504446","pdf"),
	
	/**
	 * Quicken.
	 */
	QDF("AC9EBD8F","qdf"),
	
	/**
	 * Windows Password.
	 */
	PWL("E3828596","pwl"),
	
	/**
	 * ZIP Archive.
	 */
	ZIP("504B0304","zip"),
	
	/**
	 * RAR Archive.
	 */
	RAR("52617221","rar"),
	
	/**
	 * Wave.
	 */
	WAV("57415645","wav"),
	
	/**
	 * AVI.
	 */
	AVI("41564920","avi"),
	
	/**
	 * Real Audio.
	 */
	RAM("2E7261FD","ram"),
	
	/**
	 * Real Media.
	 */
	RM("2E524D46","rm"),
	
	/**
	 * MPEG (mpg).
	 */
	MPG("000001BA","mpg"),
	
	/**
	 * Quicktime.
	 */
	MOV("6D6F6F76","mov"),
	
	/**
	 * Windows Media.
	 */
	ASF("3026B2758E66CF11","asf"),
	
	/**
	 * SWF.
	 */
	SWF("43575309FE8C000078","swf"),
	
	/**
	 * MIDI.
	 */
	MID("4D546864","mid");

	
	
	/**
	 * 某种类型文件的其实部分字节
	 */
	private String value = "";
	
	/**
	 * 对应文件类型的后缀名称
	 */
	private String suffix = "";

	/**
	 * Constructor.
	 * 
	 * @param type 
	 */
	private FileType(String value, String suffix) {
		this.value = value;
		this.suffix = suffix;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
}

