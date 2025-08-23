package com.samrad.repository;

import com.samrad.model.User;
import java.sql.*;

public class UserDAO {
    private Connection conn;

    public UserDAO(Connection conn) {
        this.conn = conn;
    }

    // ثبت‌نام
    public boolean register(User user) {
        String sql = "INSERT INTO users (firstname, lastname, email, password, created_at) VALUES (?, ?, ?, ?, SYSTIMESTAMP)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getFirstname());
            stmt.setString(2, user.getLastname());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getPassword());
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // بروزرسانی اطلاعات کاربر
    public boolean update(User user) {
        try {
            if (user.getPassword() == null || user.getPassword().isEmpty()) {
                String sql = "UPDATE users SET firstname = ?, lastname = ? WHERE email = ?";
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setString(1, user.getFirstname());
                    stmt.setString(2, user.getLastname());
                    stmt.setString(3, user.getEmail());
                    int rows = stmt.executeUpdate();
                    return rows > 0;
                }
            } else {
                String sql = "UPDATE users SET firstname = ?, lastname = ?, password = ? WHERE email = ?";
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setString(1, user.getFirstname());
                    stmt.setString(2, user.getLastname());
                    stmt.setString(3, user.getPassword());
                    stmt.setString(4, user.getEmail());
                    int rows = stmt.executeUpdate();
                    return rows > 0;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // ورود
    public User login(String email, String password) {
        String sql = "SELECT * FROM users WHERE email=? AND password=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setFirstname(rs.getString("firstname"));
                user.setLastname(rs.getString("lastname"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
