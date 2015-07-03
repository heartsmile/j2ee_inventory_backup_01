/**
 * 
 */
package com.j2ee.java.model.bo;

import java.text.SimpleDateFormat;

import org.springframework.stereotype.Component;

/**
 * @author John Tran
 *
 */
@Component
public class Utils {
	public static final SimpleDateFormat DATE_FORMATTER_WEB = new SimpleDateFormat(
			"MM/dd/yyyy");
	public static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat(
			"yyyy-MM-dd");
	
	public static final String LINK_REPORT_RESOURCE = "E:\\j2ee_backup_01\\trunk\\src\\main\\resources\\";
	
	public static final String LINK_REPORT_OUTPUT = "E:\\";
}
