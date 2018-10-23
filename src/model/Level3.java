package model;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

import type.AbstractLevel;

public class Level3 extends AbstractLevel {
	
	protected DoubleBoxes boxes;

	public Level3(String fileMapUrl, String endImageUrl) throws IOException {
		super(fileMapUrl, endImageUrl);
		initBoxes();
	}
	
	protected void initBoxes() {
		Rectangle screen= new Rectangle(0, 20*tile_width, 20*tile_height,
				40* tile_height);
		Rectangle scrollBox= new Rectangle(5*tile_width, 15*tile_width,
				25*tile_height, 35*tile_height);
		this.boxes= new DoubleBoxes(screen, scrollBox);
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

	private void drawScreen(Graphics g) {
		Tile tile= null;
		Tile[][] map= this.map.getMap();
		HashMap<Integer, BufferedImage> set= this.map.getTileSet();
		int row= boxes.getScreenBeginY() / tile_height;
		int col= boxes.getScreenBeginX() / tile_width;
		int rowMax= boxes.getScreenHeight() / tile_height;
		int colMax= boxes.getScreenWidth() / tile_width;
		int x= 0, y= 0;
		
		for(int i= row; i<rowMax; i++) {
			for(int j= col; j<colMax; j++) {
				tile= map[i][j];
				y= tile.getY() - boxes.getScreenBeginY();
				x= tile.getX() - boxes.getScreenBeginX();
				g.drawImage(set.get(tile.getTile_num()), x, 
						y, null);
			}
		}
		
	}

	protected void drawPlayer(Graphics g) {
		int screenPosY= playerScreenPositionY();
		int screenPosX= playerScreenPositionX();
		g.drawImage(player.getImage(), screenPosX, screenPosY, null);
	}
	
	protected int playerScreenPositionY() {
		int coef=0;
		coef= player.getY() / (tile_height * 20);
		return player.getY() - (coef * (tile_height * 20));
	}
	protected int playerScreenPositionX() {
		int coef=0;
		coef= player.getX() / (tile_width * 20);
		return player.getX() - (coef * (tile_width * 20));
	}
	
	 @Override
	 public boolean playerMovesUp(int yVector) {
		 System.out.println("in");
		 if(!isOnTop(-4) && boxes.isPlayerOnBottomScroll(player.getY()+yVector))
			 boxes.scroll(0, yVector);
		 return super.playerMovesUp(yVector);
	 }

}
