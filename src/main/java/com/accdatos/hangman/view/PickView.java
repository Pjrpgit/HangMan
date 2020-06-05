/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accdatos.hangman.view;


import com.accdatos.hangman.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class for PickView
 *
 * @author Ivan
 */

public class PickView {

    private PickViewModel pickViewModel;

    private Integer currentHangmanRoundId;

    private User currentUser;

    @FXML
    TableView hangmanRounds;

    @FXML
    TableColumn uncoveredWord;

    @FXML
    TableColumn numErrors;

    @FXML
    TableColumn id;
    
    /*
    * Data Binding instructions
    */

    @FXML
    void initialize() {
        pickViewModel = new PickViewModel();
        pickViewModel.setCurrentUser(currentUser);
        
        if (pickViewModel.getCurrentUser() != null
                && pickViewModel.getHangmanRounds() != null) {
            uncoveredWord.setCellValueFactory(new PropertyValueFactory<HangmanRoundRow, String>("uncoveredWord"));
            numErrors.setCellValueFactory(new PropertyValueFactory<HangmanRoundRow, Integer>("numErrors"));
            id.setCellValueFactory(new PropertyValueFactory<HangmanRoundRow, Integer>("id"));
            hangmanRounds.setItems(pickViewModel.getHangmanRoundTableData());
            hangmanRounds.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                if (newSelection != null) {
                    this.currentHangmanRoundId = ((HangmanRoundRow) newSelection).getId();
                    changeToPlayView((Stage) hangmanRounds.getScene().getWindow());
                }
            });
        }
    }

    /**
     * Process play game request
     * 
     * @param event Button play game click event
     */
    
    @FXML
    public void playGame(ActionEvent event) {
        changeToPlayView((Stage) ((Node) event.getSource()).getScene().getWindow());
    }
    
    private void changeToPlayView(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/play.fxml"));
            PlayView playView = new PlayView();
            playView.setCurrentUser(currentUser);
            playView.setCurrentHangmanRoundId(currentHangmanRoundId);
            fxmlLoader.setController(playView);
            Parent play_parent = (Parent) fxmlLoader.load();
            Scene play_scene = new Scene(play_parent);
            stage.setScene(play_scene);
            stage.show();
        } catch (Exception e) {
        }

    }

    /**
     * Sets current user in the controller
     * 
     * @param user Current user
     */
    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

}
