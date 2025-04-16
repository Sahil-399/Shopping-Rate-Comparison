package com.svit.servlet;
import java.io.IOException;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/AddToCart")
public class CartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String productName = request.getParameter("productName");
        String price = request.getParameter("price");
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId"); // Assuming user is logged in
        
        if (userId == null) {
            out.println("<script>alert('Please login first!'); window.location='login.html';</script>");
            return;
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/yourdatabase","root","sitaram");
            String sql = "INSERT INTO cart(user_id, product_name, price) VALUES (?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, userId);
            pst.setString(2, productName);
            pst.setString(3, price);
            
            int i = pst.executeUpdate();
            if (i > 0) {
                out.println("<script>alert('Added to cart successfully!'); window.location='cart.jsp';</script>");
            } else {
                out.println("<script>alert('Failed to add to cart!'); window.location='index.html';</script>");
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            out.println("<script>alert('Error occurred!'); window.location='index.html';</script>");
        }
    }
}
