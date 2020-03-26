package com.example.musiconline.ManActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.musiconline.Model.Baihat;
import com.example.musiconline.R;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;

public class PlaynhacActivity extends AppCompatActivity {
    Toolbar toolbar;
    SeekBar seekBar;
    Animation animation;
    CircleImageView circleImageView;
    TextView tencasi,tenbaihat;
    MediaPlayer mediaPlayer;
    TextView textViewtimesong, textViewtotaltimesong;
    ImageButton imageButtonplay, imageButtonrepeat, imageButtonnext, imageButtonpre, imageButtonrandom;
    public static ArrayList<Baihat> baihatArrayList = new ArrayList<>();
    boolean repeat = false;
    int position = 0;
    boolean random = false;
    boolean next = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playnhac);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        getdatainten();
        anhxa();
        evenclick();
    }

    private void evenclick() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                if (baihatArrayList.size() > 0) {
                    Playnhac(baihatArrayList.get(position).getHinhbaihat());
                    handler.removeCallbacks(this);
                } else {
                    handler.postDelayed(this, 300);
                }

            }
        }, 500);
        imageButtonplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    imageButtonplay.setImageResource(R.drawable.iconplay);
                } else {
                    mediaPlayer.start();
                    imageButtonplay.setImageResource(R.drawable.iconpause);
                }

            }
        });
        imageButtonrepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (repeat == false) {
                    if (random == true) {
                        random = false;
                        imageButtonrepeat.setImageResource(R.drawable.iconsyned);
                        imageButtonrandom.setImageResource(R.drawable.iconsuffle);
                    }
                    imageButtonrepeat.setImageResource(R.drawable.iconsyned);
                    repeat = true;
                } else {
                    imageButtonrepeat.setImageResource(R.drawable.iconrepeat);
                    repeat = false;
                }
            }
        });
        imageButtonrandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (random == false) {
                    if (repeat == true) {
                        repeat = false;
                        imageButtonrandom.setImageResource(R.drawable.iconshuffled);
                        imageButtonrepeat.setImageResource(R.drawable.iconrepeat);
                    }
                    imageButtonrandom.setImageResource(R.drawable.iconshuffled);
                    random = true;
                } else {
                    random = false;
                    imageButtonrandom.setImageResource(R.drawable.iconsuffle);
                }


            }
        });
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());
            }
        });
        imageButtonnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (baihatArrayList.size() > 0) {
                    if (mediaPlayer.isPlaying() || mediaPlayer != null) {
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    if (position < (baihatArrayList.size())) {
                        imageButtonplay.setImageResource(R.drawable.iconpause);
                        position++;
                        if (repeat == true) {
                            if (position == 0) {
                                position = baihatArrayList.size();
                            }
                            position -= 1;
                        }
                        if (random == true) {
                            Random random = new Random();
                            int index = random.nextInt(baihatArrayList.size());
                            if (index == position) {
                                position = index - 1;
                            }
                            position = index;
                        }
                        if (position > (baihatArrayList.size() - 1)) {
                            position = 0;
                        }
                        new playMp3().execute(baihatArrayList.get(position).getLinkbaihat());
                        Playnhac(baihatArrayList.get(position).getHinhbaihat());
                        toolbar.setTitle(baihatArrayList.get(position).getTenbaihat());
                        updatatime();
                    }
                }
                imageButtonpre.setClickable(false);
                imageButtonnext.setClickable(false);
                Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imageButtonpre.setClickable(true);
                        imageButtonnext.setClickable(true);
                    }
                }, 5000);
            }
        });
        imageButtonpre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (baihatArrayList.size() > 0) {
                    if (mediaPlayer.isPlaying() || mediaPlayer != null) {
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    if (position < (baihatArrayList.size())) {
                        imageButtonplay.setImageResource(R.drawable.iconpause);
                        position--;
                        if (position < 0) {
                            position = baihatArrayList.size() - 1;
                        }
                        if (repeat == true) {
                            position += 1;
                        }
                        if (position > (baihatArrayList.size() - 1)) {
                            position = 0;
                        }
                        if (random == true) {
                            Random random = new Random();
                            int index = random.nextInt(baihatArrayList.size());
                            if (index == position) {
                                position = index - 1;
                            }
                            position = index;
                        }
                        new playMp3().execute(baihatArrayList.get(position).getLinkbaihat());
                        toolbar.setTitle(baihatArrayList.get(position).getTenbaihat());
                        Playnhac(baihatArrayList.get(position).getHinhbaihat());
                        updatatime();
                    }
                }
                imageButtonpre.setClickable(false);
                imageButtonnext.setClickable(false);
                Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imageButtonpre.setClickable(true);
                        imageButtonnext.setClickable(true);
                    }
                }, 5000);
            }
        });
    }

    private void Playnhac(String hinhbaihat) {
        Picasso.with(PlaynhacActivity.this).load(hinhbaihat).into(circleImageView);
        animation= AnimationUtils.loadAnimation(this,R.anim.xoaydianhac);
        circleImageView.startAnimation(animation);
    }

    private void anhxa() {
        circleImageView=findViewById(R.id.imageviewpcicyyeuthich);
        toolbar = findViewById(R.id.toolbarplaynhacyeuthich);
        textViewtimesong = findViewById(R.id.texttimesongyeuthich);
        textViewtotaltimesong = findViewById(R.id.textviewtotltimesongyeuthich);
        imageButtonplay = findViewById(R.id.imagebuttonplayyeuthich);
        imageButtonnext = findViewById(R.id.imagebuttonnextyeuthich);
        seekBar = findViewById(R.id.seekbarsongyeuthich);
        imageButtonpre = findViewById(R.id.imagebuttonreviewyeuthich);
        imageButtonrandom = findViewById(R.id.imagebuttonyeuthich);
        imageButtonrepeat = findViewById(R.id.imagebuttonrepetyeuthich);
        tenbaihat=findViewById(R.id.tenbaihat);
        tencasi=findViewById(R.id.tencasi);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
         if (baihatArrayList.size()>0){
             new playMp3().execute(baihatArrayList.get(position).getLinkbaihat());
             imageButtonplay.setImageResource(R.drawable.iconpause);
         }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying()||mediaPlayer!=null){
                    mediaPlayer.stop();
                    mediaPlayer.release();
                    mediaPlayer = null;
                }
                finish();
            }
        });
    }

    private void getdatainten() {
        Intent intent=getIntent();
        Bundle bundle=intent.getBundleExtra("dulieu");
        if(bundle!=null){
            position=bundle.getInt("int");
            baihatArrayList=bundle.getParcelableArrayList("danhsachbaihat");
        }

    }
    class playMp3 extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... strings) {
            return strings[0];
        }
        @Override
        protected void onPostExecute(String baihat) {
            super.onPostExecute(baihat);
            try {
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mediaPlayer.stop();
                        mediaPlayer.reset();
                    }
                });
                mediaPlayer.setDataSource(baihat);
                mediaPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mediaPlayer.start();
            tenbaihat.setText(baihatArrayList.get(position).getTenbaihat());
            tencasi.setText(baihatArrayList.get(position).getCasi());
            Timesong();
            updatatime();
        }
    }
    private void Timesong() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        textViewtotaltimesong.setText(simpleDateFormat.format(mediaPlayer.getDuration()));
        seekBar.setMax(mediaPlayer.getDuration());
    }
    private void updatatime() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mediaPlayer != null) {
                    seekBar.setProgress(mediaPlayer.getCurrentPosition());
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
                    textViewtimesong.setText(simpleDateFormat.format(mediaPlayer.getCurrentPosition()));
                    handler.postDelayed(this, 300);
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            next = true;

                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        }, 300);
        final Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (next == true) {
                    if (baihatArrayList.size() > 0) {
                        if (mediaPlayer.isPlaying() || mediaPlayer != null) {
                            mediaPlayer.stop();
                            mediaPlayer.release();
                            mediaPlayer = null;
                        }
                        if (position < (baihatArrayList.size())) {
                            imageButtonplay.setImageResource(R.drawable.iconpause);
                            position++;
                            if (repeat == true) {
                                if (position == 0) {
                                    position = baihatArrayList.size();
                                }
                                position -= 1;
                            }
                            if (random == true) {
                                Random random = new Random();
                                int index = random.nextInt(baihatArrayList.size());
                                if (index == position) {
                                    position = index - 1;
                                }
                                position = index;
                            }
                            if (position > (baihatArrayList.size() - 1)) {
                                position = 0;
                            }
                            new playMp3().execute(baihatArrayList.get(position).getLinkbaihat());
                            Playnhac(baihatArrayList.get(position).getHinhbaihat());
                        }
                    }
                    imageButtonpre.setClickable(false);
                    imageButtonnext.setClickable(false);
                    Handler handler1 = new Handler();
                    handler1.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            imageButtonpre.setClickable(true);
                            imageButtonnext.setClickable(true);
                        }
                    }, 5000);
                    next = false;
                    handler1.removeCallbacks(this);
                } else {
                    handler1.postDelayed(this, 1000);
                }
            }
        }, 1000);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mediaPlayer.stop();
        mediaPlayer.release();
        mediaPlayer = null;
        finish();
    }
}
