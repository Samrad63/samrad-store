package com.samrad.servlet;

import com.samrad.model.Product;
import com.samrad.repository.ProductDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

import com.google.gson.Gson;


public class ProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        try (PrintWriter out = response.getWriter()) {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            Connection connection = DriverManager.getConnection(
                    "jdbc:oracle:thin:@//localhost:1521/orclpdb1", "HESAM", "myjava123"
            );

            ProductDAO dao = new ProductDAO(connection);
            List<Product> products = dao.getAllProducts(); // دریافت همه محصولات

            // تبدیل لیست محصولات به JSON
            Gson gson = new Gson();
            String json = gson.toJson(products);

            out.print(json);
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().print("{\"error\":\"مشکل در دریافت محصولات\"}");
        }
    }
}
