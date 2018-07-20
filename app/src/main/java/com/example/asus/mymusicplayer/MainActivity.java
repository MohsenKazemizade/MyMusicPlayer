package com.example.asus.mymusicplayer;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


import com.example.asus.mymusicplayer.adapter.AlbumAdapter;
import com.example.asus.mymusicplayer.adapter.TrackAdapter;

import com.example.asus.mymusicplayer.model.Album;
import com.example.asus.mymusicplayer.model.Track;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

//    private AudioManager mAudioManager;
//    private MediaPlayer mMediaPlayer;
//
//    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener =
//            new AudioManager.OnAudioFocusChangeListener() {
//                @Override
//                public void onAudioFocusChange(int focusChange) {
//                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
//                            focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
//                        mMediaPlayer.pause();
////                        mMediaPlayer.seekTo(0);
//                    }else if (focusChange == AudioManager.AUDIOFOCUS_GAIN){
//                        mMediaPlayer.start();
//                    }else if (focusChange == AudioManager.AUDIOFOCUS_LOSS){
//                        releaseMediaPlayer();
//                    }
//                }
//            };
//
//
//    private MediaPlayer.OnCompletionListener mCompletionListener =
//            new MediaPlayer.OnCompletionListener() {
//                @Override
//                public void onCompletion(MediaPlayer mediaPlayer) {
//                    releaseMediaPlayer();
//                }
//            };


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final ArrayList<Album> album = new ArrayList<>();
        album.add(new Album(R.drawable.ehaam,"ایهام","ایهام"));
        album.add(new Album(R.drawable.sirvan_khosravi_ghabeaksekhali,"سیروان خسروی","سیروان خسروی‌"));
        album.add(new Album(R.drawable.xaniar_khosravi_agemimoondi,"زانیار خسوری","زانیار خسوری"));

        ListView musicAlbumList = (ListView) findViewById(R.id.musicList1);


        AlbumAdapter adapter = new AlbumAdapter(this, album);
        musicAlbumList.setAdapter(adapter);

        musicAlbumList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                if (position == 0){
                    Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                    startActivity(intent);
                }else if (position == 1){
                    Intent intent = new Intent(MainActivity.this, Main3Activity.class);
                    startActivity(intent);
                }else {
                    Intent intent = new Intent(MainActivity.this, Main4Activity.class);
                    startActivity(intent);
                }

//                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
//                        AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
//
//                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
//                    mMediaPlayer = MediaPlayer.create(MainActivity.this, album.get(position).getMusic());
//                    mMediaPlayer.start();
//                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
//                }

            }
        });

    }

}