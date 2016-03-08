package util;

// https://github.com/mpatric/mp3agic
import util.mp3agic.Mp3File;

public class Song {

    public Song(String filename){
        //Util.printMsg("FILE: " + filename);
        getMetadata(filename);
    }

    private void getMetadata(String filename){
		try{
			Mp3File mp3file = new Mp3File(filename);
			System.out.println("Length of this mp3 is: " + mp3file.getLengthInSeconds() + " seconds");
			System.out.println("Bitrate: " + mp3file.getBitrate() + " kbps " + (mp3file.isVbr() ? "(VBR)" : "(CBR)"));
			System.out.println("Sample rate: " + mp3file.getSampleRate() + " Hz");
			System.out.println("Has ID3v1 tag?: " + (mp3file.hasId3v1Tag() ? "YES" : "NO"));
			System.out.println("Has ID3v2 tag?: " + (mp3file.hasId3v2Tag() ? "YES" : "NO"));
			System.out.println("Has custom tag?: " + (mp3file.hasCustomTag() ? "YES" : "NO"));
		} catch (Exception e){
			Util.catchException("Could not open mp3 file to read metadata", e);
		}
	}
}
