package com.example.statscroll.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.net.URL;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class MyCharactersController implements Initializable {

    @FXML
    private ListView<String> charactersListVIew;

    private ObservableList<String> characterList = FXCollections.observableArrayList();

    // Mappa nome → ID per sapere chi eliminare
    private Map<String, Integer> nameToIdMap = new HashMap<>();

    // Credenziali DB (modifica con le tue)
    private final String url = "jdbc:mysql://localhost:3306/tuo_database";
    private final String user = "tuo_utente";
    private final String password = "tua_password";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadCharactersFromDatabase();

        // Elimina con doppio click sul personaggio
        charactersListVIew.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                handleDeleteSelectedCharacter();
            }
        });
    }

    private void loadCharactersFromDatabase() {
        characterList.clear();
        nameToIdMap.clear();

        String query = "SELECT id, nome_personaggio FROM personaggi";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome_personaggio");

                characterList.add(nome);
                nameToIdMap.put(nome, id);
            }

            charactersListVIew.setItems(characterList);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void handleDeleteSelectedCharacter() {
        String selected = charactersListVIew.getSelectionModel().getSelectedItem();

        if (selected == null) return;

        Integer id = nameToIdMap.get(selected);
        if (id == null) return;

        String deleteQuery = "DELETE FROM personaggi WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(deleteQuery)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();

            loadCharactersFromDatabase(); // Ricarica la lista aggiornata

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
