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

<<<<<<< HEAD
/*
    This game has XXX phases:
    Phase 0: Roll first time for attacking
 */

public class MainActivity extends AppCompatActivity {
=======
public class MainActivity extends AppCompatActivity implements DialogBox.DialogBoxListener {
>>>>>>> 32e955f0645977d2ce7b0660117ed3033ee3cb4f
    private ImageView imageViewDie1, imageViewDie2;
    private ImageView imageViewPos2, imageViewPos3, imageViewPos4;
    private ImageView imageViewLuckyDie1, imageViewLuckyDie2, imageViewLuckyDie3, imageViewLuckyDie4;
    private TextView textViewPos1Sips, textViewPos2Sips, textViewPos3Sips, textViewPos4Sips;
    private TextView textViewPos1Name, textViewPos2Name, textViewPos3Name, textViewPos4Name;
    private TextView textViewMessage;
    private Button rollButton;
    private Die attackDie1, attackDie2;
    private Player attackingPlayer, pos2Player, pos3Player, pos4Player;
    private Player defendingPlayer;
    private List<Player> players;
    private int sipsToDrink;
    private int toBeat;
    private int defenceDie;
    private String dialogBoxType;
    public static MediaPlayer mediaPlayerRoll;
    public static MediaPlayer mediaPlayer;
    private Random rng = new Random();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);

        players = new ArrayList<>();
        players.add(new Player("Søren", 1, "brown",R.raw.rotating));
        players.add(new Player("Nikolaj", 2, "green",R.raw.dolphin));
        players.add(new Player("Bjørn", 3, "blue",R.raw.rubberduck));
        players.add(new Player("Christian", 4, "red",R.raw.carhorn));
        attackDie1 = new Die("white");
        attackDie2 = new Die("white");
        mediaPlayerRoll = MediaPlayer.create(this, R.raw.diceroll);
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
        playSound(attackingPlayer.getMedia());
        setDefaultValues();
        textViewMessage.setText(attackingPlayer.getName() + "'s turn. Roll!");
    }

    private void playSound(int sound) {
        if(mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
        mediaPlayer = MediaPlayer.create(this, sound);
        mediaPlayer.start();

    }

    private void placePlayers() {
        for (Player p : players) {
            switch (p.getPos()) {
                case 1:
                    textViewPos1Name.setText(p.getName());
                    textViewPos1Sips.setText("" + p.getSips());
                    imageViewLuckyDie1.setImageResource(p.getLuckyDie().getImage());
                    attackingPlayer = p;
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
        rollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attackRoll();
            }
        });

        imageViewLuckyDie1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sipsToDrink = attackingPlayer.getLuckyDie().getNumber();
                dialogBoxType = "Lucky";
                DialogBox dialogBox = new DialogBox();
                dialogBox.show(getSupportFragmentManager(), "Eksempel1");
            }
        });

        imageViewPos2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerIsUnderAttack(pos2Player);
            }
        });

        imageViewPos3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerIsUnderAttack(pos3Player);
                ;
            }
        });

        imageViewPos4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerIsUnderAttack(pos4Player);
            }
        });
    }

    private void attackRoll() {
        attackDie1.roll(imageViewDie1);
        attackDie2.roll(imageViewDie2);
        if (!specialRoll()) {
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
    }

    private boolean specialRoll() {
        if (attackDie1.getNumber() == attackDie2.getNumber()) {
            switch (attackDie1.getNumber()) {
                case 1:
                    // ones();
                    break;
                case 2:
                    // deepWaterSoloYolo();
                    break;
                case 3:
                    pair(3);
                    break;
                case 4:
                    pair(4);
                    break;
                case 5:
                    pair(5);
                    break;
                case 6:
                    // sixes();
                    break;
            }
            return true;
        }
        return false;
    }

    private void pair(int i) {
        sipsToDrink = i;
        dialogBoxType = "Pair";
        DialogBox dialogBox = new DialogBox();
        dialogBox.show(getSupportFragmentManager(), "Eksempel1");
    }

    @Override
    public void onPairs() {
        for (Player p : players) {
            p.addSips(sipsToDrink);
        }
        rotatePlayers();
    }

    private void playerIsUnderAttack(Player p) {
        sipsToDrink = attackValue();
        dialogBoxType = "YouHaveBeenAttacked";
        defendingPlayer = p;
        DialogBox dialogBox = new DialogBox();
        dialogBox.show(getSupportFragmentManager(), "Eksempel2");
    }

    @Override
    public void onIWillDrinkMySips() {
        defendingPlayer.addSips(attackValue());
        updateSips();
        rotatePlayers();
    }

    @Override
    public void onIWillDefendMyself() {
        toBeat = Math.max(attackDie1.getNumber(), attackDie2.getNumber());
        dialogBoxType = "DefenceTime";
        DialogBox dialogBox = new DialogBox();
        dialogBox.show(getSupportFragmentManager(), "Eksempel1");
    }

    @Override
    public void onRollClickedDefence() {
        MainActivity.mediaPlayerRoll.start();
        defenceDie = rng.nextInt(6) + 1;
        if (defenceDie >= toBeat) {
            playSound(R.raw.denied);
            dialogBoxType = "SuccesfulDefence";
            DialogBox dialogBox = new DialogBox();
            dialogBox.show(getSupportFragmentManager(), "Eksempel1");
        } else {
            if(rng.nextInt(2)==1) {
                playSound(R.raw.fail);
            } else {
                playSound(R.raw.fail2);
            }
            dialogBoxType = "UnsuccesfulDefence";
            DialogBox dialogBox = new DialogBox();
            dialogBox.show(getSupportFragmentManager(), "Eksempel1");
        }
    }

    @Override
    public void onSuccesfulDefence() {
        attackingPlayer.addSips(1);
        rotatePlayers();
    }

    @Override
    public void onUnsuccesfulDefence() {
        defendingPlayer.addSips(attackValue() + 1);
        defendingPlayer.getLuckyDie().increaseDie();
        rotatePlayers();
    }




    private int attackValue() {
        return (attackDie1.getNumber() + attackDie2.getNumber()) / 2;
    }

    private void updateSips() {
        textViewPos1Sips.setText("" + attackingPlayer.getSips());
        textViewPos2Sips.setText("" + pos2Player.getSips());
        textViewPos3Sips.setText("" + pos3Player.getSips());
        textViewPos4Sips.setText("" + pos4Player.getSips());
    }

    @Override
    public void onYesClickedLucky() {
        attackingPlayer.getLuckyDie().roll(imageViewLuckyDie1);
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
    public int defenceDie() {
        return defenceDie;
    }

    @Override
    public Player defendingPlayer() {
        return defendingPlayer;
    }

    @Override
    public Player attackingPlayer() {
        return attackingPlayer;
    }

    @Override
    public String dialogBoxType() {
        return dialogBoxType;
    }


}
