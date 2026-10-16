package com.scc.dao;

import com.scc.model.user;
import java.sql.*;

public class userDao {
    public boolean registerUser(String username, String passwordHash, String role) {
        String sql = "INSERT INTO Users (username, password_hash, role, status) VALUES (?, ?, ?, 'Active')";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, passwordHash);
            pstmt.setString(3, role);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public user login(String username, String passwordHash) {
        String sql = "SELECT * FROM Users WHERE username = ? AND password_hash = ? AND status = 'Active'";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, passwordHash);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new user(
                        rs.getInt("user_id"),
                        rs.getString("username"),
                        rs.getString("password_hash"),
                        rs.getString("role"),
                        rs.getString("status")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}