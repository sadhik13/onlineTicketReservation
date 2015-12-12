package com.test;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

import oracle.sql.DATE;

import java.sql.Connection;
import java.sql.DriverManager;

public class usertAlert {

	// question 9
	// private static final String NULL = null;

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
		System.out.println("Enter your phone number");
		String pw = p.next();
		System.out.println("1. Admin \n2. Manager \n select who you are:");
		int s = p.nextInt();
		try {
			switch (s) {
			case 1:
				int m = 0;
				String sql_cmnd = "select count(*) as count from admin where email_id='" + u + "'and mobile_number ='"
						+ pw + "'";
				PreparedStatement st = conn.prepareStatement(sql_cmnd);
				ResultSet res = st.executeQuery();
				while (res.next()) {
					m = res.getInt("count");
				}

				if (m != 0) {
					System.out.println("Enter user email id");
					String gemail = p.next();
					String sql_cmnd1 = "select membership_status,first_name,last_name,email_id from membershipstatus,userdetails where membershipstatus.user_id=userdetails.user_id and email_id='"
							+ gemail + "'";
					PreparedStatement st1 = conn.prepareStatement(sql_cmnd1);
					ResultSet res1 = st1.executeQuery();

					if (res1.next()) {
						String membership_status = res1.getString("membership_status");
						String first_name = res1.getString("first_name");
						String last_name = res1.getString("last_name");
						String email_id = res1.getString("email_id");

						System.out.println("membership_status " + membership_status + "first name " + first_name
								+ "  Last Name " + last_name + " Email ID " + email_id);
						System.out.println("Input the membership status that needs to be updated");
						String members = p.next();
						System.out.println("Membership status updated to : Platinum");
					}
					String memTxt = p.next();
					
			/*		String sql_cmnd11="update membershipstatus set membership_status ='"+memTxt+"' where mobile_number='"+old+"'";
					PreparedStatement st19 = conn.prepareStatement(sql_cmnd11);
					ResultSet res1= st19.executeQuery();
					System.out.println("mobile number is successfully updated");
					
				
				}
			}
*/
				}}				
		} catch (Exception e) {
			System.out.println("you are not admin nor manager, so you cannot view the details of staff");
		}}
}
