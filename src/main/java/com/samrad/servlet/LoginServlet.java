package com.samrad.servlet;

import com.samrad.model.User;
import com.samrad.util.DBConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    private Connection connection;

    @Override
    public void init() throws ServletException {
        try {
            connection = DBConnection.getConnection();
        } catch (Exception e) {
            throw new ServletException("خطا در اتصال به دیتابیس", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");

        String email = req.getParameter("email");
        String password = req.getParameter("password");

        try {
            String sql = "SELECT firstname, lastname, email FROM users WHERE email = ? AND password = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                // ایجاد سشن
                HttpSession session = req.getSession();
                User user = new User(rs.getString("firstname"), rs.getString("lastname"), rs.getString("email"), null);
                session.setAttribute("user", user);

                // هدایت به داشبورد
                // هدایت به JSP واسطه برای پر کردن localStorage
                resp.sendRedirect(req.getContextPath() + "/setUser.jsp");


            } else {
                // هدایت به لاگین با پیام خطا
                resp.sendRedirect("login.html?error=1");
            }

        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect("login.html?error=2");
        }
    }
}
