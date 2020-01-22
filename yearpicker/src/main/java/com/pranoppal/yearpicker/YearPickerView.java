package com.pranoppal.yearpicker;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

public class YearPickerView extends LinearLayout {
    public YearPickerView(Context context, @Nullable AttributeSet attrs) {
        super( context, attrs );
        inflate(getContext(), R.layout.main_calendar , this);
    }
}
