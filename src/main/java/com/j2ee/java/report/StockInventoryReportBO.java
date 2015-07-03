package com.j2ee.java.report;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import com.j2ee.java.model.dao.HibernateUtil;

@Component
public class StockInventoryReportBO {
	
	private Map<String, Object> params = new HashMap<String, Object>();
	static SessionFactory session = HibernateUtil.getSessionFactory();

	public Session getOpenSession() {

		return session.openSession();
	}

	public void runReport(String path, String fileName, int stockID) {// GEN-FIRST:event_jxBtnReviewActionPerformed
		
		Connection connection = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/inventory_erp","root", "anhquan");
			
			params.put("REPORT_CONNECTION", connection);
			String filePath = fileName;
			try {

				JasperReport reportFile = JasperCompileManager.compileReport(path);
				JasperPrint jPrint = JasperFillManager.fillReport(reportFile,
						params);
				JasperExportManager.exportReportToPdfFile(jPrint, filePath);

			} catch (JRException ex) {
				Logger.getLogger(ProductReportBO.class.getName()).log(Level.ERROR,
						null, ex);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
