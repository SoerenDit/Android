package com.example.skejbydicejava;

import java.util.ArrayList;
import java.util.List;

public class LuckyDie {
    private int number;
    private List<Integer> numberPics;


    public LuckyDie(String color) {
        number = 1;
        numberPics = new ArrayList<>();
        setNumberPics(color);
    }

    private void setNumberPics(String color) {
        switch (color) {
            case "brown":
                numberPics.add(R.drawable.brownl1);
                numberPics.add(R.drawable.brownl2);
                numberPics.add(R.drawable.brownl3);
                numberPics.add(R.drawable.brownl4);
                numberPics.add(R.drawable.brownl5);
                numberPics.add(R.drawable.brownl6);
                break;
            case "green":
                numberPics.add(R.drawable.gl1);
                numberPics.add(R.drawable.gl2);
                numberPics.add(R.drawable.gl3);
                numberPics.add(R.drawable.gl4);
                numberPics.add(R.drawable.gl5);
                numberPics.add(R.drawable.gl6);
                break;
            case "blue":
                numberPics.add(R.drawable.bluel1);
                numberPics.add(R.drawable.bluel2);
                numberPics.add(R.drawable.bluel3);
                numberPics.add(R.drawable.bluel4);
                numberPics.add(R.drawable.bluel5);
                numberPics.add(R.drawable.bluel6);
                break;
            case "red":
                numberPics.add(R.drawable.redl1);
                numberPics.add(R.drawable.redl2);
                numberPics.add(R.drawable.redl3);
                numberPics.add(R.drawable.redl4);
                numberPics.add(R.drawable.redl5);
                numberPics.add(R.drawable.redl6);
                break;
        }
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int n) {
        number = n;
    }

    public void increaseDie() {
        number++;
    }

    public int getState() {
        return numberPics.get(number-1);
    }
}
