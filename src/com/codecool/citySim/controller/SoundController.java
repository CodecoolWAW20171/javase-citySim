package com.codecool.citySim.controller;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URL;

class SoundController {
    MediaPlayer sebaMusicPlayer;
    MediaPlayer playSirens;
    private Media sebaMusic;
    private Media policeSirens;

    SoundController(){
        URL sebaURL = getClass().getResource("/sounds/seba.mp3");
        URL policeURL = getClass().getResource("/sounds/police.mp3");
        sebaMusic = new Media(sebaURL.toString());
        policeSirens = new Media(policeURL.toString());
        sebaMusicPlayer = new MediaPlayer(sebaMusic);
        playSirens = new MediaPlayer(policeSirens);
        playSirens.setCycleCount(MediaPlayer.INDEFINITE);
        sebaMusicPlayer.setCycleCount(MediaPlayer.INDEFINITE);
    }
}
