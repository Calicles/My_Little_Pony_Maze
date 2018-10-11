package model;

import java.io.IOException;
import type.AbstractLevel;

public class Level extends AbstractLevel {

	public Level(String[] tilesUrl, int[][] map, String PonyUrl) throws IOException {
		super(new Map(tilesUrl, map), PonyUrl, 0, 0);
	}
	
}
