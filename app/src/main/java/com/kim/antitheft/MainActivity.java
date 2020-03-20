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
    RadioGroup rg;
    RadioButton detectUSB;
    RadioButton detectMove;
    ToggleButton toggleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img = (ImageView) findViewById(R.id.img);
        textView = (TextView) findViewById(R.id.textView);
        rg = (RadioGroup) findViewById(R.id.rg);
        detectUSB = (RadioButton) findViewById(R.id.detectUSB);
        detectMove = (RadioButton) findViewById(R.id.detectMove);
        toggleButton = (ToggleButton) findViewById(R.id.toggleButton);

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if(checkedId == R.id.detectUSB){                                                     // 충전기 감지모드 작동

                    toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                            if(isChecked){                                                           // ON

                                receiver = new MyBroadcastReceiver();
                                IntentFilter intentFilter = new IntentFilter();
                                intentFilter.addAction(Intent.ACTION_POWER_CONNECTED);
                                intentFilter.addAction(Intent.ACTION_POWER_DISCONNECTED);
                                registerReceiver(receiver, intentFilter);

                                Toast.makeText(
                                        getApplicationContext(),
                                        "도난방지가 실행되었습니다.",
                                        Toast.LENGTH_SHORT
                                ).show();

                            }else {                                                                  // OFF
                                unregisterReceiver(receiver);

                                Toast.makeText(
                                        getApplicationContext(),
                                        "도난방지가 중지되었습니다.",
                                        Toast.LENGTH_SHORT
                                ).show();
                            }
                        }
                    });

                }else if (checkedId == R.id.detectMove){                                             // 움직임 감지모드 작동

                    Toast.makeText(
                            getApplicationContext(),
                            "움직임 감지",
                            Toast.LENGTH_SHORT
                    ).show();
                }
            }
        });

    }// onCreate

    public void onToggleClicked(View v){                                                             // 토글버튼 클릭 시 Toast

        boolean on = ((ToggleButton) v).isChecked();

        if(on){                                                                                      // ON

            img.setImageResource(R.drawable.ic_alarm_on_black_24dp);
            textView.setText("도난방지 실행!");

            Toast.makeText(
                    getApplicationContext(),
                    "도난방지가 실행되었습니다.",
                    Toast.LENGTH_SHORT
            ).show();

        }else {                                                                                      // OFF

            img.setImageResource(R.drawable.ic_alarm_off_black_24dp);
            textView.setText("도난방지 중지!");

            Toast.makeText(
                    getApplicationContext(),
                    "도난방지가 중지되었습니다.",
                    Toast.LENGTH_SHORT
            ).show();   // ...
        }
    }
}
