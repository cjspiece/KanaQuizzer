package com.example.cjspiece.kanaquizzer;

import android.app.IntentService;
import android.content.Intent;
import android.media.MediaPlayer;

public class CorrectSoundService extends IntentService {

    public MediaPlayer mp;

    public CorrectSoundService() {
        super("CorrectSoundService");
    }

    @Override
    protected void onHandleIntent (Intent intent) {
        MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.correct);
        mediaPlayer.start();
        mp = mediaPlayer;
    }

    protected void onStop() {
        mp.release();
        mp = null;
    }
}
