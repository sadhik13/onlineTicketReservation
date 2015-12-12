package com.test;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

import oracle.sql.DATE;

import java.sql.Connection;
import java.sql.DriverManager;

public class regUserThread {

// question 2 and 4 
//	private static final String NULL = null;

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
		
	System.out.println("Enter your email id");
	String u = p.next();
	System.out.println("Enter your password");
	String pw = p.next();
int m=0;	
	 String sql_cmnd="select count(*) as count from userdetails where email_id='"+u+"'and password ='"+pw+"'";
	   PreparedStatement st = conn.prepareStatement(sql_cmnd);
	   ResultSet res= st.executeQuery();
	   while (res.next()) {
     m=res.getInt("count");    
	   }
	   String arg = "select user_id from userdetails where email_id ='"+u+"'";
	   PreparedStatement tmt = conn.prepareStatement(arg);
		ResultSet resu = tmt.executeQuery();
		while (resu.next()) {
			String uid = resu.getString("user_id");

	  // int i =Integer.parseInt(arg);
	   if(m!=0){
		   System.out.println("select 1. comment on existing thread\n 2. Create a new thread");
		   int a= p.nextInt();
		 //  String time;
		switch(a)
		   {
		   case 1:
			   //display the threads
			   String abc = "select thread_id,discussiondesc from discussion";
			   PreparedStatement smt = conn.prepareStatement(abc);
				ResultSet re = smt.executeQuery();
				while (re.next()) {
					String desc = re.getString("discussiondesc");
					String num = re.getString("thread_id");
					System.out.println(num+ " "+ desc );
				}
				//System.out.println("enter your name");
				//String name= p.next();
				System.out.println("select which thread you want to comment, please enter the thread id");
				int id= p.nextInt();
				// insert comment
				System.out.println("enter the comment");
				String comments = p.next();
			
			String Sql = "INSERT INTO thread_discussion(thread_id,comments,givenby) VALUES('" + id + "','" + comments +  "','" + uid + "')";
				PreparedStatement stmt = conn.prepareStatement(Sql);
				ResultSet r = stmt.executeQuery();

				System.out.println("sucessfully commented");
		   break;
		case 2: 
			//create new threads
			  
			String Sql1 = "SELECT MAX(thread_ID) AS NEW_thread_ID FROM discussion";
			PreparedStatement stmt1 = conn.prepareStatement(Sql1);
			ResultSet rs1 = stmt1.executeQuery();
			int thread_ID = 0;
			while (rs1.next()) {
				thread_ID = rs1.getInt("NEW_thread_ID") + 1;
			}
			System.out.println("New thread ID is: " + thread_ID);
				System.out.println("enter the description for thread you want to create");
				String description = p.next();
				System.out.println("enter the comments for above thread you created");
				
				String comment = p.next();
			   String Sql14 = "INSERT INTO discussion VALUES('" + thread_ID + "','" +uid+"','" + comment + "','" + description + "')";
				PreparedStatement stmt14 = conn.prepareStatement(Sql14);
				ResultSet res14 = stmt14.executeQuery();

				System.out.println("sucessfully created a new thread");
		
				break;
				
  }  
	   }
		else
		{
			System.out.println("you are not an user, you are not allowed to create or update threads");
			System.out.println("\n\n description \t\t\t comments\n");
			String v="select discussiondesc,thread_discussion.comments from discussion,thread_discussion where discussion.thread_id=thread_discussion.thread_id ";
					
			PreparedStatement stmt3 = conn.prepareStatement(v);
			ResultSet res3 = stmt3.executeQuery();
			while (res3.next()) {
				String descrip = res3.getString("discussiondesc");
				String c = res3.getString("comments");
			//	String show_time = res3.getString("show_date_time");
			//	String screen_no = res3.getString("screen_number");
				System.out.println( descrip + "\t\t" + c );
			
		}
	}
}
}
}