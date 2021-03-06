package com.example.skejbydicejava;

public class Player {
    private String name;
    private String color;
    private int sips;
    private int beer;
    private int pos;
    private int token;
    private Die lucky;
    private int media;

    public Player(String name, int pos, String color, int media) {
        this.name = name;
        this.pos = pos;
        setToken(color);
        this.color = color;
        this.media = media;
        lucky = new Die(color);
        beer = 0;
        sips = 0;
    }

    private void setToken(String color) {
        switch (color) {
            case "brown":
                token = R.drawable.token1;
                break;
            case "green":
                token = R.drawable.token2;
                break;
            case "blue":
                token = R.drawable.token3;
                break;
            case "red":
                token = R.drawable.token4;
                break;
        }
    }

    public Die getLuckyDie() {
        return lucky;
    }

    public int getToken() {
        return token;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public String getName() {
        return name;
    }

    public int getSips() {
        return sips;
    }

    public int getMedia() {return media; }

    public void addSips(int num) {
        if(sips + num < 14) {
            sips += num;
        } else {
            MainActivity.mediaPlayerKill.start();
            beer += 1;
            sips = 0;
        }
    }

    public int getBeer() {return beer;}
}
