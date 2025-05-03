package com.example.statscroll.dao;

import com.example.statscroll.model.CharacterTraits;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CharacterTraitsDAO {
    private final String url = "jdbc:h2:./data/statScrollDB";

    public CharacterTraitsDAO() {
        try (Connection conn = DriverManager.getConnection(url, "sa", "");
             Statement stmt = conn.createStatement()) {

            stmt.execute("""
                CREATE TABLE IF NOT EXISTS character_traits (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    character_id INT,
                    type VARCHAR(100),
                    value VARCHAR(100)
                )
            """);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<CharacterTraits> getAll() {
        List<CharacterTraits> traits = new ArrayList<>();
        String sql = "SELECT * FROM character_traits";

        try (Connection conn = DriverManager.getConnection(url, "sa", "");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                traits.add(mapResultSetToCharacterTraits(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return traits;
    }

    public CharacterTraits getById(int id) {
        String sql = "SELECT * FROM character_traits WHERE id = ?";
        CharacterTraits trait = null;

        try (Connection conn = DriverManager.getConnection(url, "sa", "");
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                trait = mapResultSetToCharacterTraits(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return trait;
    }

    public List<CharacterTraits> getByCharacterId(int characterId) {
        List<CharacterTraits> traits = new ArrayList<>();
        String sql = "SELECT * FROM character_traits WHERE character_id = ?";

        try (Connection conn = DriverManager.getConnection(url, "sa", "");
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, characterId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                traits.add(mapResultSetToCharacterTraits(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return traits;
    }

    public void save(CharacterTraits ct) {
        String sql = "INSERT INTO character_traits (character_id, type, value) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, "sa", "");
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, ct.getCharacter_id());
            ps.setString(2, ct.getType());
            ps.setString(3, ct.getValue());
            ps.executeUpdate();

            ResultSet keys = ps.getGeneratedKeys();
            if (keys.next()) {
                ct.setId(keys.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(CharacterTraits ct) {
        String sql = "UPDATE character_traits SET character_id = ?, type = ?, value = ? WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(url, "sa", "");
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, ct.getCharacter_id());
            ps.setString(2, ct.getType());
            ps.setString(3, ct.getValue());
            ps.setInt(4, ct.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM character_traits WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(url, "sa", "");
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private CharacterTraits mapResultSetToCharacterTraits(ResultSet rs) throws SQLException {
        return new CharacterTraits(
                rs.getInt("id"),
                rs.getInt("character_id"),
                rs.getString("type"),
                rs.getString("value")
        );
    }
}
