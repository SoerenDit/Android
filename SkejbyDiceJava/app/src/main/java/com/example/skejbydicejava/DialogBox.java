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
    private String dialogBoxType;
    private String title;
    private String message;

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        sips = listener.sipsToDrink();
        dialogBoxType = listener.dialogBoxType();

        switch (dialogBoxType) {
            case "Lucky":
                title = "Pay first!";
                if (sips == 1) {
                    message = "You have to drink " + sips + " sip, before you may use the lucky die!";
                } else {
                    message = "You have to drink " + sips + " sips, before you may use the lucky die!";
                }
        }


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (dialogBoxType) {
                            case "Lucky": listener.onYesClickedLucky();
                        }

                    }
                })
                .setNegativeButton("Nope", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        return builder.create();
    }

    public interface DialogBoxListener {
        void onYesClickedLucky();

        int sipsToDrink();

        String dialogBoxType();
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
