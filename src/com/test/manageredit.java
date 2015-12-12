package com.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/// a-3
public class manageredit {
	public static void main(String k[]) throws SQLException{
	 	Scanner p = new Scanner(System.in);
	
        String              url = "jdbc:oracle:thin:@fourier.cs.iit.edu:1521:orcl";
     //   String              dbName = "FALL2015";
        String              driver = "oracle.jdbc.driver.OracleDriver";
        String              userName = "skanduk4"; 
        String              password = "YCsBUo3...uK3Y15";
        Connection          conn= null;
	        
 try {
           Class.forName(driver).newInstance();
           conn = DriverManager.getConnection(url,userName,password);
		} catch (Exception e) {
	            e.printStackTrace();
		}
 int m = 0;
 	System.out.println("Enter your email id");
 	String mgid = p.next();
 	
	   String sql_cmnd="select count(*) as count from manager where email_id='"+mgid+"'";
	   PreparedStatement st = conn.prepareStatement(sql_cmnd);
	   ResultSet res= st.executeQuery();
	   while (res.next()) {
       m=res.getInt("count");                        
    }                
  
    if(m!=0){
    	String cmnd = "select theatre_id from theatre where manager_id in (select manager_id from manager where email_id='"+mgid+"')";
    	PreparedStatement stm = conn.prepareStatement(cmnd);
        ResultSet resl= stm.executeQuery();
    	System.out.println("enter the details to be updated");
    
    	System.out.println("enter the movie name to be updated ");
    	String oldname = p.next();
    	
    	
    	int n=0;
    	 String sql="select count(*) as count from movies where movie_name='"+oldname+"'";
  	   PreparedStatement s = conn.prepareStatement(sql);
  	   ResultSet re= s.executeQuery();
  	   while (re.next()) {
         n=re.getInt("count");                        
      }                
  	   if(n==0){
    	System.out.println("movie name doesnot exist");
      }
    	else{
    	System.out.println("enter the new movie name ");
    	String name = p.next();
    	String sql_cmnd1="update movies set movie_name='"+name+"' where movie_name='"+oldname+"'";
    	PreparedStatement st1 = conn.prepareStatement(sql_cmnd1);
    	ResultSet res1= st1.executeQuery();

    	System.out.println("enter the new show timings: ");
    	String shwtm = p.next();
    	
    	String sql_cmnd5="update shows set show_date_time ='"+shwtm+"' where theatre_id in (select theatre_id from theatre where manager_id in (select manager_id from manager where email_id='"+mgid+"'))";
		
    	PreparedStatement st5 = conn.prepareStatement(sql_cmnd5);
    	ResultSet res5= st5.executeQuery();
		System.out.println("successfully updated");
	}
    }
    else
    {
    	System.out.println("wrong email id");
    }

}
}
