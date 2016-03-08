package util;

import java.io.File;
import java.util.ArrayList;

public class MusicManager {

	private int count = 0;
	private String directory = "";
	private ArrayList<Song> songs;

	public MusicManager(String d){
		this.directory = d;
		this.songs = new ArrayList<Song>();
		parseDirectory(this.directory);
	}

	private void parseDirectory(String directory){
		File folder = new File(directory);
		File[] files = folder.listFiles();

		for(File file: files) {
			if(!file.canRead()){
				Util.printMsg("Found unreadable file: " + file.getName());
				continue;
			}
			if(file.isDirectory()){
				parseDirectory(file.getPath());
				continue;
			}
			if(file.isFile()){
				if(file.getName().endsWith(".mp3")){
					Song newSong = new Song(file.getPath());
					songs.add(newSong);
				}
			}
		}
	}

	/*
	public String[] getSongs(){
		String[] titles =  new String[this.count];
		return titles;
	}
	*/

	public int getNumberOfSongs(){
		return songs.size();
	}
}
