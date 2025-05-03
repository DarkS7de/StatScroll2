package com.example.statscroll.dao;

import com.example.statscroll.model.CharacterWeapons;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CharacterWeaponsDAO {
    private final String url = "jdbc:h2:./data/statScrollDB";

    public CharacterWeaponsDAO() {
        try (Connection conn = DriverManager.getConnection(url, "sa", "");
             Statement stmt = conn.createStatement()) {

            stmt.execute("""
                CREATE TABLE IF NOT EXISTS character_weapons (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    character_id INT,
                    weapon_id INT
                )
            """);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<CharacterWeapons> getAll() {
        List<CharacterWeapons> weapons = new ArrayList<>();
        String sql = "SELECT * FROM character_weapons";

        try (Connection conn = DriverManager.getConnection(url, "sa", "");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                weapons.add(mapResultSetToCharacterWeapons(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return weapons;
    }

    public CharacterWeapons getById(int id) {
        String sql = "SELECT * FROM character_weapons WHERE id = ?";
        CharacterWeapons weapon = null;

        try (Connection conn = DriverManager.getConnection(url, "sa", "");
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                weapon = mapResultSetToCharacterWeapons(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return weapon;
    }

    public List<CharacterWeapons> getByCharacterId(int characterId) {
        List<CharacterWeapons> weapons = new ArrayList<>();
        String sql = "SELECT * FROM character_weapons WHERE character_id = ?";

        try (Connection conn = DriverManager.getConnection(url, "sa", "");
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, characterId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                weapons.add(mapResultSetToCharacterWeapons(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return weapons;
    }

    public void save(CharacterWeapons cw) {
        String sql = "INSERT INTO character_weapons (character_id, weapon_id) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(url, "sa", "");
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, cw.getCharacter_id());
            ps.setInt(2, cw.getWeapon_id());
            ps.executeUpdate();

            ResultSet keys = ps.getGeneratedKeys();
            if (keys.next()) {
                cw.setId(keys.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(CharacterWeapons cw) {
        String sql = "UPDATE character_weapons SET character_id = ?, weapon_id = ? WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(url, "sa", "");
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, cw.getCharacter_id());
            ps.setInt(2, cw.getWeapon_id());
            ps.setInt(3, cw.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM character_weapons WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(url, "sa", "");
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private CharacterWeapons mapResultSetToCharacterWeapons(ResultSet rs) throws SQLException {
        return new CharacterWeapons(
                rs.getInt("id"),
                rs.getInt("character_id"),
                rs.getInt("weapon_id")
        );
    }
}
