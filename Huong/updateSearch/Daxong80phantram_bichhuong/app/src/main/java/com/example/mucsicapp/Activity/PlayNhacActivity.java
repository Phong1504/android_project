package com.example.mucsicapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mucsicapp.Adapter.ViewPagerPlayNhac;
import com.example.mucsicapp.Fragment.Fragment_Dia_Nhac;
import com.example.mucsicapp.Fragment.Fragment_Play_DS_baohat;
import com.example.mucsicapp.Model.Baihat;
import com.example.mucsicapp.R;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

public class PlayNhacActivity extends AppCompatActivity {
    Toolbar toolbarPlaynhac;
    TextView tvTimesong, tvTotalTime;
    SeekBar skTime;
    ImageButton imgNext, imgsuffle, imgplay, imgpre, imgrepeat;
    ViewPager ViewpagerPlay;
    public static ArrayList<Baihat> mangbaihat =  new ArrayList<>();
    public static ViewPagerPlayNhac adapternhac;
    Fragment_Dia_Nhac fragmentDiaNhac;
    Fragment_Play_DS_baohat fragment_play_ds_baohat;
    MediaPlayer mediaPlayer;
    int position =0;
    boolean repeat = false;
    boolean checkrandom = false;
    boolean next = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_nhac);
        //dam bao hieu nang mang
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        GetData();
        INit();
        eventClick();
    }

    private void eventClick() {
        //khi co ca khuc hat , thay doi hinh dang nut
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (adapternhac.getItem(1) != null){
                    if(mangbaihat.size() > 0){
                        fragmentDiaNhac.Playnhac(mangbaihat.get(0).getHinhBaihat());
                        handler.removeCallbacks(this);
                    }
                    else {
                        handler.postDelayed(this, 300);
                    }
                }
            }
        }, 500);
        imgplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    imgplay.setImageResource(R.drawable.iconplay);
                }else{
                    mediaPlayer.start();
                    imgplay.setImageResource(R.drawable.iconpause);
                }
            }
        });

        imgrepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (repeat == false){
                    if (checkrandom == true){
                        checkrandom = false;
                        imgrepeat.setImageResource(R.drawable.iconsyned);
                        imgsuffle.setImageResource(R.drawable.iconsuffle);
                    }
                    imgrepeat.setImageResource(R.drawable.iconsyned);
                    repeat=true;
                }else {
                    imgrepeat.setImageResource(R.drawable.iconrepeat);
                    repeat=false;
                }
            }
        });
        imgsuffle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkrandom == false){
                    if (repeat == true){
                        repeat = false;
                        imgsuffle.setImageResource(R.drawable.iconshuffled);
                        imgrepeat.setImageResource(R.drawable.iconrepeat);
                    }
                    imgsuffle.setImageResource(R.drawable.iconshuffled);
                    checkrandom=true;
                }else {
                    imgsuffle.setImageResource(R.drawable.iconsuffle);
                    checkrandom=false;
                }
            }
        });
        skTime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
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
        //Nut Next
        imgNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mangbaihat.size()> 0){
                    if (mediaPlayer.isPlaying() || mediaPlayer != null){
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer=null;
                    }
                    if (position < (mangbaihat.size())){
                        imgplay.setImageResource(R.drawable.iconpause);
                        position++;
                        if(repeat==true){
                            if (position==0){
                                position = mangbaihat.size();
                            }
                            position -=1;
                        }
                        if(checkrandom==true){
                            Random random = new Random();
                            int index = random.nextInt(mangbaihat.size());
                            if(index==position){
                                position = index -1;
                            }
                            position=index;
                        }
                        if (position>(mangbaihat.size() -1)){
                            position = 0;
                        }
                        new PlayMP3().execute(mangbaihat.get(position).getLinkBaihat());
                        fragmentDiaNhac.Playnhac(mangbaihat.get(position).getHinhBaihat());
                        getSupportActionBar().setTitle(mangbaihat.get(position).getTenBaihat());
                    }
                }
                imgpre.setClickable(false);
                imgNext.setClickable(false);
                Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imgpre.setClickable(true);
                        imgNext.setClickable(true);
                    }
                }, 5000);
            }
        });

        imgpre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mangbaihat.size()> 0){
                    if (mediaPlayer.isPlaying() || mediaPlayer != null){
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer=null;
                    }
                    if (position < (mangbaihat.size())){
                        imgplay.setImageResource(R.drawable.iconpause);
                        position--;
                        if(position<0){
                            position = mangbaihat.size() -1;
                        }
                        if(repeat==true){
                            position +=1;
                        }
                        if(checkrandom==true){
                            Random random = new Random();
                            int index = random.nextInt(mangbaihat.size());
                            if(index==position){
                                position = index -1;
                            }
                            position=index;
                        }

                        new PlayMP3().execute(mangbaihat.get(position).getLinkBaihat());
                        fragmentDiaNhac.Playnhac(mangbaihat.get(position).getHinhBaihat());
                        getSupportActionBar().setTitle(mangbaihat.get(position).getTenBaihat());
                    }
                }
                imgpre.setClickable(false);
                imgNext.setClickable(false);
                Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imgpre.setClickable(true);
                        imgNext.setClickable(true);
                    }
                }, 5000);
            }
        });
    }

    private void GetData() {
        Intent intent = getIntent();
        mangbaihat.clear();
        if (intent != null) {
            if (intent.hasExtra("ca khuc")){
                Baihat baihat= intent.getParcelableExtra("ca khuc");
                mangbaihat.add(baihat);
            };
            if (intent.hasExtra("cacbaihat")){
                ArrayList<Baihat> mangbaihatplAY = intent.getParcelableArrayListExtra("cacbaihat");
                mangbaihat = mangbaihatplAY;
            }
        }
    }

    private void INit() {
        toolbarPlaynhac = findViewById(R.id.toolbarPlaynhac);
        tvTimesong = findViewById(R.id.tvTimeSong);
        tvTotalTime = findViewById(R.id.tvTotalTimesong);
        imgplay = findViewById(R.id.imgbuttonplay);
        imgpre = findViewById(R.id.imgbuttonPre);
        imgNext = findViewById(R.id.imgbuttonNext);
        imgsuffle = findViewById(R.id.imgbuttonSuffle);
        imgrepeat = findViewById(R.id.imgbuttonrepeat);
        skTime = findViewById(R.id.seekbarSong);
        ViewpagerPlay = findViewById(R.id.viewPagerPlaynhac);
        setSupportActionBar(toolbarPlaynhac);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarPlaynhac.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                mediaPlayer.stop();
                mangbaihat.clear();
            }
        });
        toolbarPlaynhac.setTitleTextColor(Color.WHITE);

        fragmentDiaNhac = new Fragment_Dia_Nhac();
        fragment_play_ds_baohat = new Fragment_Play_DS_baohat();
        adapternhac = new ViewPagerPlayNhac(getSupportFragmentManager());
        adapternhac.AddFragment(fragment_play_ds_baohat);
        adapternhac.AddFragment(fragmentDiaNhac);
        ViewpagerPlay.setAdapter(adapternhac);
        fragmentDiaNhac = (Fragment_Dia_Nhac) adapternhac.getItem(1);
        if (mangbaihat.size() > 0){
            getSupportActionBar().setTitle(mangbaihat.get(0).getTenBaihat());
            new PlayMP3().execute(mangbaihat.get(0).getLinkBaihat());
            imgplay.setImageResource(R.drawable.iconpause);
        }

    }

    class PlayMP3 extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {
            return strings[0];

        }

        @Override
        protected void onPostExecute(String baihat) {
            super.onPostExecute(baihat);
            try {
            mediaPlayer = new MediaPlayer();
            //lay nhac nghe online
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
                throw new RuntimeException(e);
            }
            mediaPlayer.start();
            TimeSong();
        }
    }

    private void TimeSong() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        tvTotalTime.setText(simpleDateFormat.format(mediaPlayer.getDuration()));
        //update lai thoi gian khi keo thanh thoi gian nghe nhac
        skTime.setMax(mediaPlayer.getDuration());
    }

}