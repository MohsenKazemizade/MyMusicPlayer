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

public class Main4Activity extends AppCompatActivity {

    private AudioManager mAudioManager;
    private MediaPlayer mMediaPlayer;

    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                @Override
                public void onAudioFocusChange(int focusChange) {
                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                            focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
//                        mMediaPlayer.pause();
                        mMediaPlayer.seekTo(0);
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
        setContentView(R.layout.activity_main4);
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Track> tracks = new ArrayList<>();
        tracks.add(new Track(R.drawable.xaniar_amadelamvasattangmishe, R.raw.xaniar_amadelamvasattangmishe, "Ama Delam Vasat Tang Mishe"));
        tracks.add(new Track(R.drawable.xaniar_hasoodim_mishe, R.raw.xaniar_hasoodimmishe, "Hasoodim Mishe"));
        tracks.add(new Track(R.drawable.xaniar_khosravi_agemimoondi, R.raw.xaniar_agemimoondi, "Age Mimoondi"));
        tracks.add(new Track(R.drawable.xaniar_nanemishe, R.raw.xaniar_nanemishe, "Na Nemishe"));
        tracks.add(new Track(R.drawable.xaniar_midoonestammiri, R.raw.xaniar_midoonestam_miri, "Midoonestam Miri"));
        tracks.add(new Track(R.drawable.xaniar_shodi_hame_donyam, R.raw.xaniar_shodi_hame_donyam, "Shodi Hame Donyam"));
        tracks.add(new Track(R.drawable.xaniar_nemidooni, R.raw.xaniar_nemidooni, "Nemidooni"));
        tracks.add(new Track(R.drawable.xaniar_feat_sirvankhosravi_nemiramaghab, R.raw.xaniarfeatsirvankhosravi_nemiramaghab, "Nemiram Aghab"));
        tracks.add(new Track(R.drawable.xanyar_khosravi, R.raw.xaniar_khialamrahatboodazat, "Khialam Rahat Bood Azat"));


        ListView musicListView = (ListView)findViewById(R.id.musicList3);

        TrackAdapter adapter = new TrackAdapter(this, tracks);
        musicListView.setAdapter(adapter);

        musicListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                releaseMediaPlayer();

                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
                    mMediaPlayer = MediaPlayer.create(Main4Activity.this, tracks.get(position).getMusic());
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
        releaseMediaPlayer();
//        mMediaPlayer.pause();
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (mMediaPlayer != null){
            mMediaPlayer.start();
        }
    }
}
