package com.example.skejbydicejava;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;

/*
    This game has XXX phases:
    Phase 0: Roll first time for attacking
 */

public class MainActivity extends AppCompatActivity {
    private ImageView imageViewDie1, imageViewDie2;
    private ImageView imageViewOpp1, imageViewOpp2, imageViewOpp3;
    private Button rollButton;
    private Random rng = new Random();
    private int attackDie1, attackDie2;
    private TextView playerSips, opp1Sips, opp2Sips, opp3Sips;
    private TextView playerName, opp1Name, opp2Name, opp3Name;
    private Player player, opp1, opp2, opp3;
    private MediaPlayer diceRollSound;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        diceRollSound = MediaPlayer.create(MainActivity.this,R.raw.diceroll);

        player = new Player("Søren");
        opp1 = new Player("Nikolaj");
        opp2 = new Player("Bjørn");
        opp3 = new Player("Christian");

        setUpComponents();
        updateSips();
        setDefaultValues();
        setClickListeners();

    }

    private void setClickListeners() {
        imageViewOpp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attack(opp1,attackValue());
            }
        });

        imageViewOpp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attack(opp2,attackValue());
            }
        });

        imageViewOpp3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attack(opp3,attackValue());
            }
        });

        rollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attackDie1 = rollDie(imageViewDie1);
                attackDie2 = rollDie(imageViewDie2);
                rollButton.setEnabled(false);
                imageViewOpp1.setEnabled(true);
                imageViewOpp2.setEnabled(true);
                imageViewOpp3.setEnabled(true);
            }
        });
    }

    private void setDefaultValues() {
        rollButton.setEnabled(true);
        imageViewOpp1.setEnabled(false);
        imageViewOpp2.setEnabled(false);
        imageViewOpp3.setEnabled(false);
    }

    private void setUpComponents() {
        playerName = findViewById(R.id.text_view_playerName);
        opp1Name = findViewById(R.id.text_view_opp1Name);
        opp2Name = findViewById(R.id.text_view_opp2Name);
        opp3Name = findViewById(R.id.text_view_opp3Name);

        playerName.setText(player.getName());
        opp1Name.setText(opp1.getName());
        opp2Name.setText(opp2.getName());
        opp3Name.setText(opp3.getName());

        playerSips = findViewById(R.id.text_view_playerSips);
        opp1Sips = findViewById(R.id.text_view_opp1Sips);
        opp2Sips = findViewById(R.id.text_view_opp2Sips);
        opp3Sips = findViewById(R.id.text_view_opp3Sips);

        imageViewDie1 = findViewById(R.id.image_view_die1);
        imageViewDie2 = findViewById(R.id.image_view_die2);

        imageViewOpp1 = findViewById(R.id.image_view_opp1);
        imageViewOpp2 = findViewById(R.id.image_view_opp2);
        imageViewOpp3 = findViewById(R.id.image_view_opp3);

        rollButton = findViewById(R.id.roll_button);
    }


    private int attackValue() {
        return (attackDie1+attackDie2)/2;
    }

    private void attack(Player p, int sips) {
        p.addSips(sips);
        updateSips();
        rollButton.setEnabled(true);
        imageViewOpp1.setEnabled(false);
        imageViewOpp2.setEnabled(false);
        imageViewOpp3.setEnabled(false);
    }

    private void updateSips() {
        playerSips.setText("" + player.getSips());
        opp1Sips.setText("" + opp1.getSips());
        opp2Sips.setText("" + opp2.getSips());
        opp3Sips.setText("" + opp3.getSips());
    }

    private int rollDie(ImageView imageViewDie) {
        diceRollSound.start();
        int randomNumber = rng.nextInt(6) + 1;

        switch (randomNumber) {
            case 1:
                imageViewDie.setImageResource(R.drawable.d1);
                break;
            case 2:
                imageViewDie.setImageResource(R.drawable.d2);
                break;
            case 3:
                imageViewDie.setImageResource(R.drawable.d3);
                break;
            case 4:
                imageViewDie.setImageResource(R.drawable.d4);
                break;
            case 5:
                imageViewDie.setImageResource(R.drawable.d5);
                break;
            case 6:
                imageViewDie.setImageResource(R.drawable.d6);
                break;
        }
        return randomNumber;
    }
}
