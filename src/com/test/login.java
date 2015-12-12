package com.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class login {

	/// a_2
	public static void main(String k[]) throws SQLException {
		Scanner p = new Scanner(System.in);

		String url = "jdbc:oracle:thin:@fourier.cs.iit.edu:1521:orcl";
		String dbName = "FALL2015";
		String driver = "oracle.jdbc.driver.OracleDriver";
		String userName = "skanduk4";
		String password = "YCsBUo3...uK3Y15";
		Connection conn = null;
		int m = 0;
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url, userName, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("select any number \n1 guest \n2 Admin \n3 User");
		int a = p.nextInt();
		String first_name;
		String email_id;
		String last_name;
		switch (a) {
		case 1:
			System.out.println("please enter your email id");
			String gemail = p.next();
			String sql_cmnd = "select count(*) as count from guest where email_id='" + gemail + "'";
			PreparedStatement st = conn.prepareStatement(sql_cmnd);
			ResultSet res = st.executeQuery();
			while (res.next()) {
				m = res.getInt("count");
			}
			// res.close();
			if (m != 0) {
				String sql_cmnd1 = "select * from guest where email_id = '" + gemail + "'";
				PreparedStatement st1 = conn.prepareStatement(sql_cmnd1);
				ResultSet res1 = st1.executeQuery();

				if (res1.next()) {
					first_name = res1.getString("first_name");
					last_name = res1.getString("last_name");
					email_id = res1.getString("email_id");

					System.out
							.println("first name " + first_name + "  Last Name " + last_name + " Email ID " + email_id);
				}
			} else
				System.out.println("you are not a guest, your permission to veiw this content is denied");
			break;

		case 2:
			System.out.println("please enter your email id");
			String aemail = p.next();
			String sql_cmnd2 = "select count(*) as count from admin ";// where
																		// admin_id=?
			PreparedStatement st2 = conn.prepareStatement(sql_cmnd2);
			ResultSet res2 = st2.executeQuery();
			while (res2.next()) {
				m = res2.getInt("count");
			}
			// res1.close();
			if (m != 0) {
				String sql_cmnd3 = "select * from guest ";
				PreparedStatement st3 = conn.prepareStatement(sql_cmnd3);
				ResultSet res3 = st3.executeQuery();
				while (res3.next()) {
					first_name = res3.getString("first_name");
					last_name = res3.getString("last_name");
					email_id = res3.getString("email_id");

					System.out
							.println("first name " + first_name + "  Last Name " + last_name + " Email ID " + email_id);
				}
			}
			// else

			// System.out.println("you are not a guest, your permission to veiw
			// this content is denied");
			break;
		case 3:
			System.out.println("please enter your email id");
			String uemail = p.next();
			String sql_cmnd4 = "select count(*) as count from userdetails where email_id='" + uemail + "'";
			PreparedStatement st4 = conn.prepareStatement(sql_cmnd4);
			ResultSet res4 = st4.executeQuery();
			while (res4.next()) {
				m = res4.getInt("count");
			}

			// res2.close();
			if (m != 0) {
				System.out.println(
						"you are not an admin or guest, your permission to veiw the content of guest is denied");
			} else {
				System.out.println("wrong email id");
			}
			break;
		}

	}
}
