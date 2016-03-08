package util;

import java.io.File;

// https://github.com/mpatric/mp3agic
import util.mp3agic.Mp3File;

public class MusicManager {

	private int count = 0;

	private void parseDirectory(String directory){
		File folder = new File(directory);
		File[] files = folder.listFiles();

		for(File file: files) {
			if(!file.canRead()){
				Util.printMsg("Found unreadable file: " + file.getName());
				continue;
			}
			if(file.isDirectory()){
				//Util.printMsg("DIRECTORY: " + f.getPath());
				parseDirectory(file.getPath());
				continue;
			}
			if(file.isFile()){
				if(file.getName().endsWith(".mp3")){
					count++;
					Util.printMsg("FILE: " + file.getName());
				}
			}
		}
	}

	public String[] getSongs(String directory){
		parseDirectory(directory);
		String[] titles =  new String[this.count];
		return titles;
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
			Util.catchException("Could not open mp3 file", e);
		}
	}
}
