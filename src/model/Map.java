package model;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

import type.AbstractTileMap;

public class Map extends AbstractTileMap {
	
	public Map(HashMap<Integer, BufferedImage> tileSet, int[][] map) throws IOException {
		super(tileSet, map);
	}


}
