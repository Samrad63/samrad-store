package com.samrad.servlet;

import com.samrad.model.User;
import com.samrad.repository.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

public class UserServlet extends HttpServlet {
    private Connection connection;
    private UserDAO userDAO;

    @Override
    public void init() throws ServletException {
        try {
            connection = DriverManager.getConnection(
                    "jdbc:oracle:thin:@//localhost:1521/orclpdb1",
                    "HESAM",
                    "myjava123"
            );
            userDAO = new UserDAO(connection);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=UTF-8");
        String action = req.getParameter("action");

        try {
            if ("register".equals(action)) {
                String firstname = req.getParameter("firstname");
                String lastname = req.getParameter("lastname");
                String email = req.getParameter("email");
                String password = req.getParameter("password");

                User user = new User(firstname, lastname, email, password);
                boolean success = userDAO.register(user);

                if (success) {
                    resp.getWriter().write("{\"status\":\"success\"}");
                } else {
                    resp.getWriter().write("{\"status\":\"error\",\"message\":\"ایمیل قبلاً ثبت شده یا خطا رخ داده\"}");
                }

            } else if ("login".equals(action)) {
                String email = req.getParameter("email");
                String password = req.getParameter("password");

                User user = userDAO.login(email, password);
                if (user != null) {
                    HttpSession session = req.getSession();
                    session.setAttribute("user", user);

                    String json = String.format(
                            "{\"firstname\":\"%s\",\"lastname\":\"%s\",\"email\":\"%s\"}",
                            user.getFirstname(), user.getLastname(), user.getEmail()
                    );
                    resp.getWriter().write("{\"status\":\"success\",\"user\":" + json + "}");
                } else {
                    resp.getWriter().write("{\"status\":\"error\",\"message\":\"ایمیل یا رمز عبور اشتباه است.\"}");
                }
            } else {
                resp.getWriter().write("{\"status\":\"error\",\"message\":\"عملیات نامعتبر\"}");
            }

        } catch (Exception e) {
            e.printStackTrace(); // برای دیباگ
            resp.getWriter().write("{\"status\":\"error\",\"message\":\"خطای سرور! دوباره تلاش کنید\"}");
        }
    }

    @Override
    public void destroy() {
        try { if(connection != null) connection.close(); } catch (Exception ignored) {}
    }
}
