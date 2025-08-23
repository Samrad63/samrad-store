//package com.samrad.servlet;
//
//
//
//import com.samrad.model.User;
//import com.samrad.repository.UserDAO;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.*;
//import java.io.IOException;
//import java.sql.Connection;
//import java.sql.DriverManager;
//
//public class RegisterServlet extends HttpServlet {
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//
//        String name = request.getParameter("name");
//        String email = request.getParameter("email");
//        String password = request.getParameter("password");
//
//        try {
//            Connection conn = DriverManager.getConnection(
//                    "jdbc:oracle:thin:@//localhost:1521/orclpdb1",
//                    "HESAM",
//                    "myjava123"
//            );
//            UserDAO dao = new UserDAO(conn);
//
//            // اگر کاربر با این ایمیل وجود نداشته باشه
//            if (dao.findByEmail(email) == null) {
//                User user = new User(name, email, password);
//                dao.register(user);
//                response.sendRedirect("login.html?registered=1"); // موفقیت
//            } else {
//                response.sendRedirect("register.html?error=exists"); // کاربر تکراری
//            }
//
//            conn.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//            response.sendRedirect("register.html?error=1");
//        }
//    }
//}
