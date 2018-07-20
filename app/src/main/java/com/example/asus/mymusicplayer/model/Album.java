package com.example.asus.mymusicplayer.model;

/**
 * Created by ASUS on 20/07/2018.
 */

public class Album {
    private int aPicture;
    private String aArtist;
    private String aAlbum;

    public Album(){

    }
    public Album(int aPicture,String aArtist, String aAlbum){

        this.aPicture = aPicture;
        this.aArtist = aArtist;
        this.aAlbum = aAlbum;
    }

    public int getaPicture() {
        return aPicture;
    }

    public String getaArtist() {
        return aArtist;
    }

    public String getaAlbum() {
        return aAlbum;
    }
}
