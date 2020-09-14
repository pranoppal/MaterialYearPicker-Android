package com.pranoppal.yearpicker;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.pranoppal.yearpicker.model.DialogButton;

public class YearPickerView extends AbstractDialog {

    protected YearPickerView(@NonNull final Activity activity,
                             @NonNull DialogButton mPositiveButton,
                             @NonNull DialogButton mNegativeButton,
                             int startDate,
                             int endDate) {
        super( activity , mPositiveButton , mNegativeButton, startDate, endDate );

        final AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        LayoutInflater layoutInflater = activity.getLayoutInflater();

        View dialogView = createView( layoutInflater , null);

        builder.setView(dialogView);

        mDialog = builder.create();

    }

    public static class Builder {
        private Activity activity;
        private int startDate;
        private int endDate;
        private DialogButton positiveButton;
        private DialogButton negativeButton;

        public Builder(@NonNull Activity activity){
            this.activity = activity;
        }

        @NonNull
        public Builder setStartDate(int startDate){
            this.startDate = startDate;
            return this;
        }

        @NonNull
        public Builder setEndDate(int endDate){
            this.endDate = endDate;
            return this;
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
            return new YearPickerView(activity, positiveButton, negativeButton, startDate, endDate);
        }
    }
}
