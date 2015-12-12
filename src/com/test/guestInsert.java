package com.test;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class guestInsert {

/// question 5

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
			System.out.println("mobile number : ");
			String mobile_number = sc.next();
			System.out.println("Email ID : ");
			String email_id = sc.next();
			String Password = last_name;
			String Sql2 = "INSERT INTO GUEST VALUES('" + First_name + "','" + last_name + "','" + mobile_number + "','" + email_id + "')";
			PreparedStatement stmt2 = conn.prepareStatement(Sql2);
			ResultSet res = stmt2.executeQuery();
			System.out.println("sucessfully registered as guest");

			// to buy tickets

			System.out.println("select the movie details");
			String sql3 = "select theatre.theatre_name,shows.screen_number,shows.show_date_time,movies.movie_name from shows,theatre,movies where movies.theatre_id=theatre.theatre_id and shows.theatre_id=theatre.theatre_id"; // andshows.movie_id=movies.movie_id
			PreparedStatement stmt3 = conn.prepareStatement(sql3);
			ResultSet res3 = stmt3.executeQuery();
			while (res3.next()) {
				String movie_name = res3.getString("movie_name");
				String theatre_name = res3.getString("theatre_name");
				String show_time = res3.getString("show_date_time");
				String screen_no = res3.getString("screen_number");
				System.out.println(movie_name + "\t\t" + theatre_name + "\t\t" + show_time + "\t\t" + screen_no + "");
			}
			System.out.println("please enter movie name, theater, show time and scree number");
			String n = sc.next();
			String t = sc.next();
			String sh = sc.next();
			String sn = sc.next();

			String Sql6 = "SELECT MAX(booking_ID) AS NEW_booking_ID FROM bookinghistory_guest";
			PreparedStatement stmt = conn.prepareStatement(Sql6);
			ResultSet res1 = stmt.executeQuery();
			int booking_ID = 0;
			while (res1.next()) {
				booking_ID = res1.getInt("NEW_booking_ID") + 1;
			}
			System.out.println("guest booking ID is: " + booking_ID);

			String Sql5 = "INSERT INTO bookinghistory_guest VALUES('" + booking_ID + "','" + n + "','" + t + "','" + sh + "','"
					+ sn + "')";
			PreparedStatement stmt5 = conn.prepareStatement(Sql5);
			ResultSet res5 = stmt5.executeQuery();

			System.out.println("sucessfully booked");

		}
	}


