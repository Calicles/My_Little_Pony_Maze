package model;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.util.HashMap;

import javax.imageio.ImageIO;

import type.AbstractLevel;
import type.FileException;

public class Level extends AbstractLevel {

	public Level(String fileMapUrl, String PonyUrl) throws IOException {
		super(initMap(fileMapUrl), PonyUrl, 0, 0);
	}

	private static Map initMap(String fileMapUrl) throws IOException {
		HashMap<Integer, BufferedImage> tileSet= null;
		int[][] map= null;
		int[] tab= new int[2];
		
		try(FileReader r= new FileReader(fileMapUrl)){
		BufferedReader reader= new BufferedReader(r);
		String[] nbrTile= reader.readLine().split(" ");
		if(nbrTile[0].equals("tileSet"))
			tileSet= lireSet(Integer.parseInt(nbrTile[1]), reader);
		//finish lireMap(); avec BufferedReader
		}
		return new Map(tileSet, map);
	}

	private static int[][] lireMap(int[] tab, StreamTokenizer tok) {
		int iMax= tab[0];
		int jMax= tab[1];
		int[][] map= new int[iMax][jMax];
		for(int i=0; i<iMax;i++) {
			for(int j=0; j<jMax;j++) {
				try {
					tok.nextToken();
				} catch (IOException e) {
					fileFormatError("un int");
				}
				map[i][j]= (int) tok.nval;
			}
		}
		return null;
	}

	private static HashMap<Integer, BufferedImage> lireSet(int nval, BufferedReader reader) {
		HashMap<Integer, BufferedImage> tileSet= new HashMap<>();
		String url= "", tmp[]= null;
		BufferedImage image= null;
		int tile_num= 0;
		for(int i= 0; i<nval;i++) {
			try {
				tmp= reader.readLine().split(" ");
				tile_num= Integer.parseInt(tmp[0]);
				url= tmp[1];
				image= ImageIO.read(new File(url));
				tileSet.put(tile_num, image);
			}catch(IOException ioe) {fileFormatError(ioe.getMessage()+" un int puis une url");
			}catch(NumberFormatException nfe) {fileFormatError("un int");}
		}
		return tileSet;
	}
	private static void fileFormatError(String msg) {
		throw new FileException("était attendu: "+msg);
	}
	
}
