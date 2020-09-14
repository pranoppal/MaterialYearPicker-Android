package com.pranoppal.materialyearpicker;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.pranoppal.yearpicker.AbstractDialog;
import com.pranoppal.yearpicker.YearPickerView;
import com.pranoppal.yearpicker.interfaces.DialogInterface;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        final YearPickerView yearPickerView = new YearPickerView.Builder(this)
                .setPositiveButton(new AbstractDialog.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        Toast.makeText(MainActivity.this, "Ok clicked", Toast.LENGTH_SHORT).show();
                        dialogInterface.dismiss();
                    }
                })
                .setNegativeButton(new AbstractDialog.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        Toast.makeText(MainActivity.this, "Cancel clicked", Toast.LENGTH_SHORT).show();
                        dialogInterface.dismiss();
                    }
                })
                .setStartDate(1970)
                .setEndDate(2020)
                .build();
        Button button = findViewById(R.id.dialog);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yearPickerView.show();
            }
        });
    }
}
