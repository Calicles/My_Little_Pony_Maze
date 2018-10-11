package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

import type.AbstractTileMap;

public class Map extends AbstractTileMap {
	
	public Map(String[] ImagesUrl, int[][] map) throws IOException {
		super(initSet(ImagesUrl), map);
	}

	private static HashMap<Integer, BufferedImage> initSet(String[] imagesUrl) throws IOException {
		HashMap<Integer, BufferedImage> tileSet= new HashMap<>();
		File file= null;
		int index= 0;
		for(String url:imagesUrl) {
			file= new File(url);
			BufferedImage image= ImageIO.read(file);
			tileSet.put(index, image);
		}
		return tileSet;
	}

}
