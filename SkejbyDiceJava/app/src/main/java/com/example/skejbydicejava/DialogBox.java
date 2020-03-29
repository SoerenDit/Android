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
    private String dialogBoxType;
    private String title;
    private String message;
    private Player attackedPlayer;
    private String positiveButton;
    private String negativeButton;

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        sips = listener.sipsToDrink();
        String sipString = "";
        toBeat = listener.highestAttackDie();

        if (sips == 1) {
            sipString = sips + " sip";
        } else {
            sipString = sips + " sips";
        }
        dialogBoxType = listener.dialogBoxType();
        attackedPlayer = listener.attackedPlayer();

        switch (dialogBoxType) {
            case "Lucky":
                title = "Pay first!";
                message = "You have to drink " + sipString + ", before you may use the lucky die!";
                positiveButton = "Ok";
                negativeButton = "Nope";
                break;
            case "YouHaveBeenAttacked":
                title = attackedPlayer.getName() + ", you are under attack!";
                message = "What do you want to do?";
                positiveButton = "I will drink my " + sipString + ".";
                negativeButton = "I will of course defend myself!";
                break;
            case "DefenceTime":
                title = "Defence time!";
                message = "Ok, " + attackedPlayer.getName() + ", you have to roll at least " + toBeat + ":";
                positiveButton = "Roll";
                negativeButton = "";
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
                                listener.onYesClickedAttack();
                                break;
                            case "DefenceTime":
                                listener.onYesClickedDefence();
                        }
                    }
                })
                .setNegativeButton(negativeButton, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (dialogBoxType) {
                            case "YouHaveBeenAttacked":
                                listener.onNoClickedAttack();
                                break;
                        }

                    }
                });
        return builder.create();
    }

    public interface DialogBoxListener {
        void onYesClickedLucky();

        void onYesClickedAttack();
        void onNoClickedAttack();

        void onYesClickedDefence();

        int sipsToDrink();

        int highestAttackDie();

        String dialogBoxType();

        Player attackedPlayer();
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
