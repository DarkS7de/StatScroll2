package com.example.statscroll.dao;

import com.example.statscroll.model.Proficiencies;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProficienciesDAO {
    private final String url = "jdbc:h2:./data/statScrollDB";

    public ProficienciesDAO() {
        try(Connection conn = DriverManager.getConnection(url, "sa", "");
            Statement stmt = conn.createStatement()){
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS proficiencies (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    name VARCHAR(50),
                    type VARCHAR(50)
                )
            """);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public List<Proficiencies> getAll() {
        List<Proficiencies> list = new ArrayList<>();
        String sql = "SELECT * FROM proficiencies";

        try (Connection conn = DriverManager.getConnection(url, "sa", "");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                list.add(mapResultSetToProficiency(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public Proficiencies getById(String id) {
        String sql = "SELECT * FROM proficiencies WHERE id = ?";
        Proficiencies proficiency = null;

        try (Connection conn = DriverManager.getConnection(url, "sa", "");
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                proficiency = mapResultSetToProficiency(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return proficiency;
    }

    public void save(Proficiencies proficiency) {
        String sql = "INSERT INTO proficiencies (id, name, type) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, "sa", "");
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, proficiency.getId());
            ps.setString(2, proficiency.getName());
            ps.setString(3, proficiency.getType());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Proficiencies proficiency) {
        String sql = "UPDATE proficiencies SET name = ?, type = ? WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(url, "sa", "");
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, proficiency.getName());
            ps.setString(2, proficiency.getType());
            ps.setString(3, proficiency.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(String id) {
        String sql = "DELETE FROM proficiencies WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(url, "sa", "");
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Proficiencies mapResultSetToProficiency(ResultSet rs) throws SQLException {
        return new Proficiencies(
                rs.getString("id"),
                rs.getString("name"),
                rs.getString("type")
        );
    }
}
