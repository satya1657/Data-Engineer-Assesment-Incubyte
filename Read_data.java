package incubyte;

import java.io.File;  
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFSheet;  
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;  
import org.apache.poi.ss.usermodel.FormulaEvaluator;  
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook; 

public class Read_data {
	
	
	public static void InsertRowInDB(String Name, 	long CustomerID,	long Opendate,	long Consulteddate,	String VaccineType,	
			String DrName,	String State, String Country,	long postCode,	long DOB,	char Active){
		try {
		    Connection myconn=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","Satya@1657");
		    myconn.setAutoCommit(false);
		    Statement mystmt=myconn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			 
		    Set<String> set = new HashSet<>(); 
		    ResultSet myrs=mystmt.executeQuery("SELECT Cust_I\r\n" + 
			 		"FROM Main\r\n");
		   while(myrs.next()) {
			   set.add(myrs.getString("Cust_I"));
		   }
		   if(!set.contains(String.valueOf(CustomerID))) {
			 PreparedStatement pstmt=myconn.prepareStatement("insert into main values(?,?,?,?,?,?,?,?,?,?,?)");
			 pstmt.setString(1, Name);
			 pstmt.setString(2, String.valueOf(CustomerID));
			 pstmt.setDate(3, new Date(Opendate));
			 pstmt.setDate(4, new Date(Consulteddate));
			 pstmt.setString(5, VaccineType);
			 pstmt.setString(6, DrName);
			 pstmt.setString(7, State);
			 pstmt.setString(8, Country);
			 pstmt.setLong(9, postCode);
			 pstmt.setDate(10, new Date(DOB));
			 pstmt.setString(11, String.valueOf(Active));
			 pstmt.setDate(3,new Date(904694400000L));//change
			 pstmt.executeUpdate();
			 myconn.commit();
			 
			 System.out.println("added Successfully...");
		   }
		 }
	 catch(Exception exc)
	    {
//		 	System.out.println("Some error occured...");
		 exc.printStackTrace();
	    }

    } 

	
	

	 public static void main(String[] args) throws IOException {
		 try {
	        String excelFilePath = "D:\\Incubyte\\sample_data.xlsx";
	        FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
	         
	        Workbook workbook = new XSSFWorkbook(inputStream);
	        org.apache.poi.ss.usermodel.Sheet firstSheet =  workbook.getSheetAt(0);
	        Iterator<Row> iterator = firstSheet.iterator();
	        
	        String Name="",  VaccineType="",	DrName="", State="", Country="";
            long Opendate,CustomerID,Consulteddate,postCode, DOB; char Active;
	        
	        while (iterator.hasNext()) {
	        	
	            Row row = iterator.next();
	            
	            Name=row.getCell(0).getStringCellValue();
	            System.out.println("Name = "+Name);
	            CustomerID=(long)row.getCell(1).getNumericCellValue();
	            Opendate=(long)row.getCell(2).getNumericCellValue(); 
	            Consulteddate=(long)row.getCell(3).getNumericCellValue();
	            VaccineType=row.getCell(4).getStringCellValue();
	            DrName=row.getCell(5).getStringCellValue();
	            State = row.getCell(6).getStringCellValue();
	            Country =row.getCell(7).getStringCellValue();
	            postCode=(long)row.getCell(8).getNumericCellValue();
	            DOB=(long)row.getCell(9).getNumericCellValue();
	           // System.out.println("Name = "+DOB);
	            Active=row.getCell(10).getStringCellValue().charAt(0);
	          
//	                        System.out.print(cell.getStringCellValue());
//	                        System.out.print(cell.getBooleanCellValue());
//	                        System.out.print(cell.getNumericCellValue());
	                        
	              
	           InsertRowInDB(Name,CustomerID,Opendate,Consulteddate,VaccineType,DrName,State,Country,postCode,DOB,Active);
	            } 
	        workbook.close();
	        inputStream.close();
	 		 }catch(Exception e) {
//			 e.printStackTrace();
	 			 System.out.println("Some Error occured...");
		  }
	    }
	 }

