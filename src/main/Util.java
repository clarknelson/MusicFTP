package main;

import java.io.BufferedReader;
import java.io.FileReader;

public class Util {

	public static void readConfigFileAt(String path){
		try(BufferedReader in = new BufferedReader(new FileReader(path))){
			String line = in.readLine();
			while(line != null){
				System.out.println(line);
				line = in.readLine();
			}
		} catch (Exception e){
			printErr("Problem reading config file");
			e.printStackTrace();
		}
	}

	public static void printHelp(){
		printMsg("Usage: java MusicFTP [--client | --server]");
	}
	public static void printMsg(String msg){
		System.out.println(msg);
	}
	public static void printErr(String err){
		System.err.println(err);
	}
}
