package com.mast.util;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;

public class POIExcelUtility {
	
	
	public static void writeXl(HSSFSheet sheet,int rowIndex,int columnIndex,String data)
	{
		
		HSSFRow row=sheet.getRow(rowIndex);
		if(row==null)
			row=sheet.createRow(rowIndex);
		HSSFCell cell =row.getCell(columnIndex);
		if(cell==null)
			cell=row.createCell(columnIndex);
		
		cell.setCellValue(data);
		
	}
	
	public static void writeXl(HSSFSheet sheet,int rowIndex,int columnIndex,double data)
	{
		
		HSSFRow row=sheet.getRow(rowIndex);
		if(row==null)
			row=sheet.createRow(rowIndex);
		HSSFCell cell =row.getCell(columnIndex);
		if(cell==null)
			cell=row.createCell(columnIndex);
		
		cell.setCellValue(data);
		
	}

}
