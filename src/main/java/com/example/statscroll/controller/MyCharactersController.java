package com.example.statscroll.controller;

import com.example.statscroll.dao.CharactersDAO;
import com.example.statscroll.model.Characters;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class MyCharactersController implements Initializable {

    @FXML public Button backButton;
    @FXML private ListView<String> charactersListVIew;
    @FXML private Button deleteButton;
    @FXML private ComboBox<String> classFilterComboBox;
    @FXML private TextField searchField;
    @FXML private Button resetFilterButton;

    private ObservableList<String> characterList = FXCollections.observableArrayList();
    private FilteredList<String> filteredCharacters;
    private Map<String, Integer> nameToIdMap = new HashMap<>();
    private Map<String, String> nameToClassMap = new HashMap<>();

    private final List<String> dndClasses = List.of(
            "Tutte le classi", "Guerriero", "Mago", "Ladro",
            "Chierico", "Paladino", "Druido"
    );

    private CharactersDAO charactersDAO = new CharactersDAO();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupClassFilter();
        setupSearchFilter();
        loadCharactersFromDatabase();
        setupDoubleClickHandler();
    }

    public void refreshCharacterList() {
        loadCharactersFromDatabase();
    }

    private void setupClassFilter() {
        classFilterComboBox.setItems(FXCollections.observableArrayList(dndClasses));
        classFilterComboBox.getSelectionModel().selectFirst();

        classFilterComboBox.valueProperty().addListener((obs, oldVal, newVal) -> {
            updateFilter();
        });
    }

    private void setupSearchFilter() {
        filteredCharacters = new FilteredList<>(characterList, p -> true);
        charactersListVIew.setItems(filteredCharacters);

        searchField.textProperty().addListener((obs, oldVal, newVal) -> {
            updateFilter();
        });
    }

    private void setupDoubleClickHandler() {
        charactersListVIew.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                handleCharacterSelection();
            }
        });
    }

    private void handleCharacterSelection() {
        String selectedName = charactersListVIew.getSelectionModel().getSelectedItem();
        if (selectedName != null) {
            Integer characterId = nameToIdMap.get(selectedName);
            Characters character = charactersDAO.findById(characterId);
            Session.setCurrentCharacter(character);
            try {
                openCharacterDetailPage();
            } catch (IOException e) {
                e.printStackTrace();
                showErrorAlert("Errore", "Impossibile aprire i dettagli del personaggio");
            }
        }
    }

    private void updateFilter() {
        String selectedClass = classFilterComboBox.getValue();
        String searchText = searchField.getText().toLowerCase();

        filteredCharacters.setPredicate(character -> {
            // Filtro per classe
            boolean classMatches = selectedClass == null ||
                    selectedClass.equals("Tutte le classi") ||
                    selectedClass.equals(nameToClassMap.get(character));

            // Filtro per ricerca testo
            boolean nameMatches = searchText.isEmpty() ||
                    character.toLowerCase().contains(searchText);

            return classMatches && nameMatches;
        });
    }

    public void loadCharactersFromDatabase() {
        characterList.clear();
        nameToIdMap.clear();
        nameToClassMap.clear();

        // Debug: verifica l'ID utente
        System.out.println("Caricamento personaggi per user ID: " + Session.getUserId());

        List<Characters> myCharacters = charactersDAO.findByUserId(Session.getUserId().toString());

        // Debug: verifica i personaggi trovati
        System.out.println("Personaggi trovati: " + myCharacters.size());

        for(Characters character : myCharacters) {
            nameToIdMap.put(character.getName(), character.getId());
            nameToClassMap.put(character.getName(), character.getChar_class());
            characterList.add(character.getName());
        }

        updateFilter();
    }

    @FXML
    private void handleDelete(ActionEvent actionEvent) {
        String selectedName = charactersListVIew.getSelectionModel().getSelectedItem();
        if (selectedName != null) {
            Integer characterId = nameToIdMap.get(selectedName);

            Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
            confirmation.setTitle("Conferma eliminazione");
            confirmation.setHeaderText("Eliminare il personaggio?");
            confirmation.setContentText("Sei sicuro di voler eliminare " + selectedName + "?");

            Optional<ButtonType> result = confirmation.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                charactersDAO.deleteById(characterId);
                loadCharactersFromDatabase();
                showInfoAlert("Successo", "Personaggio eliminato con successo");
            }
        } else {
            showErrorAlert("Errore", "Nessun personaggio selezionato");
        }
    }

    @FXML
    private void handleResetFilter(ActionEvent actionEvent) {
        classFilterComboBox.getSelectionModel().selectFirst();
        searchField.clear();
    }

    @FXML
    public void handleBack(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/menuPage.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    private void openCharacterDetailPage() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/characterSheet.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) charactersListVIew.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    private void showErrorAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showInfoAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}