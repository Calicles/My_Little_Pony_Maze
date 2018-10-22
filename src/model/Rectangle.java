package model;

import java.awt.Dimension;

public class Rectangle {

	private int beginX, beginY;
	private int width;
	private int height;
	
	public Rectangle(int width, int height) {
		beginX= 0; beginY= 0;
		this.width= width;
		this.height= height;
	}
	public Rectangle(int beginX, int width, int beginY, int heigth) {
		this.beginX= beginX;
		this.beginY= beginY;
		this.width= width;
		this.height= heigth;
	}
	
	public int getWidth() {return width;}
	public int getHeight() {return height;}
	public int getBeginX() {return beginX;}
	public int getBeginY() {return beginY;}
	public Dimension getDimension() {return new Dimension(width, height);}
	
	public void translate(int x, int y) {
		beginX += x; width += x;
		beginY += y; height += y;
	}
	
	public boolean isOnLeft(int toTest) {return toTest < beginX;}
	public boolean isOnRight(int toTest) {return toTest > width;}
	public boolean isOnTop(int toTest) {return toTest < beginY;}
	public boolean isOnBottom(int toTest) {return toTest >= height;}
	
	public static boolean isOver(int x, int position) {return x>position;}
	public static boolean isBefore(int x, int position) {return x<position;}
	public static  boolean isInBox(Rectangle box, Rectangle position) {
		return box.getBeginX() <= position.getBeginX() &&
				box.getWidth() >= position.getWidth() &&
				box.getBeginY() <= position.getBeginY() &&
				box.getHeight() >= position.getHeight();
	}
	
}
