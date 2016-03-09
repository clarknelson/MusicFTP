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

	/** Populates the songs ArrayList with created Song */
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
					Song newSong = new Song(file.getName(), file.getPath());
					songs.add(newSong);
				}
			}
		}
	}

	public void download(String query){
		Util.printMsg(query);
	}

	public void downloadSong(String song){
		Util.printMsg(song);
	}
	public void downloadArtist(String artist){
		Util.printMsg(artist);

	}

	/** Gets a list of filenames */
	public String[] getFileNames(){
		String[] titles = new String[songs.size()];

		int count = 0;
		for(Song song : songs){
			titles[count] = song.getFileName();
			count++;
		}
		return titles;
	}

	public String[] getArtistList(){
		ArrayList<String> artists = new ArrayList<String>();
		ArrayList<Integer> songCount = new ArrayList<Integer>();

		for(Song song : songs){
			String artist = song.getArtist();
			if(artist != null){
				if(!artists.contains(artist)){
					artists.add(artist);
					songCount.add(1);
				} else {
					int index = artists.indexOf(artist);
					Integer count = songCount.get(index);
					songCount.set(index, count + 1);
				}
			}
		}

		String[] result = new String[artists.size()];
		for(int i=0; i<result.length; i++){
			result[i] = artists.get(i) + " - " + songCount.get(i) + " songs";
		}
		return result;
	}

	public String[] getSongsByArtist(String query){
		ArrayList<String> s = new ArrayList<String>();

		String artistQuery = query.toLowerCase();

		for(Song song : songs){
			String artist = song.getArtist();

			if(artist != null){
				if(artist.toLowerCase().equals(artistQuery)){
					s.add(song.getFileName());
				}
			}
		}
		String[] answer = new String[s.size()];
		answer = s.toArray(answer);
		return answer;
	}

	public File[] downloadSongsByArtist(String artist){
		Util.printMsg(artist);
		return new File[10];
	}

	public int getNumberOfSongs(){
		return songs.size();
	}
}
