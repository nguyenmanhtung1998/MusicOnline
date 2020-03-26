package com.example.musiconline.Service;

import android.app.Service;
import android.content.ContentUris;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.IBinder;

import java.io.IOException;
import java.util.ArrayList;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.PowerManager;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.musiconline.Model.Baihat;

public class MusicService extends Service implements
        MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener,
        MediaPlayer.OnCompletionListener {
    private MediaPlayer player;
    //song list
    private final IBinder musicBind = new MusicService.MusicBinder();
    private ArrayList<Baihat> baihatArrayList;
    //current position
    private int songPosn;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return musicBind;
    }
    @Override
    public boolean onUnbind(Intent intent){
        player.stop();
        player.release();
        return false;
    }
    public class playSong extends AsyncTask<String,Void,String> {
        Baihat baihat = baihatArrayList.get(songPosn);
        String currSong = baihat.getIdBaiHat();

        @Override
        protected String doInBackground(String... strings) {
            return null;
        }

        @Override
        protected void onPostExecute(String baihat) {
            super.onPostExecute(baihat);
            try {
                player.setDataSource(baihat);
                player.prepareAsync();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
    @Override
    public void onCompletion(MediaPlayer mp) {
        player.setOnCompletionListener(this);
        player.stop();
        player.reset();
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        player.setOnErrorListener(this);
        return false;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        player.setOnPreparedListener(this);
        mp.start();
    }
    public void setSong(int songIndex){
        songPosn=songIndex;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        songPosn=0;
        player = new MediaPlayer();
        initMusicPlayer();
    }
    public void initMusicPlayer(){
        player.setWakeMode(getApplicationContext(),
                PowerManager.PARTIAL_WAKE_LOCK);
        player.setAudioStreamType(AudioManager.STREAM_MUSIC);
    }
    public class MusicBinder extends Binder {
        public MusicService getService() {
            return MusicService.this;
        }
    }
    public void setList(ArrayList<Baihat> baihats){
        baihatArrayList=baihats;
    }
}
