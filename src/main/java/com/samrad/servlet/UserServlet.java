package com.samrad.servlet;

import com.samrad.model.User;
import com.samrad.repository.UserDAO;
import com.samrad.util.DBConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
    private UserDAO userDAO;

    @Override
    public void init() throws ServletException {
        try {
            Connection connection = DBConnection.getConnection();
            userDAO = new UserDAO(connection);
        } catch (Exception e) {
            throw new ServletException("خطا در اتصال به دیتابیس", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("application/json;charset=UTF-8");
        String action = req.getParameter("action");
        System.out.println("Action received: " + action);


        try {
            if ("register".equals(action)) {
                handleRegister(req, resp);
            } else if ("login".equals(action)) {
                handleLogin(req, resp);
            } else {
                resp.getWriter().write("{\"status\":\"error\",\"message\":\"عملیات نامعتبر\"}");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().write("{\"status\":\"error\",\"message\":\"خطای سرور! دوباره تلاش کنید\"}");
        }
    }

    private void handleRegister(HttpServletRequest req, HttpServletResponse resp) throws IOException {
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
    }

    private void handleLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        User user = userDAO.login(email, password);
        if (user != null) {
            HttpSession session = req.getSession();
            session.setAttribute("user", user);

            String jsonUser = String.format(
                    "{\"firstname\":\"%s\",\"lastname\":\"%s\",\"email\":\"%s\"}",
                    user.getFirstname(), user.getLastname(), user.getEmail()
            );
            resp.getWriter().write("{\"status\":\"success\",\"user\":" + jsonUser + "}");
        } else {
            resp.getWriter().write("{\"status\":\"error\",\"message\":\"ایمیل یا رمز عبور اشتباه است.\"}");
        }
    }
}
