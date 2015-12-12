package com.test;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

import oracle.sql.DATE;

import java.sql.Connection;
import java.sql.DriverManager;

public class alertAdmin {

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
					// viewStaffDetails();
					String vw = "select * from staffdetails where function_type is null and dutyday='SUNDAY'";
					PreparedStatement pp = conn.prepareStatement(vw);
					// PreparedStatement pp= conn.preparedStatement(vw);
					ResultSet rr = pp.executeQuery();
					while (rr.next()) {
						String staff_id = rr.getString("staff_ID");
						String staff_First_name = rr.getString("staff_First_name");
						String staff_last_name = rr.getString("staff_last_name");
						String address = rr.getString("address");
						String Zipcode = rr.getString("Zipcode");
						String start_time = rr.getString("start_time");
						String theatre_id = rr.getString("theatre_id");
						String funtion_type = rr.getString("function_type");
						String dutyday = rr.getString("dutyday");
						String end_time = rr.getString("end_time");

						System.out.println(staff_id + "  " + staff_First_name + "  " + staff_last_name + "  " + address
								+ "  " + Zipcode + "  " + start_time + "  " + theatre_id + "  " + funtion_type + "  "
								+ dutyday + "  " + end_time);

					}
				}
				break;
			case 2:
				int n = 0;
				String sql = "select count(*) as count from manager where email_id='" + u + "'and mobile_number ='" + pw
						+ "'";
				PreparedStatement sst = conn.prepareStatement(sql);
				ResultSet rs = sst.executeQuery();
				while (rs.next()) {
					n = rs.getInt("count");
				}

				if (n != 0) {
					String vw = "select * from staffdetails where function_type is null and dutyday='FRIDAY'";
					PreparedStatement pp = conn.prepareStatement(vw);
					ResultSet rr = pp.executeQuery();
					while (rr.next()) {
						String staff_id = rr.getString("staff_id");
						String staff_First_name = rr.getString("staff_First_name");
						String staff_last_name = rr.getString("staff_last_name");
						String address = rr.getString("address");
						String Zipcode = rr.getString("Zipcode");
						String start_time = rr.getString("start_time");
						String theatre_id = rr.getString("theatre_id");
						String funtion_type = rr.getString("function_type");
						String dutyday = rr.getString("dutyday");
						String end_time = rr.getString("end_time");

						System.out.println(staff_id + "  " + staff_First_name + "  " + staff_last_name + "  " + address
								+ "  " + Zipcode + "  " + start_time + "  " + theatre_id + "  " + funtion_type + "  "
								+ dutyday + "  " + end_time);
					}
					break;
				}
				 else
				    {
				    	System.out.println("wrong email id");
				    }
 
			}
		} catch (Exception e) {
			 System.out.println("you are not admin nor manager, so you cannot view the details of staff");
			
		}

	}
}
