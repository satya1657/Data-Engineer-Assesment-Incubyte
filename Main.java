package incubyte;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections4.map.HashedMap;

import jdbcconnection.newtable;

public class Main {

	public static void main(String[] args) {
		try {
		    Connection myconn=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","Satya@1657");
		    Statement mystmt=myconn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		//------------------------------------------------------------------------------------------    
		   System.out.println();
		   
		   Map<String,String> map = new HashMap<>();
		   Set<String> set = new HashSet<>(); 
		   
		 ResultSet myrs=mystmt.executeQuery("SELECT distinct country\r\n" + 
		 		"FROM Main\r\n");
		  while(myrs.next()) {
			  String t = "Table_"+myrs.getString("country");
			  map.put(t,myrs.getString("country")); 
		      System.out.println(t);
		  }
		  ResultSet tables =mystmt.executeQuery("Show tables");
		  
		  while(tables.next()) {
			  set.add(tables.getString("Tables_in_hospital"));
		  }
		
		  for(String k : map.keySet()) {
		  if(!set.contains(k.toLowerCase())){
			  mystmt.executeUpdate("create table "+k+" \r\n" + " like Main\r\n");
			  String st = "INSERT INTO "+k+" SELECT * FROM Main WHERE country= '"+map.get(k)+"';";
			  mystmt.executeUpdate(st);
		     }

		     
		  }
		  
		 System.out.println("done...");

	}catch(Exception exc)
	{
		exc.printStackTrace();
	 }
   }
}
