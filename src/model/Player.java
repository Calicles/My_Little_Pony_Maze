package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import type.AbstractEntity;

public class Player extends AbstractEntity {
	
	private BufferedImage[] face, left, right, back;
	int index;


	public Player(String imageUrl, String[][] sprites, int xStart, int yStart) throws IOException {
		super(imageUrl, xStart, yStart);
		face= toBufferedImages(sprites[0]);
		left= toBufferedImages(sprites[1]);
		right= toBufferedImages(sprites[2]);
		back= toBufferedImages(sprites[3]);
		index= 1;
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
		if(xVector < 0) {
			image= left[index];
			index++;
			if(index == left.length)
				index= 0;
		}else {
			image= right[index];
			index++;
			if(index == right.length)
				index= 0;
		}
		this.setX(x + xVector);
	}
	public void translateY(int yVector)
	{
		if(yVector < 0) {
			image= back[index];
			index++;
			if(index == back.length)
				index= 0;
		}else {
			image= face[index];
			index++;
			if(index == face.length)
				index= 0;
		}
		this.setY(y + yVector);
	}
	
	private void resetIndex() {
		index= 1;
	}
	public void stopLeft() {
		resetIndex();
		image= left[index];
	}
	public void stopRight() {
		resetIndex();
		image= right[index];
	}
	public void stopUp() {
		resetIndex();
		image= back[index];
	}
	public void stopDown() {
		resetIndex();
		image= face[index];
	}
	
	private BufferedImage[] toBufferedImages(String[] tab) throws IOException {
		BufferedImage[] images= new BufferedImage[tab.length];
		for(int i= 0;i<tab.length;i++) {
			images[i]= ImageIO.read(new File(tab[i]));
		}
		return images;
	}

}
