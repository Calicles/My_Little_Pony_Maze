package type;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import model.Tile;

public abstract class AbstractTileMap {
	
	private HashMap<Integer, BufferedImage> tileSet;
	private Tile[][] map;
	private int tile_width, tile_height;
	
	public AbstractTileMap(HashMap<Integer, BufferedImage> tileSet, int[][] map)
	{
		this.tileSet= tileSet;
		this.tile_width= tileSet.get(0).getWidth();
		this.tile_height= tileSet.get(0).getHeight();
		initMap(map);
	}

	public void drawMap(Graphics g)
	{
		Tile tile= null;
		for(int i=0;i<map.length;i++)
		{
			for(int j=0;j<map[0].length;j++)
			{
				tile= map[i][j];
				g.drawImage(tileSet.get(tile.getTile_num()),
						tile.getX(), tile.getY(), null);
			}
		}
	}
	
	private void initMap(int[][] map)
	{
		this.map= new Tile[map.length][map[0].length];
		int tile_num= 0;
		for(int i=0;i<map.length;i++)
		{
			for(int j=0;j<map[0].length;j++)
			{
				tile_num= map[i][j];
				this.map[i][j]= new Tile(tile_num, i * tile_width, 
						j * tile_height);
			}
		}
		
	}
	
	public int[] getDimension() {
		int[] tab= new int[2];
		tab[0]= tileSet.get(0).getWidth() * map.length;
		tab[1]= tileSet.get(0).getHeight() * map[0].length;
		return tab;
	}
	

}
