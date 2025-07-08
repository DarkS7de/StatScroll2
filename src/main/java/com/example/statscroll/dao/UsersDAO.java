package com.example.statscroll.dao;

import com.example.statscroll.model.Users;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsersDAO {
    private final String url = "jdbc:h2:./statScrolldb";
    private final String user = "sa";
    private final String password = "";

    public UsersDAO() {
        initializeDatabase();
    }

    private void initializeDatabase() {
        String sql = """
            CREATE TABLE IF NOT EXISTS USERS (
                id INT AUTO_INCREMENT PRIMARY KEY,
                username VARCHAR(50) UNIQUE NOT NULL,
                password VARCHAR(100) NOT NULL,
                email VARCHAR(100) UNIQUE NOT NULL,
                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
            );
            """;

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            handleSQLException("Errore durante l'inizializzazione del database", e);
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public void save(Users user) {
        String sql = "INSERT INTO USERS (username, password, email, created_at) VALUES (?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, user.getUsername());
            ps.setString(2, hashPassword(user.getPassword()));
            ps.setString(3, user.getEmail());
            ps.setTimestamp(4, new Timestamp(user.getCreated_at().getTime()));

            int affectedRows = ps.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creazione utente fallita, nessuna riga modificata.");
            }

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    user.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creazione utente fallita, nessun ID ottenuto.");
                }
            }
        } catch (SQLException e) {
            handleSQLException("Errore durante il salvataggio dell'utente", e);
        }
    }

    public List<Users> findAll() {
        List<Users> users = new ArrayList<>();
        String sql = "SELECT * FROM USERS ORDER BY username";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                users.add(mapResultSetToUser(rs));
            }
        } catch (SQLException e) {
            handleSQLException("Errore durante il recupero degli utenti", e);
        }
        return users;
    }

    public boolean login(String username, String password) {
        String sql = "SELECT password FROM USERS WHERE username = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String storedHash = rs.getString("password");
                    return verifyPassword(password, storedHash);
                }
            }
        } catch (SQLException e) {
            handleSQLException("Errore durante il login", e);
        }
        return false;
    }

    public boolean emailExists(String email) {
        return checkFieldExists("email", email);
    }

    public boolean usernameExists(String username) {
        return checkFieldExists("username", username);
    }

    private boolean checkFieldExists(String field, String value) {
        String sql = String.format("SELECT COUNT(*) FROM USERS WHERE %s = ?", field);

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, value);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            handleSQLException("Errore durante la verifica dell'esistenza del campo " + field, e);
        }
        return false;
    }

    private Users mapResultSetToUser(ResultSet rs) throws SQLException {
        return new Users(
                rs.getInt("id"),
                rs.getString("username"),
                "", // Non restituiamo la password per sicurezza
                rs.getString("email"),
                rs.getTimestamp("created_at")
        );
    }

    private String hashPassword(String plainPassword) {
        // Implementazione base - in produzione usare BCrypt
        return Integer.toString(plainPassword.hashCode());
    }

    private boolean verifyPassword(String inputPassword, String storedHash) {
        return hashPassword(inputPassword).equals(storedHash);
    }

    private void handleSQLException(String message, SQLException e) {
        // Qui potresti loggare l'errore o lanciare un'eccezione personalizzata
        System.err.println(message + ": " + e.getMessage());
        e.printStackTrace();
    }

    // Metodo aggiuntivo per cercare utente per ID
    public Users findById(int id) {
        String sql = "SELECT * FROM USERS WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToUser(rs);
                }
            }
        } catch (SQLException e) {
            handleSQLException("Errore durante la ricerca dell'utente per ID", e);
        }
        return null;
    }
}