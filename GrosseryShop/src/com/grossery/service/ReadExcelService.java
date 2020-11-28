package com.grossery.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grossery.controller.LoginController;
import com.grossery.model.HistoryMaster;
import com.grossery.model.MailContent;

@Service
public class ReadExcelService {
	private static final Logger logger = Logger.getLogger(ReadExcelService.class);
	@Autowired
	public MailContent content;
	@Autowired
	HttpServletRequest request;

	public MailContent readExcel(String filepath,String subject,String body) throws EncryptedDocumentException, InvalidFormatException, IOException{
		content.setBody(body);
		content.setSubject(subject);
		int passwdColindx=0;
		List<String> phoneNos=new ArrayList<>();
		FileInputStream file = new FileInputStream(filepath);
		Workbook workbook = WorkbookFactory.create(file);
		 Sheet sheet = workbook.getSheetAt(0);
		 DataFormatter dataFormatter = new DataFormatter();
		 
		 for (Row row: sheet) {
			 
	            for(Cell cell: row) {
	            	if(row.getRowNum()==0){
	            		String cellValue = dataFormatter.formatCellValue(cell);
	            		if(cellValue.contains("Phone") || cellValue.contains("Mobile")){
	            		passwdColindx= cell.getColumnIndex();	
	            		logger.info("passwdColindx---"+ passwdColindx);
	            		}
	            	}else{
	            		
	            		if(passwdColindx==cell.getColumnIndex()){
	            			logger.info("cell no---"+ cell.getColumnIndex());
	                String mobileNo = dataFormatter.formatCellValue(cell);
	                phoneNos.add(mobileNo);
	                logger.info(mobileNo + "\t");
	            		}
	               // System.out.print(cellValue + "\t");
	            	}
	            }
	          
	        }
		 content.setTo(phoneNos);
		 file.close();
		return content;
	}
	
	public String writeFailedNoInExcel(String filename,Map<String,String> phonelist){
		
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet spreadsheet = workbook.createSheet( " Failed Phone Numbers ");
		XSSFRow row;
		int rowid = 0;
		row = spreadsheet.createRow(rowid++);
		row.createCell(0).setCellValue("Phone Number");
		row.createCell(1).setCellValue("Reason Of Failure");
		
		for (Map.Entry<String, String> entry : phonelist.entrySet()) {
			row = spreadsheet.createRow(rowid++);
			row.createCell(0).setCellValue(entry.getKey());
			row.createCell(1).setCellValue(entry.getValue());
		}
		 FileOutputStream out = null;
		 File currDir = new File(".");
		    String path = currDir.getAbsolutePath();
		    String fileLocation = path.substring(0, path.length() - 1)+filename;
		    
		    logger.info("write excel in------"+fileLocation);
		    
		    Properties prop = new Properties();
		    InputStream input = null;
		try {
			String contextPath = request.getContextPath();
			 input = new FileInputStream(contextPath+"/config.properties");

		        // load a properties file
		        prop.load(input);
			logger.info("context path is--"+contextPath);
			logger.info("property is-------"+prop.getProperty("upload.location"));
			out = new FileOutputStream(
			         new File(fileLocation));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 try {
			workbook.write(out);
			workbook.close();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
}
