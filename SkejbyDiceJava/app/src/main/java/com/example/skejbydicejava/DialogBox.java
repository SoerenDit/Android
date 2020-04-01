package com.example.skejbydicejava;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDialogFragment;

public class DialogBox extends AppCompatDialogFragment {
    private DialogBoxListener listener;
    private int sips;
    private int toBeat;
    private int defenceDie;
    private String dialogBoxType;
    private String title;
    private String message;
    private Player defendingPlayer;
    private Player attackingPlayer;
    private String positiveButton;
    private String negativeButton;

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        sips = listener.sipsToDrink();
        String sipString = "";
        toBeat = listener.highestAttackDie();
        defenceDie = listener.defenceDie();

        if (sips == 1) {
            sipString = sips + " sip";
        } else {
            sipString = sips + " sips";
        }
        dialogBoxType = listener.dialogBoxType();
        defendingPlayer = listener.defendingPlayer();
        attackingPlayer = listener.attackingPlayer();

        switch (dialogBoxType) {
            case "Lucky":
                title = "Pay first!";
                message = "You have to drink " + sipString + ", before you may use the lucky die!";
                positiveButton = "Ok";
                negativeButton = "Nope";
                break;
            case "YouHaveBeenAttacked":
                title = defendingPlayer.getName() + ", you are under attack!";
                message = "What do you want to do?";
                positiveButton = "I will drink my " + sipString + ".";
                negativeButton = "I will of course defend myself!";
                break;
            case "DefenceTime":
                title = "Defence time!";
                message = "Ok, " + defendingPlayer.getName() + ", you have to roll at least " + toBeat + ":";
                positiveButton = "Roll";
                negativeButton = "";
                break;
            case "SuccesfulDefence":
                System.out.println("This is also odd");
                title = "You rolled " + defenceDie + "! Your defence is succesful!";
                message = attackingPlayer.getName() + " has to drink 1 sip!";
                positiveButton = "Nice";
                break;
            case "UnsuccesfulDefence":
                title = "You rolled " + defenceDie + ". You didn't defend yourself...";
                int toDrink = sips + 1;
                message = "You have to drink " + toDrink + " sips, and your lucky die is increased by one";
                positiveButton = ":(";
                break;
            case "Pair":
                title = "You rolled a pair of " + sips + "'s!";
                message = "Everyone has to drink " + sipString;
                positiveButton = "Cheers";
                break;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton(positiveButton, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (dialogBoxType) {
                            case "Lucky":
                                listener.onYesClickedLucky();
                                break;
                            case "YouHaveBeenAttacked":
                                listener.onIWillDrinkMySips();
                                break;
                            case "DefenceTime":
                                listener.onRollClickedDefence();
                                break;
                            case "SuccesfulDefence":
                                listener.onSuccesfulDefence();
                                break;
                            case "UnsuccesfulDefence":
                                listener.onUnsuccesfulDefence();
                                break;
                            case "Pair":
                                listener.onPairs();
                                break;
                        }
                    }
                })
                .setNegativeButton(negativeButton, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (dialogBoxType) {
                            case "YouHaveBeenAttacked":
                                listener.onIWillDefendMyself();
                                break;
                        }

                    }
                });
        return builder.create();
    }

    public interface DialogBoxListener {
        void onYesClickedLucky();

        void onIWillDrinkMySips();

        void onIWillDefendMyself();

        void onRollClickedDefence();

        int sipsToDrink();

        int highestAttackDie();

        int defenceDie();

        Player defendingPlayer();

        Player attackingPlayer();

        String dialogBoxType();

        void onSuccesfulDefence();

        void onUnsuccesfulDefence();

        void onPairs();
    }

    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (DialogBoxListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    "must implement DialogBoxListener)");
        }
    }
}
