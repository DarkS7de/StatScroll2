package com.example.statscroll.dao;

import com.example.statscroll.model.Armor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArmorDAO {
    private final String url = "jdbc:h2:./statscroll_db";

    public ArmorDAO() {
        try (Connection conn = DriverManager.getConnection(url, "sa", "");
             Statement stmt = conn.createStatement()) {

            stmt.execute("""
                CREATE TABLE IF NOT EXISTS armor (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    name VARCHAR(100),
                    base_ac INT,
                    dex_cap INT,
                    type VARCHAR(50)
                )
            """);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void save(Armor armor) {
        String sql = "INSERT INTO armor (name, base_ac, dex_cap, type) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, "sa", "");
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, armor.getName());
            ps.setInt(2, armor.getBase_ac());
            ps.setInt(3, armor.getDex_cap());
            ps.setString(4, armor.getType());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Armor> findAll() {
        List<Armor> list = new ArrayList<>();
        String sql = "SELECT * FROM armor";

        try (Connection conn = DriverManager.getConnection(url, "sa", "");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Armor armor = new Armor(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("base_ac"),
                        rs.getInt("dex_cap"),
                        rs.getString("type")
                );
                list.add(armor);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public Armor findById(int id) {
        String sql = "SELECT * FROM armor WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(url, "sa", "");
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Armor(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("base_ac"),
                        rs.getInt("dex_cap"),
                        rs.getString("type")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void deleteById(int id) {
        String sql = "DELETE FROM armor WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(url, "sa", "");
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
