package com.kim.antitheft;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    MyBroadcastReceiver receiver;
    ToggleButton toggleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toggleButton = (ToggleButton) findViewById(R.id.toggleButton);

        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){

                    receiver = new MyBroadcastReceiver();
                    IntentFilter intentFilter = new IntentFilter();
                    intentFilter.addAction(Intent.ACTION_POWER_CONNECTED);
                    intentFilter.addAction(Intent.ACTION_POWER_DISCONNECTED);
                    registerReceiver(receiver, intentFilter);

                }else {
                    unregisterReceiver(receiver);
                }
            }
        });

    }// onCreate

    public void onToggleClicked(View v){

        boolean on = ((ToggleButton) v).isChecked();

        if(on){                                                                                      // 실행

            Toast.makeText(
                    getApplicationContext(),
                    "도난방지가 실행되었습니다.",
                    Toast.LENGTH_SHORT
            ).show();

        }else {                                                                                      // 중지

            Toast.makeText(
                    getApplicationContext(),
                    "도난방지가 중지되었습니다.",
                    Toast.LENGTH_SHORT
            ).show();
        }
    }
}
