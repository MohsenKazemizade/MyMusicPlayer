package com.example.asus.mymusicplayer;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.asus.mymusicplayer.adapter.TrackAdapter;
import com.example.asus.mymusicplayer.model.Track;

import java.util.ArrayList;

public class Main3Activity extends AppCompatActivity {
    private AudioManager mAudioManager;
    private MediaPlayer mMediaPlayer;

    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                @Override
                public void onAudioFocusChange(int focusChange) {
                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                            focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
                        mMediaPlayer.pause();
//                        mMediaPlayer.seekTo(0);
                    }else if (focusChange == AudioManager.AUDIOFOCUS_GAIN){
                        mMediaPlayer.start();
                    }else if (focusChange == AudioManager.AUDIOFOCUS_LOSS){
                        releaseMediaPlayer();
                    }
                }
            };


    private MediaPlayer.OnCompletionListener mCompletionListener =
            new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    releaseMediaPlayer();
                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Track> tracks = new ArrayList<>();
        tracks.add(new Track(R.drawable.sirvan_khosravi_bargard, R.raw.sirvan_khosravi_bargard, "Bargard"));
        tracks.add(new Track(R.drawable.sirvan_khosravi_ghabeaksekhali, R.raw.sirvan_khosravi_ghabeaksekhali, "Ghabe Axe Khali"));
        tracks.add(new Track(R.drawable.sirvan_khosravi_injajayemondannist, R.raw.sirvan_khosravi_injajayemondannist, "Inja Jaye Mondan Nist"));
        tracks.add(new Track(R.drawable.sirvan_khosravi_khaterateto, R.raw.sirvan_khosravi_khaterateto, "Khaterate To"));
        tracks.add(new Track(R.drawable.sirvan_khosravi_tajobnakon, R.raw.sirvan_khosravi_tajobnakon, "Tajob Nakon"));
        tracks.add(new Track(R.drawable.sirvan_khosravi_zibatarinetefagh, R.raw.sirvan_khosravi_zibatarinetefagh, "Zibatarin Etefagh"));
        tracks.add(new Track(R.drawable.xaniar_feat_sirvankhosravi_nemiramaghab, R.raw.xaniarfeatsirvankhosravi_nemiramaghab, "Nemiram Aghab"));
        tracks.add(new Track(R.drawable.kheili_rooza_gozasht, R.raw.sirvan_khosravi_kheiliroozagozasht, "Kheili Rooza Gozasht"));


        ListView musicListView = (ListView)findViewById(R.id.musicList2);

        TrackAdapter adapter = new TrackAdapter(this, tracks);
        musicListView.setAdapter(adapter);

        musicListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                releaseMediaPlayer();

                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
                    mMediaPlayer = MediaPlayer.create(Main3Activity.this, tracks.get(position).getMusic());
                    mMediaPlayer.start();
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                }

            }
        });

    }

    private void releaseMediaPlayer(){
        if (mMediaPlayer != null) {
            mMediaPlayer.stop();
            mMediaPlayer.release();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        //releaseMediaPlayer();
        mMediaPlayer.pause();
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (mMediaPlayer != null){
            mMediaPlayer.start();
        }
    }
}
