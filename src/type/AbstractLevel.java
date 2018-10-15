package type;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import javax.imageio.ImageIO;

import model.Map;
import model.Pony;
import model.Rectangle;
import model.Tile;

public abstract class AbstractLevel {

	protected Map map;
	protected Pony player;
	protected Rectangle screenSize;
	
	protected boolean running;
	protected int tile_width, tile_height;
	
	public AbstractLevel(String fileMapUrl) throws IOException {
		initMap(fileMapUrl);
		setScreenSize();
		tile_width= map.getTile_width();
		tile_height= map.getTile_height();
		running= true;
	}
	
	public Dimension getDimension() {return screenSize.getDimension();}

	public void setScreenSize() {
		int[] tab= map.getDimension();
		screenSize= new Rectangle(tab[0], tab[1]);
	}
	
	public void drawLevel(Graphics g)
	{
		if(running) {
			map.drawMap(g);
			g.drawImage(player.getImage(), player.getX(), player.getY(), null);
		}else {
			Color color= g.getColor();
			g.setColor(Color.BLACK);
			g.drawString("SUCCESS", 50, 50);
			g.setColor(color);
		}
	}
	
	public boolean playerMovesLeft(int xVector) {
		if(isPlayerOnExit())
			running= false;
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
		if(isPlayerOnExit())
			running= false;
		int deltaX= 0;
		int posX= player.getX() + player.getWidth();
		if(posX >= screenSize.getWidth() - tile_width) {
			if(!isOnRight(xVector)) {
				deltaX= screenSize.getWidth() - posX;
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
		if(isPlayerOnExit())
			running= false;
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
		return false;
	}
	public boolean playerMovesDown(int yVector) {
		if(isPlayerOnExit())
			running= false;
		int deltaY= 0;
		int posY= player.getY() + player.getHeight();
		if(posY > screenSize.getHeight() - tile_height) {
			if(!isOnBottom(yVector)) {
				deltaY= screenSize.getHeight() - posY;
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
		Rectangle tiles= null;
		Tile solidTile= null;
		int minRow= 0, minCol= 0, maxCol= 0;
		int posY= player.getY() + player.getHeight();
		minRow= posY / tile_height +1;
		minCol= player.getX() / tile_width;
		maxCol= (player.getX() + player.getWidth()) / tile_width;
		tiles= new Rectangle(minRow, minRow, minCol, maxCol);
		solidTile= map.isSolidTileOnRoad(tiles);
		if(solidTile == null)
			return yVector;
		return solidTile.getY() - posY - 1;
	}

	private int checkUpTiles(int yVector) {
		Tile solidTile= null;
		Rectangle tiles= null;
		int minRow= 0, minCol= 0, maxCol= 0;
		int posY= player.getY();
		minRow= posY / tile_height - 1;
		minCol= player.getX() / tile_width;
		maxCol= (player.getX() + player.getWidth()) / tile_width;
		tiles= new Rectangle(minRow, minRow, minCol, maxCol);
		solidTile= map.isSolidTileOnRoad(tiles);
		if(solidTile == null)
			return yVector;
		return (solidTile.getY() + tile_height) - posY;
	}
	
	private int checkRightTiles(int xVector) {
		Tile solidTile= null;
		Rectangle tiles= null;
		int minRow= 0, maxRow= 0, minCol= 0;
		int posX= player.getX() + player.getWidth();
		minRow= player.getY() / tile_height;
		maxRow= (player.getY() + player.getHeight()) / tile_height;
		minCol= (player.getX() + player.getWidth()) / tile_width + 1;
		tiles= new Rectangle(minRow, maxRow, minCol, minCol);
		solidTile= map.isSolidTileOnRoad(tiles);
		if(solidTile == null)
			return xVector;
		return solidTile.getX() - posX - 1;
	}

	private int checkLeftTiles(int xVector) {
		Tile solidTile= null;
		Rectangle tiles= null;
		int minRow= 0, maxRow= 0, minCol= 0;
		minRow= player.getY() / tile_height;
		maxRow= (player.getY() + player.getHeight()) / tile_height;
		minCol= player.getX() / tile_width - 1;
		tiles= new Rectangle(minRow, maxRow, minCol, minCol);
		solidTile= map.isSolidTileOnRoad(tiles);
		if(solidTile == null)
			return xVector;
		return (solidTile.getX() + tile_width) - player.getX();
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
	public boolean isPlayerInBox(Tile tile) {
		Rectangle box= new Rectangle(tile.getX(), tile.getX() +
				tile_width,tile.getY(), tile.getY() + tile_height);
		Rectangle entity= player.toRectangle();
		return Rectangle.isInBox(box, entity);
	}
	public boolean isPlayerOnExit() {
		Tile exit= map.findExit();
		return isPlayerInBox(exit);
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
