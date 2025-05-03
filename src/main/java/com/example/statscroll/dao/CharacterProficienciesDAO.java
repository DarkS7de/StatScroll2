package com.example.statscroll.dao;

import com.example.statscroll.model.CharacterProficiencies;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CharacterProficienciesDAO {
    private final String url = "jdbc:h2:./statscroll_db";

    public CharacterProficienciesDAO() {
        try (Connection conn = DriverManager.getConnection(url, "sa", "");
             Statement stmt = conn.createStatement()) {

            stmt.execute("""
                CREATE TABLE IF NOT EXISTS character_proficiencies (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    character_id INT,
                    proficiency_id INT,
                    expertise BOOLEAN
                )
            """);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void save(CharacterProficiencies cp) {
        String sql = "INSERT INTO character_proficiencies (character_id, proficiency_id, expertise) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, "sa", "");
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, cp.getCharacter_id());
            ps.setInt(2, cp.getProficiency_id());
            ps.setBoolean(3, cp.isExpertise());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<CharacterProficiencies> findAll() {
        List<CharacterProficiencies> list = new ArrayList<>();
        String sql = "SELECT * FROM character_proficiencies";

        try (Connection conn = DriverManager.getConnection(url, "sa", "");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new CharacterProficiencies(
                        rs.getInt("id"),
                        rs.getInt("character_id"),
                        rs.getInt("proficiency_id"),
                        rs.getBoolean("expertise")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<CharacterProficiencies> findByCharacterId(int characterId) {
        List<CharacterProficiencies> list = new ArrayList<>();
        String sql = "SELECT * FROM character_proficiencies WHERE character_id = ?";

        try (Connection conn = DriverManager.getConnection(url, "sa", "");
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, characterId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new CharacterProficiencies(
                        rs.getInt("id"),
                        rs.getInt("character_id"),
                        rs.getInt("proficiency_id"),
                        rs.getBoolean("expertise")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public void deleteById(int id) {
        String sql = "DELETE FROM character_proficiencies WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(url, "sa", "");
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
