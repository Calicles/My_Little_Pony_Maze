package model;

import java.io.IOException;

import type.AbstractImage;

public class Pony extends AbstractImage {
	
	private int x;
	private int y;

	public Pony(String imageUrl, int xDepart, int yDepart) throws IOException {
		super(imageUrl);
		x= xDepart;
		y= yDepart;
	}
	
	public int getX() {return x;}
	public int getY() {return y;}
	
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
	public Rectangle toRectangle() {
		return new Rectangle(x, x + width, y, y + height);
	}
}
