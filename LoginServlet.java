package com.svit.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/url")
public class LoginServlet extends HttpServlet

{

	public String query="insert into login values(?,?)";
	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException 
	{
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/shops", "root", "sitaram");
			PreparedStatement pstmt = con.prepareStatement(query);
			String username=req.getParameter("username");
			String password=req.getParameter("password");
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			int result = pstmt.executeUpdate();
			if(result!=0) {
				res.sendRedirect("home.html");
			}
			else {
				pw.println(" not inserted");
			}
			
			 
		}
		
		catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
			
		
		
	}
}
