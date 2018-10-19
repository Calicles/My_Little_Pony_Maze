package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import type.AbstractEntity;

public class Player extends AbstractEntity {
	
	private BufferedImage[] face, left, right, back;
	


	public Player(String imageUrl, String[][] sprites, int xStart, int yStart) throws IOException {
		super(imageUrl, xStart, yStart);
		face= toBufferedImages(sprites[0]);
		left= toBufferedImages(sprites[1]);
		right= toBufferedImages(sprites[2]);
		back= toBufferedImages(sprites[3]);
	}
	
	private void setX(int x)
	{
		this.x= x;
	}
	private void setY(int y)
	{
		this.y= y;
	}
	public void translateX(int xVector)
	{
		this.setX(x + xVector);
	}
	public void translateY(int yVector)
	{
		this.setY(y + yVector);
	}
	
	private BufferedImage[] toBufferedImages(String[] tab) throws IOException {
		BufferedImage[] images= new BufferedImage[tab.length];
		for(int i= 0;i<tab.length;i++) {
			images[i]= ImageIO.read(new File(tab[i]));
		}
		return images;
	}

}
