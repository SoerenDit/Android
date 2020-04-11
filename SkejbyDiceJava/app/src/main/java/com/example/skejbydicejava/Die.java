package com.example.skejbydicejava;

import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Die {
    private int number;
    private List<Integer> numberPics;
    private Random rng = new Random();

    public Die(String color) {
        numberPics = new ArrayList<>();
        number = 1;
        setNumberPics(color);
    }

    private void setNumberPics(String color) {
        switch (color) {
            case "white":
                numberPics.add(R.drawable.d1);
                numberPics.add(R.drawable.d2);
                numberPics.add(R.drawable.d3);
                numberPics.add(R.drawable.d4);
                numberPics.add(R.drawable.d5);
                numberPics.add(R.drawable.d6);
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

    public int roll(ImageView imageViewDie) {

        MainActivity.mediaPlayerRoll.start();
        int randomNumber = rng.nextInt(6) + 1;
        number = randomNumber;
        // number = 6;

        switch (randomNumber) {
            case 1:
                imageViewDie.setImageResource(numberPics.get(0));
                break;
            case 2:
                imageViewDie.setImageResource(numberPics.get(1));
                break;
            case 3:
                imageViewDie.setImageResource(numberPics.get(2));
                break;
            case 4:
                imageViewDie.setImageResource(numberPics.get(3));
                break;
            case 5:
                imageViewDie.setImageResource(numberPics.get(4));
                break;
            case 6:
                imageViewDie.setImageResource(numberPics.get(5));
                break;
        }
        return randomNumber;

    }

    public int getNumber() {
        return number;
    }

    public void increaseDie() {
        if (number < 6) {
            number++;
        }
    }

    public void decreaseDie() {
        if (number > 1) {
            number--;
        }
    }

    public int getImage() {
        return numberPics.get(number - 1);
    }


}
