package model;

import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import type.AbstractLevel;


public class Level extends AbstractLevel {
	
	protected boolean selected;

	public Level(String fileMapUrl, String endImageUrl) throws IOException {
		super(fileMapUrl, endImageUrl);
	}
	
	public boolean isSelected() {return selected;}
	public void selected() {selected= true;}
	public void deselected() {selected= false;}
	
	@Override
	public void drawLevel(Graphics g) throws IOException {
		if(!running) {
			endImage= ImageIO.read(new File(endImageUrl));
			g.drawImage(endImage, 0, 0, null);
		}else
			super.drawLevel(g);
	}



	
}
