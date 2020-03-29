package com.example.skejbydicejava;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements DialogBox.DialogBoxListener {
    private ImageView imageViewDie1, imageViewDie2;
    private ImageView imageViewPos2, imageViewPos3, imageViewPos4;
    private ImageView imageViewLuckyDie1, imageViewLuckyDie2, imageViewLuckyDie3, imageViewLuckyDie4;
    private TextView textViewPos1Sips, textViewPos2Sips, textViewPos3Sips, textViewPos4Sips;
    private TextView textViewPos1Name, textViewPos2Name, textViewPos3Name, textViewPos4Name;
    private TextView textViewMessage;
    private Button rollButton;
    private Die attackDie1, attackDie2;
    private Player pos1Player, pos2Player, pos3Player, pos4Player;
    private Player attackedPlayer;
    private List<Player> players;
    private int sipsToDrink;
    private int toBeat;
    private String dialogBoxType;
    public static MediaPlayer mediaPlayer;
    private Random rng = new Random();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);

        players = new ArrayList<>();
        players.add(new Player("Søren", 1, "brown"));
        players.add(new Player("Nikolaj", 2, "green"));
        players.add(new Player("Bjørn", 3, "blue"));
        players.add(new Player("Christian", 4, "red"));
        attackDie1 = new Die("white");
        attackDie2 = new Die("white");
        mediaPlayer = MediaPlayer.create(this, R.raw.diceroll);

        setUpComponents();

        setDefaultValues();

        placePlayers();

        setClickListeners();
    }

    private void setUpComponents() {
        textViewPos1Name = findViewById(R.id.text_view_pos1Name);
        textViewPos2Name = findViewById(R.id.text_view_pos2Name);
        textViewPos3Name = findViewById(R.id.text_view_pos3Name);
        textViewPos4Name = findViewById(R.id.text_view_pos4Name);

        textViewPos1Sips = findViewById(R.id.text_view_pos1Sips);
        textViewPos2Sips = findViewById(R.id.text_view_pos2Sips);
        textViewPos3Sips = findViewById(R.id.text_view_pos3Sips);
        textViewPos4Sips = findViewById(R.id.text_view_pos4Sips);

        textViewMessage = findViewById(R.id.text_view_message);

        imageViewDie1 = findViewById(R.id.image_view_die1);
        imageViewDie2 = findViewById(R.id.image_view_die2);

        imageViewLuckyDie1 = findViewById(R.id.lucky_die_pos1);
        imageViewLuckyDie2 = findViewById(R.id.lucky_die_pos2);
        imageViewLuckyDie3 = findViewById(R.id.lucky_die_pos3);
        imageViewLuckyDie4 = findViewById(R.id.lucky_die_pos4);

        imageViewPos2 = findViewById(R.id.image_view_pos2);
        imageViewPos3 = findViewById(R.id.image_view_pos3);
        imageViewPos4 = findViewById(R.id.image_view_pos4);

        rollButton = findViewById(R.id.roll_button);
    }

    private void setDefaultValues() {
        rollButton.setEnabled(true);
        imageViewPos2.setEnabled(false);
        imageViewPos3.setEnabled(false);
        imageViewPos4.setEnabled(false);
    }

    private void rotatePlayers() {
        for (Player p : players) {
            if (p.getPos() == 4) {
                p.setPos(1);
            } else {
                p.setPos(p.getPos() + 1);
            }
        }
        placePlayers();
    }

    private void placePlayers() {
        for (Player p : players) {
            switch (p.getPos()) {
                case 1:
                    textViewPos1Name.setText(p.getName());
                    textViewPos1Sips.setText("" + p.getSips());
                    imageViewLuckyDie1.setImageResource(p.getLuckyDie().getImage());
                    pos1Player = p;
                    break;
                case 2:
                    textViewPos2Name.setText(p.getName());
                    textViewPos2Sips.setText("" + p.getSips());
                    imageViewPos2.setImageResource(p.getToken());
                    imageViewLuckyDie2.setImageResource(p.getLuckyDie().getImage());
                    pos2Player = p;
                    break;
                case 3:
                    textViewPos3Name.setText(p.getName());
                    textViewPos3Sips.setText("" + p.getSips());
                    imageViewPos3.setImageResource(p.getToken());
                    imageViewLuckyDie3.setImageResource(p.getLuckyDie().getImage());
                    pos3Player = p;
                    break;
                case 4:
                    textViewPos4Name.setText(p.getName());
                    textViewPos4Sips.setText("" + p.getSips());
                    imageViewPos4.setImageResource(p.getToken());
                    imageViewLuckyDie4.setImageResource(p.getLuckyDie().getImage());
                    pos4Player = p;
                    break;
            }
        }
    }

    private void setClickListeners() {
        imageViewLuckyDie1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sipsToDrink = pos1Player.getLuckyDie().getNumber();
                dialogBoxType = "Lucky";
                DialogBox dialogBox = new DialogBox();
                dialogBox.show(getSupportFragmentManager(),"Eksempel1");
            }
        });

        imageViewPos2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sipsToDrink = attackValue();
                dialogBoxType = "YouHaveBeenAttacked";
                attackedPlayer = pos2Player;
                DialogBox dialogBox = new DialogBox();
                dialogBox.show(getSupportFragmentManager(),"Eksempel2");

            }
        });

        imageViewPos3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sipsToDrink = attackValue();
                dialogBoxType = "YouHaveBeenAttacked";
                attackedPlayer = pos3Player;
                DialogBox dialogBox = new DialogBox();
                dialogBox.show(getSupportFragmentManager(),"Eksempel3");
            }
        });

        imageViewPos4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sipsToDrink = attackValue();
                dialogBoxType = "YouHaveBeenAttacked";
                attackedPlayer = pos4Player;
                DialogBox dialogBox = new DialogBox();
                dialogBox.show(getSupportFragmentManager(),"Eksempel4");
            }
        });


        rollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attackDie1.roll(imageViewDie1);
                attackDie2.roll(imageViewDie2);
                rollButton.setEnabled(false);
                imageViewPos2.setEnabled(true);
                imageViewPos3.setEnabled(true);
                imageViewPos4.setEnabled(true);
                if (attackValue() > 1) {
                    textViewMessage.setText("Who do you want to give " + attackValue() + " sips?");
                } else {
                    textViewMessage.setText("Who should drink a single sip?");
                }

            }
        });
    }

    private void attack(Player p, int sips) {
        p.addSips(sips);
        updateSips();
        rollButton.setEnabled(true);
        imageViewPos2.setEnabled(false);
        imageViewPos3.setEnabled(false);
        imageViewPos4.setEnabled(false);
    }

    private int attackValue() {
        return (attackDie1.getNumber() + attackDie2.getNumber()) / 2;
    }

    private void updateSips() {
        textViewPos1Sips.setText("" + pos1Player.getSips());
        textViewPos2Sips.setText("" + pos2Player.getSips());
        textViewPos3Sips.setText("" + pos3Player.getSips());
        textViewPos4Sips.setText("" + pos4Player.getSips());
    }

    @Override
    public void onYesClickedLucky() {
        pos1Player.getLuckyDie().roll(imageViewLuckyDie1);
    }

    @Override
    public void onYesClickedAttack() {
        attack(attackedPlayer, attackValue());
        rotatePlayers();
        textViewMessage.setText(pos1Player.getName() + "'s turn. Roll!");
    }

    @Override
    public void onNoClickedAttack() {
        toBeat = Math.max(attackDie1.getNumber(),attackDie2.getNumber());
        dialogBoxType = "DefenceTime";
        DialogBox dialogBox = new DialogBox();
        dialogBox.show(getSupportFragmentManager(),"Eksempel1");
    }

    @Override
    public void onYesClickedDefence() {
        MainActivity.mediaPlayer.start();
        int randomNumber = rng.nextInt(6) + 1;
        if(randomNumber > toBeat) {
            dialogBoxType = "SuccesfulDefence";
            DialogBox dialogBox = new DialogBox();
            dialogBox.show(getSupportFragmentManager(),"Eksempel1");
        } else {

        }
        System.out.println("You rolled " + randomNumber + "!");

    }

    @Override
    public int sipsToDrink() {
        return sipsToDrink;
    }

    @Override
    public int highestAttackDie() {
        return toBeat;
    }

    @Override
    public String dialogBoxType() {
        return dialogBoxType;
    }

    @Override
    public Player attackedPlayer() {
        return attackedPlayer;
    }
}
