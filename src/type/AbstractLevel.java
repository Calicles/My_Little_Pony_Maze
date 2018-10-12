package type;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import javax.imageio.ImageIO;

import model.Geometry;
import model.Map;
import model.Pony;

public abstract class AbstractLevel {

	private Map map;
	private Pony player;
	private Geometry screenSize;
	
	private int tile_width, tile_height;
	
	public AbstractLevel(String fileMapUrl) throws IOException {
		initMap(fileMapUrl);
		setScreenSize();
		tile_width= map.getTile_width();
		tile_height= map.getTile_height();
	}
	
	public Dimension getDimension() {return screenSize.getDimension();}
	
	public void drawLevel(Graphics g)
	{
		map.drawMap(g);
		g.drawImage(player.getImage(), player.getX(), player.getY(), null);
	}
	
	public boolean playerMovesLeft(int xVector) {
		int deltaX= 0;
		int posX= player.getX();
		if(posX < tile_width) {
			if(!isOnLeft(xVector)) {
				if(xVector > posX)
					xVector= 0 - posX;
				player.translateX(xVector);
				return true;
			}
		}else if((deltaX= checkLeftTiles(xVector)) < -1) {
			if(xVector < deltaX)
				xVector= deltaX;
			player.translateX(xVector);
			return true;
		}	
		return false;
	}
	public boolean playerMovesRight(int xVector) {
		int deltaX= 0;
		int posX= player.getX() + player.getWidth();
		deltaX= screenSize.getWidth() - posX;
		if(posX >= screenSize.getWidth() - tile_width) {
			if(!isOnRight(xVector)) {
				if(xVector > deltaX)
					xVector= deltaX;
				player.translateX(xVector);
				return true;
			}
		}else if((deltaX= checkRightTiles(xVector)) > 1) {
			if(xVector > deltaX)
				xVector= deltaX;
			player.translateX(xVector);
			return true;
		}
		return false;
	}
	public boolean playerMovesUp(int yVector) {
		int deltaY= 0;
		int posY= player.getY();
		if(posY < tile_height) {
			if(!isOnTop(yVector)) {
				if(yVector > posY)
					yVector= posY;
				player.translateY(yVector);
				return true;
			}
		}else if((deltaY= checkUpTiles(yVector)) < -1) {
			if(yVector < deltaY)
				yVector= deltaY;
			player.translateY(yVector);
			return true;
		}
		System.out.println(deltaY);
		return false;
	}
	public boolean playerMovesDown(int yVector) {
		int deltaY= 0;
		int posY= player.getY() + player.getHeight();
		deltaY= screenSize.getHeight() - posY;
		if(posY > screenSize.getHeight() - tile_height) {
			if(!isOnBottom(yVector)) {
				if(yVector > deltaY) 
					yVector= deltaY;
				player.translateY(yVector);
				return true;
			}
		}else if((deltaY= checkDownTiles(yVector)) > 1) {
			if(yVector > deltaY) 
				yVector= deltaY;
			player.translateY(yVector);
			return true;
		}
		return false;
	}
	
	private int checkDownTiles(int yVector) {
		int minRow= 0, minCol= 0, maxCol= 0;
		int posY= player.getY() + player.getHeight();
		minRow= posY / tile_height +1;
		minCol= player.getX() / tile_width;
		maxCol= (player.getX() + player.getWidth()) / tile_width;
		return map.isDownTilesTraversable(minRow, minCol, maxCol,
				posY, yVector);
	}

	private int checkUpTiles(int yVector) {
		int minRow= 0, minCol= 0, maxCol= 0;
		int posY= player.getY();
		minRow= posY / tile_height - 1;
		minCol= player.getX() / tile_width;
		maxCol= (player.getX() + player.getWidth()) / tile_width;
		return map.isUpTilesTraversable(minRow, minCol, maxCol, 
				posY, yVector);
	}
	private int checkRightTiles(int xVector) {
		int minRow= 0, maxRow= 0, minCol= 0;
		int posX= player.getX() + player.getWidth();
		minRow= player.getY() / tile_height;
		maxRow= (player.getY() + player.getHeight()) / tile_height;
		minCol= (player.getX() + player.getWidth()) / tile_width + 1;
		return map.isTilesRightTraversable(minRow, maxRow, 
				minCol, posX, xVector);
	}

	private int checkLeftTiles(int xVector) {
		int minRow= 0, maxRow= 0, minCol= 0;
		minRow= player.getY() / tile_height;
		maxRow= (player.getY() + player.getHeight()) / tile_height;
		minCol= player.getX() / tile_width - 1;
		return map.isTilesLeftTraversable(minRow, maxRow, minCol,
				player.getX(), xVector);
	}

	public void setScreenSize() {
		int[] tab= map.getDimension();
		screenSize= new Geometry(tab[0], tab[1]);
	}
	public boolean isOnLeft(int toTest) {
		return screenSize.isOnLeft(player.getX() + toTest);
	}
	public boolean isOnRight(int toTest) {
		int x= player.getX() + player.getWidth();
		return screenSize.isOnRight(x + toTest);
	}
	public boolean isOnTop(int toTest) {
		return screenSize.isOnTop(player.getY() + toTest);
	}
	public boolean isOnBottom(int toTest) {
		int y= player.getY() + player.getHeight();
		return screenSize.isOnBottom(y + toTest);
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
