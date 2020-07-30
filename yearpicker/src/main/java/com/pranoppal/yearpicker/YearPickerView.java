package com.pranoppal.yearpicker;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.pranoppal.yearpicker.model.DialogButton;

public class YearPickerView extends AbstractDialog {

    protected YearPickerView(@NonNull final Activity activity,
                             @NonNull DialogButton mPositiveButton,
                             @NonNull DialogButton mNegativeButton) {
        super( activity , mPositiveButton , mNegativeButton );

        final AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        LayoutInflater layoutInflater = activity.getLayoutInflater();

        View dialogView = createView( layoutInflater , null);

        builder.setView(dialogView);

        mDialog = builder.create();

//
//        alertDialog.setView(LayoutInflater.from(context).inflate(R.layout.main_calendar,null));
////        inflate(getContext(), R.layout.main_calendar , this);
//        gridView = alertDialog.findViewById(R.id.year_grid_view);
//        gridView.setAdapter(new NumberAdapter(context));
//        alertDialog.findViewById(R.id.ok).setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                alertDialog.dismiss();
//            }
//        });
//        alertDialog.findViewById(R.id.cancel).setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                alertDialog.dismiss();
//            }
//        });
    }

    public static class Builder {
        private Activity activity;
        private int startRange;
        private int endRange;
        private DialogButton positiveButton;
        private DialogButton negativeButton;

        public Builder(@NonNull Activity activity){
            this.activity = activity;
        }

        @NonNull
        public Builder setPositiveButton(@NonNull OnClickListener onClickListener){
            positiveButton = new DialogButton(onClickListener);
            return this;
        }

        @NonNull
        public Builder setNegativeButton(@NonNull OnClickListener onClickListener){
            negativeButton = new DialogButton(onClickListener);
            return this;
        }

        @NonNull
        public YearPickerView build(){
            return new YearPickerView(activity, positiveButton, negativeButton);
        }
    }
}
