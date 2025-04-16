package com.svit.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/url1")
public class PriceCompare extends HttpServlet
{
	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException
	{
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();
		try {
		int price1 = Integer.parseInt(req.getParameter("price1"));
		int price2 = Integer.parseInt(req.getParameter("price2"));
		int price3 = Integer.parseInt(req.getParameter("price3"));
		int minPrice=0;
		
		if(price2 <= price1 && price2 <= price3) {
			minPrice=price2;
		}
		else if(price1 <= price2 && price1 <= price3) {
			minPrice=price1;
		}
		else if( price3 <= price1 && price3 <= price2) {
			minPrice=price1;
		}
		pw.println( minPrice );
		}
		catch(NumberFormatException e) {
			e.printStackTrace();
		}
	}
	
}
