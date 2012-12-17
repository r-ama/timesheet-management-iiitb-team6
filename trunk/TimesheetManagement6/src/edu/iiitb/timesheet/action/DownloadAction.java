package edu.iiitb.timesheet.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.tomcat.util.http.parser.ParseException;

import com.mast.util.DB;
import com.mast.util.POIExcelUtility;
import com.opensymphony.xwork2.ActionSupport;

public class DownloadAction extends ActionSupport {
	
	
	String startDate;
	String endDate;
	String reportType;
	private InputStream fileInputStream;
	String filename;
	  
	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public void setFileInputStream(InputStream fileInputStream) {
		this.fileInputStream = fileInputStream;
	}

	public InputStream getFileInputStream() {
	 
		return fileInputStream;
	   
	}
	
	public String getReportType() {
		return reportType;
	}


	public void setReportType(String reportType) {
		this.reportType = reportType;
	}


	public String getStartDate() {
		return startDate;
	}


	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}


	public String getEndDate() {
		return endDate;
	}


	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}


	public String execute()
	{
		SimpleDateFormat format1 = new SimpleDateFormat("dd-MMM-yyyy");
		SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
		String sDate,eDate;
		sDate=startDate;
		eDate=endDate;
		try
		{
		Date date=format1.parse(startDate);
		startDate = format2.format(date);
		
		date=format1.parse(endDate);
		endDate = format2.format(date);
		}
		 catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(reportType.equals("1"))
		{
			
			try
			{
				InputStream inputStream = new FileInputStream ("D:\\Template\\Effort-Template.xls");
	            POIFSFileSystem fileSystem = new POIFSFileSystem (inputStream);

	            HSSFWorkbook workBook = new HSSFWorkbook (fileSystem);
	            
	            HSSFSheet  sheet1    = workBook.getSheetAt (0);
	            
	            System.out.println("startDate:"+startDate+"endDate:"+endDate);
				
			String query = "select sum(te.hours_worked),e.empName from project p,allocation a,employee e,timesheetentry te";
			query+=" where p.projectId = a.projectId";
			query+=" and e.empId = a.empId";
			
			query+=" and a.projectId = te.projectId";
			query+=" and e.empId = te.empId";
			query+=" and te.date_of_entry between '"+startDate+"' and '"+endDate+"'";
			query+=" and te.approval_flag = 'Y'";
			query+=" group by e.empName";
			
			ResultSet rs= DB.readFromBmtcDB(query);
			int startRow=4;
			POIExcelUtility.writeXl(sheet1, 0, 1, sDate);
			POIExcelUtility.writeXl(sheet1, 0, 3, eDate);
			while(rs.next())
			{
				String effort=rs.getString(1);
				String userName=rs.getString(2);
				
				POIExcelUtility.writeXl(sheet1, startRow, 0, userName);
				POIExcelUtility.writeXl(sheet1, startRow, 1, Double.parseDouble(effort));
				
				startRow++;
				
				
			}
			
			filename = "effort_report"+new Date().getTime()+".xls";
			FileOutputStream fileOut1 = new FileOutputStream("D:\\Output\\"+filename);
	        workBook.write(fileOut1);
	        fileOut1.close();
	        
	        fileInputStream = new FileInputStream(new File("D:\\Output\\"+filename));
			
			return "success";
		    
			}
			catch(Exception e)
			{
				e.printStackTrace();
				return "error";
				
			}
		}
		
		if(reportType.equals("3"))
		{
			try
			{
				InputStream inputStream = new FileInputStream ("D:\\Template\\Project-Template.xls");
	            POIFSFileSystem fileSystem = new POIFSFileSystem (inputStream);

	            HSSFWorkbook workBook = new HSSFWorkbook (fileSystem);
	            
	            HSSFSheet  sheet1    = workBook.getSheetAt (0);
				
				String query = "select projectid,projectname,datediff(p.planned_end_date,p.planned_start_date) planned_duration,datediff(p.actual_end_date,p.actual_start_date) actual_duration,p.planned_effort,p.actual_effort from project p";
				
				ResultSet rs=DB.readFromBmtcDB(query);
				int startRow=4;
				POIExcelUtility.writeXl(sheet1, 0, 1, sDate);
				POIExcelUtility.writeXl(sheet1, 0, 3, eDate);
				while(rs.next())
				{
					

					for(int count=1;count<=6;count++)
					{
						if(count<=2)
							POIExcelUtility.writeXl(sheet1, startRow, count-1, rs.getString(count));
						else
						{
							if(rs.getString(count)!=null)
								POIExcelUtility.writeXl(sheet1, startRow, count-1, rs.getDouble(count));
							else
								POIExcelUtility.writeXl(sheet1, startRow, count-1, "");
							
						}
						
					}
					startRow++;
					
					
				}
				
				filename = "project_report"+new Date().getTime()+".xls";
				FileOutputStream fileOut1 = new FileOutputStream("D:\\Output\\"+filename);
		        workBook.write(fileOut1);
		        fileOut1.close();
		        
		        fileInputStream = new FileInputStream(new File("D:\\Output\\"+filename));
				
				return "success";

			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				
			}
			
			
		}
		return "success";
		
	}

}
