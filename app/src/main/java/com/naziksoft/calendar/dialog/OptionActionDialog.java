package com.naziksoft.calendar.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import com.naziksoft.calendar.R;

/**
 * Created by morozione on 1/4/18.
 */

public class OptionActionDialog extends DialogFragment {
    private static DialogInterface.OnClickListener clickListener;

    public static OptionActionDialog getInstance(DialogInterface.OnClickListener listener) {
        clickListener = listener;
        return new OptionActionDialog();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setItems(R.array.options_action, clickListener);

        return builder.create();
    }
}
