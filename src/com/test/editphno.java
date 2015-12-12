package com.test;

/// question a 1

import java.sql.*;
import java.util.Scanner;

	public class editphno {

			public static void main(String k[]) throws SQLException{
				 	Scanner p = new Scanner(System.in);
				
			        String              url = "jdbc:oracle:thin:@fourier.cs.iit.edu:1521:orcl";
			        String              dbName = "FALL2015";
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
				   
		    System.out.println("Enter old phone number");
			String old = p.next();
			System.out.println("user Details are");
			String sql_cmnd="select first_name,last_name,email_id from userdetails where mobile_number='"+old+"'";
			PreparedStatement st = conn.prepareStatement(sql_cmnd);
			ResultSet res= st.executeQuery();
			String first_name= null;
			String last_name = null;
			String email_id =null;
			if(res.next()){
				first_name = res.getString("first_name");
				last_name = res.getString("last_name");
				email_id = res.getString("email_id");
			}
			System.out.println("first name "+first_name+"  Last Name "+last_name+" Email ID "+email_id);
			System.out.println("Enter new phone number");
			String phnTxt = p.next();
		
			String sql_cmnd1="update userdetails set mobile_number ='"+phnTxt+"' where mobile_number='"+old+"'";
			PreparedStatement st1 = conn.prepareStatement(sql_cmnd1);
			ResultSet res1= st1.executeQuery();
			System.out.println("mobile number is successfully updated");
			
			String sql_cmnd2="select first_name,last_name,mobile_number,email_id from userdetails where mobile_number='"+old+"'";
			PreparedStatement st2 = conn.prepareStatement(sql_cmnd2);
	        ResultSet res2= st2.executeQuery();

			}
     	
	
		 }
			
