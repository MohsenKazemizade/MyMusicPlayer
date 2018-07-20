package com.example.asus.mymusicplayer.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asus.mymusicplayer.R;
import com.example.asus.mymusicplayer.model.Album;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 21/07/2018.
 */

public class AlbumAdapter extends ArrayAdapter<Album> {

    public AlbumAdapter(@NonNull Context context, @NonNull List<Album> objects) {
        super(context, 0, objects);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_album_item, parent, false);
        }
        Album album = getItem(position);

        ImageView pic = (ImageView) convertView.findViewById(R.id.picture2);
        assert album != null;
        pic.setImageResource(album.getaPicture());

        TextView artist = (TextView) convertView.findViewById(R.id.artist);
        artist.setText(album.getaArtist());
        TextView albumTitle = (TextView) convertView.findViewById(R.id.album);
        albumTitle.setText(album.getaAlbum());

        return convertView;
    }


}
