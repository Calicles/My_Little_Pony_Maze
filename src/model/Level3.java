package model;

import java.awt.Color;
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
	
	@Override
	public int getMapWidth() {return mapSize.getWidth();}

	@Override
	public int getMapHeight() {return mapSize.getHeight();}
	
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
			//drawScrollBox(g);//to remove
		} else
			try {
				g.drawImage(ImageIO.read(new File(endImageUrl)), 0, 0, null);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	private void drawScrollBox(Graphics g) {//to remove
		int coef, y;
		Rectangle box= boxes.getScrollBox();
		coef= box.getBeginY() / (tile_height * 20);
		y= box.getBeginY() - boxes.getScreenBeginY();
		
		g.drawRect(box.getBeginX(), y, 
				tile_width * 10, tile_height * 10);
	}

	private void drawScreen(Graphics g) {
		Tile tile= null;
		Tile[][] map= this.map.getMap();
		HashMap<Integer, BufferedImage> set= this.map.getTileSet();
		int row= boxes.getScreenBeginY() / tile_height;
		int col= boxes.getScreenBeginX() / tile_width;
		int rowMax= boxes.getScreenEndY() / tile_height;
		int colMax= boxes.getScreenEndX() / tile_width;
		int x= 0, y= 0;
		if(boxes.getScreenEndY() % 32 != 0) rowMax ++;
		for(int i= row; i<rowMax; i++) {
			for(int j= col; j<colMax; j++) {
				tile= map[i][j];
				y= tile.getY() - boxes.getScreenBeginY();
				x= tile.getX() - boxes.getScreenBeginX();
				g.drawImage(set.get(tile.getTile_num()), x, 
						y, null);
				if(y < 0) System.out.println(y);
			}
		}
	}

	protected void drawPlayer(Graphics g) {
		int screenPosY= playerScreenPositionY();
		int screenPosX= playerScreenPositionX();
		g.drawImage(player.getImage(), screenPosX, screenPosY, null);
	}
	
	public void drawMiniMap(Graphics g, int ECHELLE) {
		Color old= g.getColor();
		Tile[][] map= this.map.getMap();
		int num= 0, x, y;
		int width= tile_width / ECHELLE;
		int height= tile_height / ECHELLE;
		g.setColor(Color.ORANGE);
		for(int i=0; i<map.length; i++) {
			for(int j=0;j<map[0].length;j++) {
				num= map[i][j].getTile_num();
				x= map[i][j].getX() / ECHELLE;
				y= map[i][j].getY() / ECHELLE;
				if(num > Tile.SOLID) {
					g.fillRect(x, y, width, height);
				}
			}
		}
		g.setColor(old);
	}
	
	protected int playerScreenPositionY() {
		int posY= player.getY() - boxes.getScreenBeginY();
		return posY;
	}
	protected int playerScreenPositionX() {
		int posX= player.getX() - boxes.getScreenBeginX();
		return posX;
	}
	
	 @Override
	 public boolean playerMovesUp(int yVector) {
		 if(!screenOnTop() && 
				 boxes.isPlayerOnTopScroll(player.getY()+yVector))
			 boxes.scroll(0, yVector );
		 return super.playerMovesUp(yVector);
	 }
	 
	 @Override
	 public boolean playerMovesDown(int yVector) {
		 if(!screenOnBottom() &&
				 boxes.isPlayerOnBottomScroll(player.getY()+
						 player.getHeight() + yVector))
			 boxes.scroll(0, yVector);
		 return super.playerMovesDown(yVector);
	 }
	 
	 @Override
	 public boolean playerMovesLeft(int xVector) {
		 if(!screenOnLeft() && 
				 boxes.isPlayerOnLeftScroll(player.getX() + xVector))
			 boxes.scroll(xVector, 0);
		 return super.playerMovesLeft(xVector);
	 }
	 
	 @Override
	 public boolean playerMovesRight(int xVector) {
		 if(!screenOnRight() &&
				 boxes.isPlayerOnRightScroll(player.getX() + player.getWidth() + xVector))
			 boxes.scroll(xVector, 0);
		 return super.playerMovesRight(xVector);
	 }
	 
	 private boolean screenOnRight() {
		 return boxes.getScreenEndX() >= mapSize.getEndX();
	 }
	 
	 private boolean screenOnLeft() {
		 return boxes.getScreenBeginX() <= 0;
	 }

	private boolean screenOnBottom() {
		return boxes.getScreenEndY() >= mapSize.getEndY();
	}

	private boolean screenOnTop() {
		return boxes.getScreenBeginY() <= 0;
	}

	public int getScreenX() {
		return boxes.getScreenBeginX();
	}

	public int getScreenY() {
		return boxes.getScreenBeginY();
	}

	public int getScreenWidth() {
		return boxes.getScreenWidth();
	}

	public int getScreenHeight() {
		return boxes.getScreenHeight();
	}

	public int getPlayerX() {
		return player.getX();
	}

	public int getPlayerY() {
		return player.getY();
	}

}
