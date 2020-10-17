package com.pranoppal.yearpicker.ui.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

public class NumberAdapter extends BaseAdapter {

    private Context context;
    private int startDate;
    private int endDate;
    public NumberAdapter(Context context, int startDate, int endDate) {
        this.context = context;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public int getCount() {
        return endDate - startDate + 1;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        TextView dummyTextView = new TextView(context);
        int currentDate = position + startDate;
        dummyTextView.setText(String.valueOf(currentDate));
        return dummyTextView;

    }
}
