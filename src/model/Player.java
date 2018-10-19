package model;

import java.awt.image.BufferedImage;
import java.io.IOException;

import type.AbstractEntity;

public class Player extends AbstractEntity {
	
	private BufferedImage[] face, left, right, back;
	


	public Player(String imageUrl, String[][] sprites, int xStart, int yStart) throws IOException {
		super(imageUrl, xStart, yStart);
		face= toBufferedImage(sprites[0]);
		left= toBufferedImage(sprites[1]);
		right= toBufferedImage(sprites[2]);
		back= toBufferedImage(sprites[3]);
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

}
