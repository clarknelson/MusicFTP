package util;

// https://github.com/mpatric/mp3agic
import util.mp3agic.Mp3File;
import util.mp3agic.ID3v1;
import util.mp3agic.ID3v2;

import java.io.File;

public class Song {

    File file = null;
    Mp3File mp3file = null;
    String filename = "";
    String path = "";
    long length = 0;
    int bitrate = 0;
    String trackNumber = "";
    String artist = "";
    String title = "";
    String album = "";
    String year = "";
    String genre = "";
    boolean hasAlbumArt = false;
    byte[] albumArt = null;


    public Song(String p){
        getMetadata(p);
    }
    public Song(String f, String p){
        this.filename = f;
        getMetadata(p);
    }

    private void getMetadata(String pathname){
        try{
            this.mp3file = new Mp3File(pathname);
            this.file = new File(pathname);
            this.path = pathname;
            this.length = mp3file.getLengthInSeconds();
            this.bitrate = mp3file.getBitrate();
            if(mp3file.hasId3v1Tag()){
                getId3v1Data();
            }
            if(mp3file.hasId3v2Tag()){
                getId3v2Data();
            }
        } catch (Exception e){
            Util.catchException("Could not open mp3 file to read metadata", e);
        }
    }
    private void getId3v1Data(){
        ID3v1 id3v1Tag = this.mp3file.getId3v1Tag();
        this.trackNumber = id3v1Tag.getTrack();
        this.artist = id3v1Tag.getArtist();
        this.title = id3v1Tag.getTitle();
        this.album = id3v1Tag.getAlbum();
        this.year = id3v1Tag.getYear();
        this.genre = id3v1Tag.getGenreDescription();
    }
    private void getId3v2Data(){
        ID3v2 id3v2Tag = this.mp3file.getId3v2Tag();
        this.trackNumber = id3v2Tag.getTrack();
        this.artist = id3v2Tag.getArtist();
        this.title = id3v2Tag.getTitle();
        this.album = id3v2Tag.getAlbum();
        this.year = id3v2Tag.getYear();
        this.genre = id3v2Tag.getGenreDescription();

        this.albumArt = id3v2Tag.getAlbumImage();
        if (this.albumArt != null) {
            this.hasAlbumArt = true;
            //System.out.println("Have album image data, length: " + this.albumArt.length + " bytes");
            //System.out.println("Album image mime type: " + id3v2Tag.getAlbumImageMimeType());
        }

        // available in Id3v2 but we dont need it
        //System.out.println("Comment: " + id3v2Tag.getComment());
        //System.out.println("Composer: " + id3v2Tag.getComposer());
        //System.out.println("Publisher: " + id3v2Tag.getPublisher());
        //System.out.println("Original artist: " + id3v2Tag.getOriginalArtist());
        //System.out.println("Album artist: " + id3v2Tag.getAlbumArtist());
        //System.out.println("Copyright: " + id3v2Tag.getCopyright());
        //System.out.println("URL: " + id3v2Tag.getUrl());
        //System.out.println("Encoder: " + id3v2Tag.getEncoder());
    }

    public String getFileName(){
        //Util.printMsg(this.filename);
        return this.filename;
    }
    public String getPath(){
        //Util.printMsg(this.path);
        return this.path;
    }
    /** Returns length of song in seconds */
    public long getLength(){
        //Util.printMsg(this.length);
        return this.length;
    }
    /** Returns "quality" of the audio file */
    public int getBitrate(){
        //Util.printMsg(this.bitrate);
        return this.bitrate;
    }
    /** Returns the location this song on the album */
    public String getTrackNumber(){
        //Util.printMsg(this.trackNumber);
        return this.trackNumber;
    }

    public String getArtist(){
        //Util.printMsg(this.artist);
        return this.artist;
    }
    public String getTitle(){
        //Util.printMsg(this.title);
        return this.title;
    }
    public String getAlbum(){
        //Util.printMsg(this.album);
        return this.album;
    }
    public String getYear(){
        //Util.printMsg(this.year);
        return this.year;
    }
    public String getGenre(){
        //Util.printMsg(this.genre);
        return this.genre;
    }
    public File getFile(){
        return this.file;
    }

    // if you want to be a baws, write public File getAlbumArt()
    //boolean hasAlbumArt = false;
    //byte[] albumArt = null;
}
