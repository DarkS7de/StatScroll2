package com.example.statscroll.dao;

import com.example.statscroll.model.CharacterArmor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CharacterArmorDAO {
    private final String url = "jdbc:h2:./statscroll_db";

    public CharacterArmorDAO() {
        try (Connection conn = DriverManager.getConnection(url, "sa", "");
             Statement stmt = conn.createStatement()) {

            stmt.execute("""
                CREATE TABLE IF NOT EXISTS character_armor (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    character_id INT,
                    armor_id INT,
                    equipped BOOLEAN
                )
            """);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void save(CharacterArmor ca) {
        String sql = "INSERT INTO character_armor (character_id, armor_id, equipped) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, "sa", "");
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, ca.getCharacter_id());
            ps.setInt(2, ca.getArmor_id());
            ps.setBoolean(3, ca.isEquipped());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<CharacterArmor> findAll() {
        List<CharacterArmor> list = new ArrayList<>();
        String sql = "SELECT * FROM character_armor";

        try (Connection conn = DriverManager.getConnection(url, "sa", "");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                CharacterArmor ca = new CharacterArmor(
                        rs.getInt("id"),
                        rs.getInt("character_id"),
                        rs.getInt("armor_id"),
                        rs.getBoolean("equipped")
                );
                list.add(ca);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<CharacterArmor> findByCharacterId(int characterId) {
        List<CharacterArmor> list = new ArrayList<>();
        String sql = "SELECT * FROM character_armor WHERE character_id = ?";

        try (Connection conn = DriverManager.getConnection(url, "sa", "");
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, characterId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new CharacterArmor(
                        rs.getInt("id"),
                        rs.getInt("character_id"),
                        rs.getInt("armor_id"),
                        rs.getBoolean("equipped")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public void deleteById(int id) {
        String sql = "DELETE FROM character_armor WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(url, "sa", "");
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
