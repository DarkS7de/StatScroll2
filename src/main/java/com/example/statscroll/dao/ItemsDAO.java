package com.example.statscroll.dao;

import com.example.statscroll.model.Items;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemsDAO {
    private final String url = "jdbc:h2:./data/statScrollDB";

    public List<Items> getAll() {
        List<Items> itemsList = new ArrayList<>();
        String sql = "SELECT * FROM items";

        try (Connection conn = DriverManager.getConnection(url, "sa", "");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                itemsList.add(mapResultSetToItem(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return itemsList;
    }

    public Items getById(int id) {
        String sql = "SELECT * FROM items WHERE id = ?";
        Items item = null;

        try (Connection conn = DriverManager.getConnection(url, "sa", "");
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                item = mapResultSetToItem(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return item;
    }

    public void save(Items item) {
        String sql = "INSERT INTO items (name, type1, type2, weight, price) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, "sa", "");
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, item.getName());
            ps.setString(2, item.getType1());
            ps.setString(3, item.getType2());
            ps.setInt(4, item.getWeight());
            ps.setInt(5, item.getPrice());
            ps.executeUpdate();

            ResultSet keys = ps.getGeneratedKeys();
            if (keys.next()) {
                item.setId(keys.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Items item) {
        String sql = "UPDATE items SET name = ?, type1 = ?, type2 = ?, weight = ?, price = ? WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(url, "sa", "");
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, item.getName());
            ps.setString(2, item.getType1());
            ps.setString(3, item.getType2());
            ps.setInt(4, item.getWeight());
            ps.setInt(5, item.getPrice());
            ps.setInt(6, item.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM items WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(url, "sa", "");
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Items mapResultSetToItem(ResultSet rs) throws SQLException {
        return new Items(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("type1"),
                rs.getString("type2"),
                rs.getInt("weight"),
                rs.getInt("price")
        );
    }
}
