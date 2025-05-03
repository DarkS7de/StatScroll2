package com.example.statscroll.dao;

import com.example.statscroll.model.CharacterItems;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CharacterItemsDAO {
    private final String url = "jdbc:h2:./statscroll_db";

    public CharacterItemsDAO() {
        try (Connection conn = DriverManager.getConnection(url, "sa", "");
             Statement stmt = conn.createStatement()) {

            stmt.execute("""
                CREATE TABLE IF NOT EXISTS character_items (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    character_id INT,
                    item_id INT,
                    quantity INT
                )
            """);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void save(CharacterItems item) {
        String sql = "INSERT INTO character_items (character_id, item_id, quantity) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, "sa", "");
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, item.getCharacter_id());
            ps.setInt(2, item.getItem_id());
            ps.setInt(3, item.getQuantity());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<CharacterItems> findAll() {
        List<CharacterItems> list = new ArrayList<>();
        String sql = "SELECT * FROM character_items";

        try (Connection conn = DriverManager.getConnection(url, "sa", "");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new CharacterItems(
                        rs.getInt("id"),
                        rs.getInt("character_id"),
                        rs.getInt("item_id"),
                        rs.getInt("quantity")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<CharacterItems> findByCharacterId(int characterId) {
        List<CharacterItems> list = new ArrayList<>();
        String sql = "SELECT * FROM character_items WHERE character_id = ?";

        try (Connection conn = DriverManager.getConnection(url, "sa", "");
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, characterId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new CharacterItems(
                        rs.getInt("id"),
                        rs.getInt("character_id"),
                        rs.getInt("item_id"),
                        rs.getInt("quantity")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public void deleteById(int id) {
        String sql = "DELETE FROM character_items WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(url, "sa", "");
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
