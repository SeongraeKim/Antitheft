package com.kim.antitheft;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {

    MediaPlayer player;

    @Override
    public void onReceive(Context context, Intent intent) {

        if(Intent.ACTION_POWER_CONNECTED == intent.getAction()){                                     // 충전기 연결 시

            Toast.makeText(
                    context,
                    "충전기 연결!",
                    Toast.LENGTH_SHORT
            ).show();

            player.stop();

        }else if (Intent.ACTION_POWER_DISCONNECTED == intent.getAction()){                           // 충전기 분리 시

            Toast.makeText(
                    context,
                    "충전기 분리!",
                    Toast.LENGTH_SHORT
            ).show();

            player = MediaPlayer.create(context, R.raw.siren);
            player.setLooping(true);
            player.start();
        }
    }
}


