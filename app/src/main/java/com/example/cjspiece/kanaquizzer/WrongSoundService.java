package com.example.cjspiece.kanaquizzer;

import android.app.IntentService;
import android.content.Intent;
import android.media.MediaPlayer;

public class WrongSoundService extends IntentService {

    public MediaPlayer mp;

    public WrongSoundService() {
        super("WrongSoundService");
    }

    @Override
    protected void onHandleIntent (Intent intent) {
        MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.wrong);
        mediaPlayer.start();
        mp = mediaPlayer;
    }

    protected void onStop() {
        mp.release();
        mp = null;
    }
}
