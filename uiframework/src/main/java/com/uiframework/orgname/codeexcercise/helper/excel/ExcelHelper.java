package com.uiframework.orgname.codeexcercise.helper.excel;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.uiframework.orgname.codeexcercise.helper.logger.LoggerHelper;
import com.uiframework.orgname.codeexcercise.helper.resource.ResourceHelper;

public class ExcelHelper {
	 private static final String STRING = null;
	private static final String NUMERIC = null;
	private static final String BOOLEAN = null;
	private Logger log =LoggerHelper.getLogger(ExcelHelper.class);
	 
	 public Object[][] getExcelData(String excellocation, String sheetName) {
		 
		 try {
			 Object dataSets[][] = null;
			 FileInputStream file= new FileInputStream(new File(excellocation));
			 //Create Workbook instance
			 XSSFWorkbook workbook = new XSSFWorkbook(file);
			 //Get Sheetname from Workbook
			 XSSFSheet sheet = workbook.getSheet(sheetName);
			 //Count number of active rows in excel sheet
			 int totalRows=sheet.getLastRowNum();
			 System.out.println(totalRows);
			 //Count number of active columns in excel sheet
			 int totalColumns=sheet.getRow(0).getLastCellNum();
			 dataSets = new Object[totalRows][totalColumns];
			 //Iterate through each row one by one
			 Iterator<Row> rowIterator = sheet.iterator();
			 int i=0;
			 while(rowIterator.hasNext()){
				 
				 Row row =rowIterator.next();
				 
				 Iterator<Cell> cellIterator = row.cellIterator();
				 int j=0;
				 while(cellIterator.hasNext()){
				 j++;
				  Cell cell = cellIterator.next();
				  
				  switch(cell.getCellType()) {
				  case STRING:
				  dataSets[i-1][j-1]=cell.getStringCellValue();
				  break;
				  case NUMERIC:
				  dataSets[i-1][j-1]=cell.getNumericCellValue();
				  break;
				  case BOOLEAN:
				  dataSets[i-1][j-1]=cell.getBooleanCellValue();
				  break;	
				  case FORMULA:
				  dataSets[i-1][j-1]=cell.getCellFormula();
				  break;
				  
				  default:
					  
				  }
				 
				 }
			 }
			 return dataSets;
			 
		 }
		catch(Exception e) { 
			e.printStackTrace();
	 }
	 return null;
  }
	 
	 public static void main(String[] args) {
		 ExcelHelper excelHelper = new ExcelHelper();
		 String excellocation = ResourceHelper.getResourcePath("/src/main/resources/configfile/testdata.xlsx");
		 Object[][] data =excelHelper.getExcelData(excellocation, "Login");
		 System.out.println(data);
	 }
	 
	 }

