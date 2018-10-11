package model;

public class Tile {
	
	public static final int SOLID= 5;

	private int tile_num;
	private int x;
	private int y;
	
	boolean traversable;
	
	public Tile(int tile_num, int x, int y)
	{
		this.tile_num= tile_num;
		this.x= x;
		this.y= y;
		this.traversable= tile_num < SOLID;
	}
	
	public int getTile_num() {return tile_num;}
	public int getX() {return x;}
	public int getY() {return y;}
	
	
}
