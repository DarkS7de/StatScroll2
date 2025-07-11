package com.example.statscroll.dao;

import com.example.statscroll.model.Characters;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CharactersDAO {
    private final String url = "jdbc:h2:./statScrolldb";

    public CharactersDAO() {
        try (Connection conn = DriverManager.getConnection(url, "sa", "");
             Statement stmt = conn.createStatement()) {

            stmt.execute("""
            CREATE TABLE IF NOT EXISTS characters (
                id INT AUTO_INCREMENT PRIMARY KEY,
                user_id INT NOT NULL,
                name VARCHAR NOT NULL,
                char_class VARCHAR NOT NULL,
                level INT DEFAULT 1,
                race VARCHAR NOT NULL,
                initiative INT DEFAULT 0,
                speed INT DEFAULT 30,
                str INT DEFAULT 10,
                dex INT DEFAULT 10,
                con INT DEFAULT 10,
                intel INT DEFAULT 10,
                wis INT DEFAULT 10,
                cha INT DEFAULT 10,
                inspiration BOOLEAN DEFAULT FALSE,
                profbonus INT DEFAULT 2,
                maxhp INT DEFAULT 10,
                hitdice VARCHAR,
                age VARCHAR,
                eyes VARCHAR,
                hair VARCHAR,
                skin VARCHAR,
                height VARCHAR,
                weight VARCHAR,
                inventory TEXT,
                portrait_url VARCHAR
            )
        """);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Errore durante la creazione della tabella characters", e);
        }
    }

    public void save(Characters character) {
        String sql = """
        INSERT INTO characters (
            user_id, name, char_class, level, race, initiative, speed,
            str, dex, con, intel, wis, cha, inspiration, profbonus,
            maxhp, hitdice, age, eyes, hair, skin, height, weight, 
            inventory, portrait_url
        ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
    """;

        try (Connection conn = DriverManager.getConnection(url, "sa", "");
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            // Imposta i 25 parametri
            ps.setString(1, character.getUser_id());
            ps.setString(2, character.getName());
            ps.setString(3, character.getChar_class());
            ps.setInt(4, character.getLevel());
            ps.setString(5, character.getRace());
            ps.setInt(6, character.getInitiative());
            ps.setInt(7, character.getSpeed());
            ps.setInt(8, character.getStr());
            ps.setInt(9, character.getDex());
            ps.setInt(10, character.getCon());
            ps.setInt(11, character.getIntel());
            ps.setInt(12, character.getWis());
            ps.setInt(13, character.getCha());
            ps.setBoolean(14, character.isInspiration());
            ps.setInt(15, character.getProfbonus());
            ps.setInt(16, character.getMaxhp());
            ps.setString(17, character.getHitdice());
            ps.setString(18, character.getAge());
            ps.setString(19, character.getEyes());
            ps.setString(20, character.getHair());
            ps.setString(21, character.getSkin());
            ps.setString(22, character.getHeight());
            ps.setString(23, character.getWeight());
            ps.setString(24, character.getInventory());
            ps.setString(25, character.getPortrait_url());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    character.setId(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Errore durante il salvataggio del personaggio", e);
        }
    }

    public void update(Characters character) {
        String sql = """
        UPDATE characters SET
            user_id = ?, name = ?, char_class = ?, level = ?, race = ?,
            initiative = ?, speed = ?, str = ?, dex = ?, con = ?, intel = ?,
            wis = ?, cha = ?, inspiration = ?, profbonus = ?, maxhp = ?,
            hitdice = ?, age = ?, eyes = ?, hair = ?, skin = ?, height = ?,
            weight = ?, inventory = ?, portrait_url = ?
        WHERE id = ?
    """;

        try (Connection conn = DriverManager.getConnection(url, "sa", "");
             PreparedStatement ps = conn.prepareStatement(sql)) {

            // Impostazione dei 24 parametri del SET (1-24)
            ps.setString(1, character.getUser_id());
            ps.setString(2, character.getName());
            ps.setString(3, character.getChar_class());
            ps.setInt(4, character.getLevel());
            ps.setString(5, character.getRace());
            ps.setInt(6, character.getInitiative());
            ps.setInt(7, character.getSpeed());
            ps.setInt(8, character.getStr());
            ps.setInt(9, character.getDex());
            ps.setInt(10, character.getCon());
            ps.setInt(11, character.getIntel());
            ps.setInt(12, character.getWis());
            ps.setInt(13, character.getCha());
            ps.setBoolean(14, character.isInspiration());
            ps.setInt(15, character.getProfbonus());
            ps.setInt(16, character.getMaxhp());
            ps.setString(17, character.getHitdice());
            ps.setString(18, character.getAge());
            ps.setString(19, character.getEyes());
            ps.setString(20, character.getHair());
            ps.setString(21, character.getSkin());
            ps.setString(22, character.getHeight());
            ps.setString(23, character.getWeight());
            ps.setString(24, character.getInventory());
            ps.setString(25, character.getPortrait_url());
            ps.setInt(26, character.getId());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Aggiornamento fallito, nessuna riga modificata");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Errore durante l'aggiornamento del personaggio", e);
        }
    }

    private void setCommonParameters(PreparedStatement ps, Characters character) throws SQLException {
        ps.setString(1, character.getUser_id());
        ps.setString(2, character.getName());
        ps.setString(3, character.getChar_class());
        ps.setInt(4, character.getLevel());
        ps.setString(5, character.getRace());
        ps.setInt(6, character.getInitiative());
        ps.setInt(7, character.getSpeed());
        ps.setInt(8, character.getStr());
        ps.setInt(9, character.getDex());
        ps.setInt(10, character.getCon());
        ps.setInt(11, character.getIntel());
        ps.setInt(12, character.getWis());
        ps.setInt(13, character.getCha());
        ps.setBoolean(14, character.isInspiration());
        ps.setInt(15, character.getProfbonus());
        ps.setInt(16, character.getMaxhp());
        ps.setString(17, character.getHitdice());
        ps.setString(18, character.getAge());
        ps.setString(19, character.getEyes());
        ps.setString(20, character.getHair());
        ps.setString(21, character.getSkin());
        ps.setString(22, character.getHeight());
        ps.setString(23, character.getWeight());
        ps.setString(24, character.getInventory());
        ps.setString(25, character.getPortrait_url());
        ps.setInt(26, character.getId());
    }

    public Characters findById(int id) {
        String sql = "SELECT * FROM characters WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(url, "sa", "");
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapResultSetToCharacter(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Characters> findByUserId(Integer userId) {
        List<Characters> characters = new ArrayList<>();
        String sql = "SELECT * FROM characters WHERE user_id = ?";

        try (Connection conn = DriverManager.getConnection(url, "sa", "");
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                characters.add(mapResultSetToCharacter(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return characters;
    }

    public List<Characters> findAll() {
        List<Characters> characters = new ArrayList<>();
        String sql = "SELECT * FROM characters";

        try (Connection conn = DriverManager.getConnection(url, "sa", "");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                characters.add(mapResultSetToCharacter(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return characters;
    }

    /**
     * Elimina un personaggio dal database in base al suo ID
     * @param id L'ID del personaggio da eliminare
     * @return true se l'eliminazione è avvenuta con successo, false altrimenti
     */
    public boolean deleteById(int id) {
        String sql = "DELETE FROM characters WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(url, "sa", "");
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private Characters mapResultSetToCharacter(ResultSet rs) throws SQLException {
        Characters character = new Characters();
        character.setId(rs.getInt("id"));
        character.setUser_id(rs.getString("user_id"));
        character.setName(rs.getString("name"));
        character.setChar_class(rs.getString("char_class"));
        character.setLevel(rs.getInt("level"));
        character.setRace(rs.getString("race"));
        character.setInitiative(rs.getInt("initiative"));
        character.setSpeed(rs.getInt("speed"));
        character.setStr(rs.getInt("str"));
        character.setDex(rs.getInt("dex"));
        character.setCon(rs.getInt("con"));
        character.setIntel(rs.getInt("intel"));
        character.setWis(rs.getInt("wis"));
        character.setCha(rs.getInt("cha"));
        character.setInspiration(rs.getBoolean("inspiration"));
        character.setProfbonus(rs.getInt("profbonus"));
        character.setMaxhp(rs.getInt("maxhp"));
        character.setHitdice(rs.getString("hitdice"));
        character.setAge(rs.getString("age"));
        character.setEyes(rs.getString("eyes"));
        character.setHair(rs.getString("hair"));
        character.setSkin(rs.getString("skin"));
        character.setHeight(rs.getString("height"));
        character.setWeight(rs.getString("weight"));
        character.setInventory(rs.getString("inventory"));
        character.setPortrait_url(rs.getString("portrait_url"));

        return character;
    }
}