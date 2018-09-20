package com.codecool.citySim.controller;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URL;

class SoundController {
    MediaPlayer playTraffic;

    SoundController(){
        URL sebaURL = getClass().getResource("/sounds/seba.mp3");
        URL trafficURL = getClass().getResource("/sounds/traffic.mp3");
        URL policeURL = getClass().getResource("/sounds/police.mp3");
        Media sebaMusic = new Media(sebaURL.toString());
        Media trafficSound = new Media(trafficURL.toString());
        Media policeSirens = new Media(policeURL.toString());
        MediaPlayer sebaMusicPlayer = new MediaPlayer(sebaMusic);
        MediaPlayer playSirens = new MediaPlayer(policeSirens);
        playTraffic = new MediaPlayer(trafficSound);
        playSirens.setCycleCount(MediaPlayer.INDEFINITE);
        sebaMusicPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        playTraffic.setCycleCount(MediaPlayer.INDEFINITE);
    }
}
