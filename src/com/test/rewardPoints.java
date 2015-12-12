package com.test;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
public class rewardPoints {

/// question 3
		public static void main(String k[]) throws SQLException {
			Scanner sc = new Scanner(System.in);

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
			System.out.println("---------------Register page--------------");
			System.out.println("Please enter details");
			System.out.println("User Name : ");
			String First_name = sc.next();
			System.out.println("last_name: ");
			String last_name = sc.next();
			System.out.println("Address : ");
			String address = sc.next();
			System.out.println("mobile number : ");
			String mobile_number = sc.next();
			System.out.println("balance: ");
			String balance = sc.next();
			System.out.println("Credit card number : ");
			String card_number = sc.next();
			System.out.println("Credit card type( Visa/Master card) : ");
			String card_type = sc.next();
			System.out.println("Email ID : ");
			String email_id = sc.next();
			String Password = last_name;
			String Sql1 = "SELECT MAX(USER_ID) AS NEW_USER_ID FROM USERDETAILS";
			PreparedStatement stmt1 = conn.prepareStatement(Sql1);
			ResultSet rs1 = stmt1.executeQuery();
			int USER_ID = 0;
			while (rs1.next()) {
				USER_ID = rs1.getInt("NEW_USER_ID") + 1;
			}
			System.out.println("New user ID is: " + USER_ID);
			String Sql2 = "INSERT INTO USERdetails VALUES('" + USER_ID + "','" + First_name + "','" + last_name + "','"
					+ address + "','" + mobile_number + "','" + Password + "','" + card_number + "','" + balance + "','"
					+ card_type + "','" + email_id + "')";
			PreparedStatement stmt2 = conn.prepareStatement(Sql2);
			ResultSet res = stmt2.executeQuery();
			System.out.println("sucessfully registered");

			// to view reward points
			int credits_earned = 100;
			String Sql5 = "INSERT INTO creditsearned (user_id,credits_earned) VALUES ('" + USER_ID + "','" + credits_earned +"')";
			PreparedStatement stmt5 = conn.prepareStatement(Sql5);
			ResultSet res5 = stmt5.executeQuery();			
			System.out.println("credits earned ="+ credits_earned);
			}
			
	}


