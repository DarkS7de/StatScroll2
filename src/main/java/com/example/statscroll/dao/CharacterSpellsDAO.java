package com.example.statscroll.dao;

import com.example.statscroll.model.CharacterSpells;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CharacterSpellsDAO {
    private final String url = "jdbc:h2:./data/statScrollDB";

    public CharacterSpellsDAO() {
        try (Connection conn = DriverManager.getConnection(url, "sa", "");
             Statement stmt = conn.createStatement()) {

            stmt.execute("""
                CREATE TABLE IF NOT EXISTS character_feats (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    character_id INT,
                    spell_id INT,
                    prepared BOOLEAN
                )
            """);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<CharacterSpells> getAll() {
        List<CharacterSpells> spells = new ArrayList<>();
        String sql = "SELECT * FROM character_spells";

        try (Connection conn = DriverManager.getConnection(url, "sa", "");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                spells.add(mapResultSetToCharacterSpells(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return spells;
    }

    public CharacterSpells getById(int id) {
        String sql = "SELECT * FROM character_spells WHERE id = ?";
        CharacterSpells spell = null;

        try (Connection conn = DriverManager.getConnection(url, "sa", "");
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                spell = mapResultSetToCharacterSpells(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return spell;
    }

    public void save(CharacterSpells cs) {
        String sql = "INSERT INTO character_spells (character_id, spell_id, prepared) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, "sa", "");
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, cs.getCharacter_id());
            ps.setInt(2, cs.getSpell_id());
            ps.setBoolean(3, cs.isPrepared());
            ps.executeUpdate();

            ResultSet keys = ps.getGeneratedKeys();
            if (keys.next()) {
                cs.setId(keys.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(CharacterSpells cs) {
        String sql = "UPDATE character_spells SET character_id = ?, spell_id = ?, prepared = ? WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(url, "sa", "");
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, cs.getCharacter_id());
            ps.setInt(2, cs.getSpell_id());
            ps.setBoolean(3, cs.isPrepared());
            ps.setInt(4, cs.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM character_spells WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(url, "sa", "");
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private CharacterSpells mapResultSetToCharacterSpells(ResultSet rs) throws SQLException {
        return new CharacterSpells(
                rs.getInt("id"),
                rs.getInt("character_id"),
                rs.getInt("spell_id"),
                rs.getBoolean("prepared")
        );
    }

    private List<CharacterSpells> getAllByCharacter(int character_id) {
        List<CharacterSpells> spells = new ArrayList<>();
        String sql = "SELECT * FROM character_spells WHERE character_id = ?";
        try(Connection conn = DriverManager.getConnection(url, "sa", "");
            PreparedStatement ps = conn.prepareStatement(sql);
        ){
            ps.setInt(1, character_id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                spells.add(mapResultSetToCharacterSpells(rs));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return spells;
    }
}
