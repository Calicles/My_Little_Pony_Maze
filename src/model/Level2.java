package model;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

import type.AbstractLevel;

public class Level2 extends AbstractLevel {
	
	protected Rectangle screen;

	public Level2(String fileMapUrl, String endImageUrl) throws IOException {
		super(fileMapUrl, endImageUrl);
		screen= new Rectangle(0, 20, 20, 40);
		this.endImageUrl= endImageUrl;
	}
	
	@Override
	public void drawLevel(Graphics g) {
		if(running) {
			drawScreen(g);
			drawPlayer(g);
		} else
			try {
				g.drawImage(ImageIO.read(new File(endImageUrl)), 0, 0, null);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	protected void drawPlayer(Graphics g) {
		int screenPosY= playerScreenPositionY();
		g.drawImage(player.getImage(), player.getX(), screenPosY, null);
	}
	
	protected int playerScreenPositionY() {
		int coef=0;
		coef= player.getY() / (tile_height * 20);
		return player.getY() - (coef * (tile_height * 20));
	}

	protected void drawScreen(Graphics g) {
		Tile tile= null;
		Tile[][] map= this.map.getMap();
		HashMap<Integer, BufferedImage> set= this.map.getTileSet();
		int row= screen.getBeginY();
		int col= screen.getBeginX();
		int rowMax= screen.getHeight();
		int colMax= screen.getWidth();
		int x= 0, y= 0;
		
		for(int i= row; i<rowMax; i++) {
			for(int j= col; j<colMax; j++) {
				tile= map[i][j];
				g.drawImage(set.get(tile.getTile_num()), x * tile_width, 
						y * tile_height, null);
				x++;
			}
			x= 0; y++;
		}
	}
	
	@Override
	public boolean playerMovesUp(int yVector) {
		if(!isOnTop(-8) && isOnTopScreen())
			loadMap(0, -20, -player.getHeight());
		return super.playerMovesUp(yVector);
	}
	@Override
	public boolean playerMovesDown(int yVector) {
		if(!isOnBottom(4) && isOnBottomScreen())
			loadMap(0, 20, player.getHeight());
		return super.playerMovesDown(yVector);
	}
	
	protected boolean isOnBottomScreen() {
		return (playerScreenPositionY() + player.getHeight()) > 
			(20 * tile_height - 4);
	}

	protected boolean isOnTopScreen() {
		return playerScreenPositionY() <= 4;
	}
	
	protected void loadMap(int xVector, int yVector, int playerVector) {
		screen.translate(xVector, yVector);
		player.translateY(playerVector);
	}

}
