package com.example.statscroll.dao;

import com.example.statscroll.model.Spells;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SpellsDAO {
    private final String url = "jdbc:h2:./data/statScrollDB";

    public List<Spells> getAll() {
        List<Spells> spellsList = new ArrayList<>();
        String sql = "SELECT * FROM spells";

        try (Connection conn = DriverManager.getConnection(url, "sa", "");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                spellsList.add(mapResultSetToSpell(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return spellsList;
    }

    public Spells getById(int id) {
        String sql = "SELECT * FROM spells WHERE id = ?";
        Spells spell = null;

        try (Connection conn = DriverManager.getConnection(url, "sa", "");
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                spell = mapResultSetToSpell(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return spell;
    }

    public void save(Spells spell) {
        String sql = "INSERT INTO spells (id, name, level, school, cast_time, range, duration, verbal, somatic, material, material_cost, damage, healing, description) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, "sa", "");
             PreparedStatement ps = conn.prepareStatement(sql)) {

            setPreparedStatementFromSpell(ps, spell);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Spells spell) {
        String sql = "UPDATE spells SET name = ?, level = ?, school = ?, cast_time = ?, range = ?, duration = ?, verbal = ?, somatic = ?, material = ?, material_cost = ?, damage = ?, healing = ?, description = ? WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(url, "sa", "");
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, spell.getName());
            ps.setInt(2, spell.getLevel());
            ps.setString(3, spell.getSchool());
            ps.setString(4, spell.getCast_time());
            ps.setString(5, spell.getRange());
            ps.setString(6, spell.getDuration());
            ps.setBoolean(7, spell.isVerbal());
            ps.setBoolean(8, spell.isSomatic());
            ps.setBoolean(9, spell.isMaterial());
            ps.setString(10, spell.getMaterial_cost());
            ps.setString(11, spell.getDamage());
            ps.setString(12, spell.getHealing());
            ps.setString(13, spell.getDescription());
            ps.setInt(14, spell.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM spells WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(url, "sa", "");
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Spells mapResultSetToSpell(ResultSet rs) throws SQLException {
        return new Spells(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getInt("level"),
                rs.getString("school"),
                rs.getString("cast_time"),
                rs.getString("range"),
                rs.getString("duration"),
                rs.getBoolean("verbal"),
                rs.getBoolean("somatic"),
                rs.getBoolean("material"),
                rs.getString("material_cost"),
                rs.getString("damage"),
                rs.getString("healing"),
                rs.getString("description")
        );
    }

    private void setPreparedStatementFromSpell(PreparedStatement ps, Spells spell) throws SQLException {
        ps.setInt(1, spell.getId());
        ps.setString(2, spell.getName());
        ps.setInt(3, spell.getLevel());
        ps.setString(4, spell.getSchool());
        ps.setString(5, spell.getCast_time());
        ps.setString(6, spell.getRange());
        ps.setString(7, spell.getDuration());
        ps.setBoolean(8, spell.isVerbal());
        ps.setBoolean(9, spell.isSomatic());
        ps.setBoolean(10, spell.isMaterial());
        ps.setString(11, spell.getMaterial_cost());
        ps.setString(12, spell.getDamage());
        ps.setString(13, spell.getHealing());
        ps.setString(14, spell.getDescription());
    }
}
