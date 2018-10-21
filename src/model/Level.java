package model;

import java.io.IOException;
import type.AbstractLevel;


public class Level extends AbstractLevel {
	
	protected boolean selected;

	public Level(String fileMapUrl) throws IOException {
		super(fileMapUrl);
	}
	
	public boolean isSelected() {return selected;}
	public void selected() {selected= true;}
	public void deselected() {selected= false;}
	



	
}
