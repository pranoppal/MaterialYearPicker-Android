package com.pranoppal.yearpicker;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

public class NumberAdapter extends BaseAdapter {

    private Context context;
    private int[] yearsArray = {0,1,2,3,4,5,6,7,8,9,10};
    public NumberAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return yearsArray.length;
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
        TextView textView;

        if (convertView == null) {
            textView = new TextView(context);
            textView.setLayoutParams(new GridView.LayoutParams(50, 10));
            textView.setPadding(8, 8, 8, 8);
        }
        else
        {
            textView = (TextView) convertView;
        }
        textView.setText(yearsArray[position]);
        return textView;
    }
}
