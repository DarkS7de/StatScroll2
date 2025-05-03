package com.example.statscroll.dao;

import com.example.statscroll.model.Characters;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CharactersDAO {
    private final String url = "jdbc:h2:./statscroll_db";

    public CharactersDAO() {
        try (Connection conn = DriverManager.getConnection(url, "sa", "");
             Statement stmt = conn.createStatement()) {

            stmt.execute("""
                CREATE TABLE IF NOT EXISTS characters (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    user_id VARCHAR,
                    name VARCHAR,
                    char_class VARCHAR,
                    subclass VARCHAR,
                    multiclass VARCHAR,
                    level INT,
                    race VARCHAR,
                    background VARCHAR,
                    alignment VARCHAR,
                    initiative INT,
                    speed INT,
                    exp INT,
                    str INT,
                    dex INT,
                    con INT,
                    intel INT,
                    wis INT,
                    cha INT,
                    inspiration BOOLEAN,
                    profbonus INT,
                    maxhp INT,
                    currenthp INT,
                    temphp INT,
                    totalhitdice VARCHAR,
                    hitdice VARCHAR,
                    spellsavedc INT,
                    spellattackbonus INT,
                    age VARCHAR,
                    eyes VARCHAR,
                    hair VARCHAR,
                    skin VARCHAR,
                    height VARCHAR,
                    weight VARCHAR,
                    portrait_url VARCHAR
                )
            """);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void save(Characters c) {
        String sql = """
            INSERT INTO characters (user_id, name, char_class, subclass, multiclass, level, race, background, alignment,
                                    initiative, speed, exp, str, dex, con, intel, wis, cha, inspiration, profbonus,
                                    maxhp, currenthp, temphp, totalhitdice, hitdice, spellsavedc, spellattackbonus,
                                    age, eyes, hair, skin, height, weight, portrait_url)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection conn = DriverManager.getConnection(url, "sa", "");
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, c.getUser_id());
            ps.setString(2, c.getName());
            ps.setString(3, c.getChar_class());
            ps.setString(4, c.getSubclass());
            ps.setString(5, c.getMulticlass());
            ps.setInt(6, c.getLevel());
            ps.setString(7, c.getRace());
            ps.setString(8, c.getBackground());
            ps.setString(9, c.getAlignment());
            ps.setInt(10, c.getInitiative());
            ps.setInt(11, c.getSpeed());
            ps.setInt(12, c.getExp());
            ps.setInt(13, c.getStr());
            ps.setInt(14, c.getDex());
            ps.setInt(15, c.getCon());
            ps.setInt(16, c.getIntel());
            ps.setInt(17, c.getWis());
            ps.setInt(18, c.getCha());
            ps.setBoolean(19, c.isInspiration());
            ps.setInt(20, c.getProfbonus());
            ps.setInt(21, c.getMaxhp());
            ps.setInt(22, c.getCurrenthp());
            ps.setInt(23, c.getTemphp());
            ps.setString(24, c.getTotalhitdice());
            ps.setString(25, c.getHitdice());
            ps.setInt(26, c.getSpellsavedc());
            ps.setInt(27, c.getSpellattackbonus());
            ps.setString(28, c.getAge());
            ps.setString(29, c.getEyes());
            ps.setString(30, c.getHair());
            ps.setString(31, c.getSkin());
            ps.setString(32, c.getHeight());
            ps.setString(33, c.getWeight());
            ps.setString(34, c.getPortrait_url());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Characters> findAll() {
        List<Characters> characters = new ArrayList<>();
        String sql = "SELECT * FROM characters";

        try (Connection conn = DriverManager.getConnection(url, "sa", "");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Characters c = new Characters(
                        rs.getInt("id"),
                        rs.getString("user_id"),
                        rs.getString("name"),
                        rs.getString("char_class"),
                        rs.getString("subclass"),
                        rs.getString("multiclass"),
                        rs.getInt("level"),
                        rs.getString("race"),
                        rs.getString("background"),
                        rs.getString("alignment"),
                        rs.getInt("initiative"),
                        rs.getInt("speed"),
                        rs.getInt("exp"),
                        rs.getInt("str"),
                        rs.getInt("dex"),
                        rs.getInt("con"),
                        rs.getInt("intel"),
                        rs.getInt("wis"),
                        rs.getInt("cha"),
                        rs.getBoolean("inspiration"),
                        rs.getInt("profbonus"),
                        rs.getInt("maxhp"),
                        rs.getInt("currenthp"),
                        rs.getInt("temphp"),
                        rs.getString("totalhitdice"),
                        rs.getString("hitdice"),
                        rs.getInt("spellsavedc"),
                        rs.getInt("spellattackbonus"),
                        rs.getString("age"),
                        rs.getString("eyes"),
                        rs.getString("hair"),
                        rs.getString("skin"),
                        rs.getString("height"),
                        rs.getString("weight"),
                        rs.getString("portrait_url")
                );
                characters.add(c);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return characters;
    }

    public Characters findById(int id) {
        String sql = "SELECT * FROM characters WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(url, "sa", "");
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Characters(
                        rs.getInt("id"),
                        rs.getString("user_id"),
                        rs.getString("name"),
                        rs.getString("char_class"),
                        rs.getString("subclass"),
                        rs.getString("multiclass"),
                        rs.getInt("level"),
                        rs.getString("race"),
                        rs.getString("background"),
                        rs.getString("alignment"),
                        rs.getInt("initiative"),
                        rs.getInt("speed"),
                        rs.getInt("exp"),
                        rs.getInt("str"),
                        rs.getInt("dex"),
                        rs.getInt("con"),
                        rs.getInt("intel"),
                        rs.getInt("wis"),
                        rs.getInt("cha"),
                        rs.getBoolean("inspiration"),
                        rs.getInt("profbonus"),
                        rs.getInt("maxhp"),
                        rs.getInt("currenthp"),
                        rs.getInt("temphp"),
                        rs.getString("totalhitdice"),
                        rs.getString("hitdice"),
                        rs.getInt("spellsavedc"),
                        rs.getInt("spellattackbonus"),
                        rs.getString("age"),
                        rs.getString("eyes"),
                        rs.getString("hair"),
                        rs.getString("skin"),
                        rs.getString("height"),
                        rs.getString("weight"),
                        rs.getString("portrait_url")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void deleteById(int id) {
        String sql = "DELETE FROM characters WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(url, "sa", "");
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Characters c) {
        String sql = """
        UPDATE characters SET
            user_id = ?, name = ?, char_class = ?, subclass = ?, multiclass = ?,
            level = ?, race = ?, background = ?, alignment = ?, initiative = ?, speed = ?, exp = ?,
            str = ?, dex = ?, con = ?, intel = ?, wis = ?, cha = ?, inspiration = ?, profbonus = ?,
            maxhp = ?, currenthp = ?, temphp = ?, totalhitdice = ?, hitdice = ?,
            spellsavedc = ?, spellattackbonus = ?,
            age = ?, eyes = ?, hair = ?, skin = ?, height = ?, weight = ?, portrait_url = ?
        WHERE id = ?
    """;

        try (Connection conn = DriverManager.getConnection(url, "sa", "");
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, c.getUser_id());
            ps.setString(2, c.getName());
            ps.setString(3, c.getChar_class());
            ps.setString(4, c.getSubclass());
            ps.setString(5, c.getMulticlass());
            ps.setInt(6, c.getLevel());
            ps.setString(7, c.getRace());
            ps.setString(8, c.getBackground());
            ps.setString(9, c.getAlignment());
            ps.setInt(10, c.getInitiative());
            ps.setInt(11, c.getSpeed());
            ps.setInt(12, c.getExp());
            ps.setInt(13, c.getStr());
            ps.setInt(14, c.getDex());
            ps.setInt(15, c.getCon());
            ps.setInt(16, c.getIntel());
            ps.setInt(17, c.getWis());
            ps.setInt(18, c.getCha());
            ps.setBoolean(19, c.isInspiration());
            ps.setInt(20, c.getProfbonus());
            ps.setInt(21, c.getMaxhp());
            ps.setInt(22, c.getCurrenthp());
            ps.setInt(23, c.getTemphp());
            ps.setString(24, c.getTotalhitdice());
            ps.setString(25, c.getHitdice());
            ps.setInt(26, c.getSpellsavedc());
            ps.setInt(27, c.getSpellattackbonus());
            ps.setString(28, c.getAge());
            ps.setString(29, c.getEyes());
            ps.setString(30, c.getHair());
            ps.setString(31, c.getSkin());
            ps.setString(32, c.getHeight());
            ps.setString(33, c.getWeight());
            ps.setString(34, c.getPortrait_url());
            ps.setInt(35, c.getId());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
