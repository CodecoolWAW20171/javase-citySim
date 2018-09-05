package com.codecool.citySim.controller;

import com.codecool.citySim.model.roads.Road;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class CitySimPaneController implements Initializable {
    @FXML
    public Pane pane;
    public Road road;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        road.setStartX(0);
        road.setStartY(360);
        road.setEndX(1280);
        road.setEndY(360);
        road.prefWidth(150);
    }
}
