package model;

import java.awt.Dimension;

public class Geometry {

	private int width;
	private int height;
	
	public Geometry(int width, int height) {
		this.width= width;
		this.height= height;
	}
	
	public int getWidth() {return width;}
	public int height() {return height;}
	
	public boolean isOnLeft(int toTest) {return toTest <= 0;}
	public boolean isOnRight(int toTest) {return toTest >= width;}
	public boolean isOnTop(int toTest) {return toTest < 0;}
	public boolean isOnBottom(int toTest) {return toTest >= height;}
	
	public Dimension getDimension() {return new Dimension(width, height);}
}
