package com.example.skejbydicejava;

public class Player {
    private String name;
    private String color;
    private int sips;
    private int pos;
    private int token;
    private Die lucky;

    public Player(String name, int pos, String color) {
        this.name = name;
        this.pos = pos;
        setToken(color);
        this.color = color;
        lucky = new Die(color);
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


    public void addSips(int num) {
        sips += num;
    }
}
