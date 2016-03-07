package util;

import java.io.File;


// lets get some metadata!
// http://www.fightingquaker.com/myid3/

public class MusicManager {

	public static String[] getSongs()
	{
		File folder = new File("../src/songs");
		File[] files = folder.listFiles();
		String[] titles =  new String[files.length];

		int count = 0;
		for(File f: files)
		{
			int songTitleIndex = f.toString().indexOf("songs") + 6;
			String songTitle = count + " : "+ f.toString().substring(songTitleIndex);
			titles[count] =  songTitle;
			count++;
		}
		return titles;
	}

	//Testing only remove
	public static void main(String[] args){
			String[] m =  MusicManager.getSongs();
			for(String s: m){
				System.out.println(s);
			}
			while(true){
			}
	}
}
