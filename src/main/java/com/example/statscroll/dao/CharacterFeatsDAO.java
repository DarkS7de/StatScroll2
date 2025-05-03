package com.example.statscroll.dao;

import com.example.statscroll.model.CharacterFeats;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CharacterFeatsDAO {
    private final String url = "jdbc:h2:./statscroll_db";

    public CharacterFeatsDAO() {
        try (Connection conn = DriverManager.getConnection(url, "sa", "");
             Statement stmt = conn.createStatement()) {

            stmt.execute("""
                CREATE TABLE IF NOT EXISTS character_feats (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    character_id INT,
                    feat_name VARCHAR(100),
                    value VARCHAR(100)
                )
            """);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void save(CharacterFeats feat) {
        String sql = "INSERT INTO character_feats (character_id, feat_name, value) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, "sa", "");
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, feat.getCharacter_id());
            ps.setString(2, feat.getFeat_name());
            ps.setString(3, feat.getValue());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<CharacterFeats> findAll() {
        List<CharacterFeats> feats = new ArrayList<>();
        String sql = "SELECT * FROM character_feats";

        try (Connection conn = DriverManager.getConnection(url, "sa", "");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                feats.add(new CharacterFeats(
                        rs.getInt("id"),
                        rs.getInt("character_id"),
                        rs.getString("feat_name"),
                        rs.getString("value")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return feats;
    }

    public List<CharacterFeats> findByCharacterId(int characterId) {
        List<CharacterFeats> feats = new ArrayList<>();
        String sql = "SELECT * FROM character_feats WHERE character_id = ?";

        try (Connection conn = DriverManager.getConnection(url, "sa", "");
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, characterId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                feats.add(new CharacterFeats(
                        rs.getInt("id"),
                        rs.getInt("character_id"),
                        rs.getString("feat_name"),
                        rs.getString("value")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return feats;
    }

    public void deleteById(int id) {
        String sql = "DELETE FROM character_feats WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(url, "sa", "");
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
