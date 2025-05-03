package com.example.statscroll.dao;

import com.example.statscroll.model.Weapons;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WeaponsDAO {
    private final String url = "jdbc:h2:./data/statScrollDB";

    public List<Weapons> getAll() {
        List<Weapons> weaponsList = new ArrayList<>();
        String sql = "SELECT * FROM weapons";

        try (Connection conn = DriverManager.getConnection(url, "sa", "");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                weaponsList.add(mapResultSetToWeapon(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return weaponsList;
    }

    public Weapons getById(int id) {
        String sql = "SELECT * FROM weapons WHERE id = ?";
        Weapons weapon = null;

        try (Connection conn = DriverManager.getConnection(url, "sa", "");
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                weapon = mapResultSetToWeapon(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return weapon;
    }

    public void save(Weapons weapon) {
        String sql = "INSERT INTO weapons (id, name, damage, type) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, "sa", "");
             PreparedStatement ps = conn.prepareStatement(sql)) {

            setPreparedStatementFromWeapon(ps, weapon);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Weapons weapon) {
        String sql = "UPDATE weapons SET name = ?, damage = ?, type = ? WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(url, "sa", "");
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, weapon.getName());
            ps.setString(2, weapon.getDamage());
            ps.setString(3, weapon.getType());
            ps.setInt(4, weapon.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM weapons WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(url, "sa", "");
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Weapons mapResultSetToWeapon(ResultSet rs) throws SQLException {
        return new Weapons(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("damage"),
                rs.getString("type")
        );
    }

    private void setPreparedStatementFromWeapon(PreparedStatement ps, Weapons weapon) throws SQLException {
        ps.setInt(1, weapon.getId());
        ps.setString(2, weapon.getName());
        ps.setString(3, weapon.getDamage());
        ps.setString(4, weapon.getType());
    }
}
