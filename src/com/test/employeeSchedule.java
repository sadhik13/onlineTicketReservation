package com.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class employeeSchedule {
	public static void main(String k[]) throws SQLException {
		Scanner p = new Scanner(System.in);

		String url = "jdbc:oracle:thin:@fourier.cs.iit.edu:1521:orcl";
		// String dbName = "FALL2015";
		String driver = "oracle.jdbc.driver.OracleDriver";
		String userName = "skanduk4";
		String password = "YCsBUo3...uK3Y15";
		Connection conn = null;

		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url, userName, password);
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
	  
	    if(m!=0)
	    {
	    	System.out.println("select among the below: \n 1. Assign the employee to theatre \n 2.Assign a job to employee");
	    	int a = p.nextInt();
	    	switch(a)
	    	{
	    		case 1:
	    			int n=0;
	    			System.out.println("enter the employe Last name to assign theater");
	    			String c=p.next();
	    			System.out.println("enter the employe First name ");
	    			String f=p.next();
	    			String ar = "select count(*) as count from staffdetails where staff_last_name ='"+c+"'";
	    			PreparedStatement tmt = conn.prepareStatement(ar);
	    			ResultSet resu = tmt.executeQuery();
				
	    			while (resu.next()) {
	    				n=resu.getInt("count");   
	    				}
	    			if(n!=0){
	    				System.out.println("already assigned to a theatre");
	    			}
	    			else
	    			{
//assign theatre
	    				String gh = "select staff_id from staffdetails where staff_last_name ='"+c+"'"; // and staff_first_name='"+f+"'";
	    				PreparedStatement mm = conn.prepareStatement(gh);
	    				ResultSet rs = mm.executeQuery();
	    				while (rs.next()) {
						String stid = rs.getString("staff_id");
	    				
						System.out.println("enter the theater name to be assigned");
						String tn = p.next();
						String cmnd = "select theatre_id from theatre where theatre_name='"+tn+"')";
				    	PreparedStatement stm = conn.prepareStatement(cmnd);
				        ResultSet res22= stm.executeQuery();
				        while (res22.next()) {
							String tnid = rs.getString("theatre_id");
				        
			//String tnid;
						String abc = "update staffdetails set theatre id = '"+tnid+"' where staff_id ='"+stid+"'";
							PreparedStatement s = conn.prepareStatement(abc);
							ResultSet r = s.executeQuery();
							System.out.println(f+ " is assigned to theatre "+ tn );
				        }
					}
			   }
			   break;
	    		case 2:
	    			int h=0;
	    			System.out.println("enter the employe Last name to assign theater");
	    			String ct=p.next();
	    			System.out.println("enter the employe First name ");
	    			String ft=p.next();
	    			System.out.println("enter the job title ");
	    			String tt=p.next();
	    			System.out.println("enter working day");
	    			String wh=p.next();
	    			int j=0;
	    			String arh = "select count(*) as count from staffdetails where function_type ='"+tt+"'" ;
	    			PreparedStatement mmm = conn.prepareStatement(arh);
	    			ResultSet rsus = mmm.executeQuery();
				
	    			while (rsus.next()) {
	    				j=rsus.getInt("count");   
	    				}
	    			if(j!=0){
	    				System.out.println("already assigned a job type, he cannot do "+tt+" type job");
	    			}
	    			else
	    			{
	    				System.out.println("already assigned a job type, he can do "+tt+" type job");
	    			}
	    			String ars = "select count(*) as count from staffdetails where function_type ='"+tt+"' and dutyday='"+wh+"'" ;
	    			PreparedStatement mmt = conn.prepareStatement(ars);
	    			ResultSet rsu = mmt.executeQuery();
				
	    			while (rsu.next()) {
	    				h=rsu.getInt("count");   
	    				}
	    			if(h!=0){
	    				System.out.println("already assigned a job on the same day");
	    			}
	    			else
	    			{		String Sql1 = "SELECT MAX(staff_ID) AS NEW_staff_ID FROM staffDETAILS";
	    			PreparedStatement stmt1 = conn.prepareStatement(Sql1);
	    			ResultSet rs1 = stmt1.executeQuery();
	    			int staff_ID = 0;
	    			while (rs1.next()) {
	    				staff_ID = rs1.getInt("NEW_staff_ID") + 1;
	    			}
	    			System.out.println("New staff ID is: " + staff_ID);
	    			
	    			String abcd = "select * from staffdetails where staff_first_name ='"+ft+"'";
	    			PreparedStatement sst = conn.prepareStatement(abcd);
	    			ResultSet rrr = sst.executeQuery();
	    			while (rrr.next()) {
	    			//	String staffID = rrr.getString("staff_ID");
	    				String staff_First_name = rrr.getString("staff_First_name");
	    				String staff_last_name = rrr.getString("staff_last_name");
	    				String address = rrr.getString("address");
	    				String Zipcode = rrr.getString("Zipcode");
	    				String start_time = rrr.getString("start_time");
	    				String theatre_id = rrr.getString("theatre_id");
	    				String funtion_type = rrr.getString("function_type");
	    				String dutyday = rrr.getString("dutyday");
	    				String end_time = rrr.getString("end_time");
	    	
	   	    			String Sql2 = "INSERT INTO staffdetails VALUES('" + staff_ID + "','" + staff_First_name + "','" + staff_last_name + "','"
	    					+ address + "','" + Zipcode + "','" + start_time + "','" + theatre_id + "','" + funtion_type + "','"
	    					+ dutyday + "','" + end_time + "')";
	    			PreparedStatement stmt2 = conn.prepareStatement(Sql2);
	    			ResultSet res6 = stmt2.executeQuery();
	    			
	    }
	    			
	    			
	    	}
	    }
	    }
	
	    else
		
			System.out.println("you are not manager");

	}
}



