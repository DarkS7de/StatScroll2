package com.example.statscroll.dao;

import com.example.statscroll.model.Users;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsersDAO {
    private final String url = "jdbc:h2:./statScrolldb";

    public UsersDAO() {
        try (Connection conn = DriverManager.getConnection(url, "sa", "");
        Statement stmt = conn.createStatement()){
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS USERS (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    username VARCHAR(50),
                    password VARCHAR(50),
                    email VARCHAR(100),
                    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
                )
            """);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void save(Users user) {
        String sql = "INSERT INTO USERS (username, password, email, created_at) VALUES (?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(url, "sa", "");
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getEmail());
            ps.setTimestamp(4, new Timestamp(user.getCreated_at().getTime()));
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Users> findAll() {
        List<Users> list = new ArrayList<>();
        String sql = "SELECT * FROM USERS";
        try (Connection conn = DriverManager.getConnection(url, "sa", "");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Users user = new Users(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getTimestamp("created_at")
                );
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
