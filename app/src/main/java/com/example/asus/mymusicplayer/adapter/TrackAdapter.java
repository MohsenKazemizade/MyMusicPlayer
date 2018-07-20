package com.example.asus.mymusicplayer.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.asus.mymusicplayer.R;
import com.example.asus.mymusicplayer.model.Track;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dev on 7/15/2018.
 */


public class TrackAdapter extends ArrayAdapter<Track> {


    public TrackAdapter(@NonNull Context context, @NonNull List<Track> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        Track track = getItem(position);

        ImageView picture = (ImageView) convertView.findViewById(R.id.picture);
        assert track != null;
        picture.setImageResource(track.getPicture());

        TextView title = (TextView)convertView.findViewById(R.id.musicTitle);
        title.setText(track.getTitle());

        return convertView;
    }
}