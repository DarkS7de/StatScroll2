package com.example.statscroll.controller;

import com.example.statscroll.dao.CharactersDAO;
import com.example.statscroll.model.Characters;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class MyCharactersController implements Initializable {

    @FXML public Button backButton;
    @FXML private ListView<String> charactersListVIew;
    @FXML private Button deleteButton;

    private ObservableList<String> characterList = FXCollections.observableArrayList();

    // Mappa nome → ID per sapere chi eliminare
    private Map<String, Integer> nameToIdMap = new HashMap<>();

    CharactersDAO charactersDAO = new CharactersDAO();

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println(Session.getUserId());
        loadCharactersFromDatabase();
        charactersListVIew.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) { // doppio click
                String selectedName = charactersListVIew.getSelectionModel().getSelectedItem();
                if (selectedName != null) {
                    Integer characterId = nameToIdMap.get(selectedName);
                    Characters character = charactersDAO.findById(characterId);
                    Session.setCharacters(character);
                    try {
                        openCharacterDetailPage();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }

    private void openCharacterDetailPage() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/characterSheet.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) charactersListVIew.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    private void loadCharactersFromDatabase() {
        characterList.clear();
        nameToIdMap.clear();

        List<Characters> myCharacters = charactersDAO.findByUserId(Session.getUserId());

        for(Characters character : myCharacters) {
            System.out.println(character);
            nameToIdMap.put(character.getName(), character.getId());
            characterList.add(character.getName());
        }

        charactersListVIew.setItems(characterList);
    }

    public void handleBack(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/menuPage.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) charactersListVIew.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void handleDelete(ActionEvent actionEvent) {
        String selectedName = charactersListVIew.getSelectionModel().getSelectedItem();
        if (selectedName != null) {
            Integer characterId = nameToIdMap.get(selectedName);

            boolean confirm = true;

            if (confirm && characterId != null) {
                charactersDAO.deleteById(characterId);
                loadCharactersFromDatabase();
            }
        }
    }

}
