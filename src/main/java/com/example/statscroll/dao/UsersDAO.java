package com.example.statscroll.dao;

import com.example.statscroll.model.Users;
import java.sql.*;

public class UsersDAO {
    private final String DB_URL = "jdbc:h2:./statScrolldb";
    private final String DB_USER = "sa";
    private final String DB_PASSWORD = "";

    public UsersDAO() {
        createTableIfNotExists();
        verifyTableStructure();
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    private void createTableIfNotExists() {
        String sql = """
            CREATE TABLE IF NOT EXISTS USERS (
                id INT AUTO_INCREMENT PRIMARY KEY,
                username VARCHAR(50) UNIQUE NOT NULL,
                password VARCHAR(100) NOT NULL,
                email VARCHAR(100) UNIQUE NOT NULL,
                is_admin BOOLEAN DEFAULT FALSE,
                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
            );
            """;

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.err.println("Errore creazione tabella: " + e.getMessage());
        }
    }

    private void verifyTableStructure() {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT is_admin FROM USERS LIMIT 1")) {
            // Verifica riuscita, la colonna esiste
        } catch (SQLException e) {
            try (Statement stmt = getConnection().createStatement()) {
                stmt.execute("DROP TABLE IF EXISTS USERS");
                createTableIfNotExists();
            } catch (SQLException ex) {
                System.err.println("Errore ricreazione tabella: " + ex.getMessage());
            }
        }
    }

    public boolean register(Users user) {
        String sql = "INSERT INTO USERS (username, password, email) VALUES (?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, user.getUsername());
            ps.setString(2, hashPassword(user.getPassword()));
            ps.setString(3, user.getEmail());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Errore registrazione: " + e.getMessage());
            return false;
        }
    }

    public Users login(String username, String password) {
        String sql = "SELECT id, username, password, is_admin FROM USERS WHERE username = ?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String storedHash = rs.getString("password");
                    if (verifyPassword(password, storedHash)) {
                        Users user = new Users();
                        user.setId(rs.getInt("id"));
                        user.setUsername(rs.getString("username"));
                        user.setAdmin(rs.getBoolean("is_admin"));
                        return user;
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Errore login: " + e.getMessage());
        }
        return null;
    }

    public void save(Users user) {
        try {
            String sql = "INSERT INTO USERS (username, password, email, is_admin) VALUES (?, ?, ?, ?)";
            try (Connection conn = getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, user.getUsername());
                ps.setString(2, hashPassword(user.getPassword()));
                ps.setString(3, user.getEmail());
                ps.setBoolean(4, user.isAdmin());
                ps.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Errore durante il salvataggio dell'utente", e);
        }
    }

    public boolean usernameExists(String username) {
        return checkExists("username", username);
    }

    public boolean emailExists(String email) {
        return checkExists("email", email);
    }

    private boolean checkExists(String field, String value) {
        String sql = "SELECT COUNT(*) FROM USERS WHERE " + field + " = ?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, value);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            System.err.println("Errore verifica esistenza: " + e.getMessage());
        }
        return false;
    }

    private String hashPassword(String password) {
        return Integer.toString(password.hashCode());
    }

    private boolean verifyPassword(String input, String storedHash) {
        return hashPassword(input).equals(storedHash);
    }
}