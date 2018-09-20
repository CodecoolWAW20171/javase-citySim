package com.codecool.citySim.controller;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URL;

class SoundController {
    MediaPlayer sebaMusicPlayer;
    MediaPlayer playTraffic;
    MediaPlayer playSirens;
    private Media sebaMusic;
    private Media trafficSound;
    private Media policeSirens;

    SoundController(){
        URL sebaURL = getClass().getResource("/sounds/seba.mp3");
        URL trafficURL = getClass().getResource("/sounds/traffic.mp3");
        URL policeURL = getClass().getResource("/sounds/police.mp3");
        sebaMusic = new Media(sebaURL.toString());
        trafficSound = new Media(trafficURL.toString());
        policeSirens = new Media(policeURL.toString());
        sebaMusicPlayer = new MediaPlayer(sebaMusic);
        playSirens = new MediaPlayer(policeSirens);
        playTraffic = new MediaPlayer(trafficSound);
        playSirens.setCycleCount(MediaPlayer.INDEFINITE);
        sebaMusicPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        playTraffic.setCycleCount(MediaPlayer.INDEFINITE);
    }
}
