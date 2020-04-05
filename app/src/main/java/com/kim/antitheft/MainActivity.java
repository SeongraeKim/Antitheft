package com.kim.antitheft;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    MyBroadcastReceiver receiver;
    ImageView img;
    TextView textView;
    ToggleButton detectUSB, detectMove, toggleButton;
    IntentFilter intentFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img = (ImageView) findViewById(R.id.img);
        textView = (TextView) findViewById(R.id.textView);
        detectUSB = (ToggleButton) findViewById(R.id.toggleButton);
        detectMove = (ToggleButton) findViewById(R.id.toggleButton);
        toggleButton = (ToggleButton) findViewById(R.id.toggleButton);

        receiver = new MyBroadcastReceiver();
        intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_POWER_CONNECTED);
        intentFilter.addAction(Intent.ACTION_POWER_DISCONNECTED);

        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(toggleButton.isChecked()){                                                        // 도난방지 시작 버튼

                    if(detectUSB.isChecked()){
                        registerReceiver(receiver, intentFilter);
                    }

                    img.setImageResource(R.drawable.ic_alarm_on_black_24dp);
                    textView.setText("도난방지 실행!");

                    Toast.makeText(
                            getApplicationContext(),
                            "도난방지가 실행되었습니다.",
                            Toast.LENGTH_SHORT
                    ).show();

                }else{                                                                               // 실행 중지 버튼
                    unregisterReceiver(receiver);


                    img.setImageResource(R.drawable.ic_alarm_off_black_24dp);
                    textView.setText("도난방지 중지!");
                    //detectUSB.setTextOff(null);

                    Toast.makeText(
                            getApplicationContext(),
                            "도난방지가 중지되었습니다.",
                            Toast.LENGTH_SHORT
                    ).show();

                    stopService(new Intent(getApplicationContext(), AlarmService.class));
                }
            }
        });

    }// onCreate
}