/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accdatos.hangman.view;

import com.accdatos.hangman.model.User;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * FXML Controller class for PlayView
 *
 * @author Ivan
 */
public class PlayView {

    private PlayViewModel playViewModel;
    private User currentUser;
    private Integer currentHangmanRoundId;

    @FXML
    private AnchorPane letterPane;

    @FXML
    private Label endMessage;

    @FXML
    Button playGame;

    @FXML
    private HBox uncoveredWord;

    @FXML
    private ImageView hangmanImage;

    @FXML
    private Label roundLog;

    /*
    * Data binding instructions
     */
    @FXML
    void initialize() {
        playViewModel = new PlayViewModel();
        playViewModel.setCurrentUser(currentUser);

        playGame.visibleProperty().bind(playViewModel.isOngoingRoundProperty().not());
        roundLog.visibleProperty().bind(playViewModel.isOngoingRoundProperty().not());
        endMessage.visibleProperty().bind(playViewModel.isOngoingRoundProperty().not());

        endMessage.textProperty().bind(Bindings.when(playViewModel.isWinProperty()).then("HAS GANADO!!").otherwise("HAS PERDIDO!!"));

        List<Image> images = new ArrayList<>();
        IntStream.range(0, 6).forEach(
                (num) -> images.add(new Image(getClass().getResource("/img/Hangman-" + num + ".png").toString())));
        hangmanImage.imageProperty().bind(Bindings.createObjectBinding(()
                -> images.get(playViewModel.numErrorsProperty().get()),
                playViewModel.numErrorsProperty()));

        playViewModel.isOngoingRoundProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue o, Object oldVal, Object newVal) {
                final ObservableList<Node> children = letterPane.getChildren();
                children.forEach((node) -> {
                    node.setDisable(!(Boolean) (newVal));
                });
                roundLog.setText(playViewModel.getRoundLog());
            }
        });

        playViewModel.guessedLettersProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue o, Object oldVal, Object newVal) {
                roundLog.setText(playViewModel.getRoundLog());
                final ObservableList<Node> children = letterPane.getChildren();
                children.filtered((node) -> playViewModel.guessedLettersProperty().get().contains(node.getId()))
                        .forEach(node -> node.setDisable(true));
            }
        });

        playViewModel.uncoveredWordProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue o, Object oldVal, Object newVal) {

                final ObservableList<Node> children = uncoveredWord.getChildren();
                children.forEach(
                        (node) -> {
                            if ((Integer.parseInt(((Label) node).getId())) < ((String) newVal).length()) {
                                ((Label) node).setText(String.valueOf(((String) newVal).charAt((Integer.parseInt(((Label) node).getId())))));
                            } else {
                                ((Label) node).setText("");
                            }
                        });
            }
        });
        if (currentHangmanRoundId != null) {
            playViewModel.resumeGame(currentHangmanRoundId);
        } else {
            playViewModel.playGame();
        }
    }

    /**
     * Process play game request
     *
     * @param event Play game button click event
     */
    @FXML

    public void playGame(ActionEvent event) {
        playViewModel.playGame();
    }

    /**
     * Process guess request process
     *
     * @param event Button letter click event
     */
    @FXML
    public void processGuess(ActionEvent event) {
        playViewModel.processGuess(((Button) event.getSource()).getId().charAt(0));
    }

    /**
     * Sets current user
     *
     * @param user Current user
     */
    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    /**
     *
     * @param id
     */
    public void setCurrentHangmanRoundId(Integer id) {
        this.currentHangmanRoundId = id;
    }

    /**
     * Visualize menu rounds request
     *
     * @param event 
     */
    @FXML
    protected void menuRounds(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/pick.fxml"));
            PickView pickView = new PickView();
            pickView.setCurrentUser(currentUser);
            fxmlLoader.setController(pickView);
            Parent parent = (Parent) fxmlLoader.load();
            Scene scene = new Scene(parent);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.setScene(scene);
            app_stage.show();
        } catch (Exception e) {
            
        }
    }
}
