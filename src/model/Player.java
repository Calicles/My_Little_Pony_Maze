package model;

import java.io.IOException;

import type.AbstractEntity;

public class Player extends AbstractEntity {
	


	public Player(String imageUrl, int xStart, int yStart) throws IOException {
		super(imageUrl, xStart, yStart);
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
