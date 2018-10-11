package type;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.util.HashMap;

import javax.imageio.ImageIO;

import model.Map;
import model.Pony;

public abstract class AbstractLevel {

	private Map map;
	private Pony player;
	
	public AbstractLevel(String fileMapUrl) throws IOException {
		initMap(fileMapUrl);
	}
	
	public void drawLevel(Graphics g)
	{
		map.drawMap(g);
		g.drawImage(player.getImage(), player.getX(), player.getY(), null);
	}
	
	public void translateXPlayer(int xVector)
	{
		player.translateX(xVector);
	}
	public void translateYPlayer(int yVector)
	{
		player.translateY(yVector);
	}
	public int[] getDimension() {
		int[] tab= map.getDimension();
		return tab;
	}
	
	private void initMap(String fileMapUrl) throws IOException {
		HashMap<Integer, BufferedImage> tileSet= null;
		int[][] map= null;
		int playerX= 0, playerY= 0;
		String playerFile[]= null;
		
		try(FileReader r= new FileReader(fileMapUrl)){
			BufferedReader reader= new BufferedReader(r);
			String[] nbrTile= reader.readLine().split(" ");
			if(!nbrTile[0].equals("tileSet"))
				fileFormatError("le mot tileSet");
			tileSet= lireSet(Integer.parseInt(nbrTile[1]), reader);
			map= lireMap(reader);
			playerFile= reader.readLine().split(" ");
			playerX= Integer.parseInt(playerFile[2]);
			playerY= Integer.parseInt(playerFile[3]);
		}
		this.map= new Map(tileSet, map);
		this.player= new Pony(playerFile[1], playerX, playerY);
	}

	private int[][] lireMap(BufferedReader reader) {
		int iMax=0, jMax= 0;
		int[][] map= null;
		String line[]= null;
		try {
			String tailleMap[]= reader.readLine().split(" ");
			if(!tailleMap[0].equals("taille"))
				fileFormatError("taille");
			iMax= lireInt(tailleMap[1]);
			jMax= lireInt(tailleMap[2]);
			map= new int[iMax][jMax];
			for(int i=0; i<iMax;i++) {
				line= reader.readLine().split(" ");
				for(int j=0; j<jMax;j++) {
					map[i][j]= lireInt(line[j]);
				}
			}
		}catch(IOException ioe) {fileFormatError("nombre");}
		return map;
	}

	private HashMap<Integer, BufferedImage> lireSet(int nval, BufferedReader reader) {
		HashMap<Integer, BufferedImage> tileSet= new HashMap<>();
		String url= "", tmp[]= null;
		BufferedImage image= null;
		int tile_num= 0;
		for(int i= 0; i<nval;i++) {
			try {
				tmp= reader.readLine().split(" ");
				tile_num= lireInt(tmp[0]);
				url= tmp[1];
				image= ImageIO.read(new File(url));
				tileSet.put(tile_num, image);
			}catch(IOException ioe) {fileFormatError(ioe.getMessage()+" un int puis une url");
			}catch(NumberFormatException nfe) {fileFormatError("un int");}
		}
		return tileSet;
	}
	private int lireInt(String n) {
		int nbr= 0;
		try {
			nbr= Integer.parseInt(n);
		}catch(NumberFormatException nfe) {fileFormatError("nombre");}
		return nbr;
	}
	private static void fileFormatError(String msg) {
		throw new FileException("était attendu: "+msg);
	}
	
}
