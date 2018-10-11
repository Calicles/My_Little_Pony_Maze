package managers;

import java.awt.Dimension;
import java.awt.Graphics;
import java.io.IOException;
import java.util.ArrayList;

import model.Geometry;
import model.Level;
import type.AbstractLevel;

public class LevelManager {

	private Level level1;
	private AbstractLevel levelRunning;
	private ArrayList<LevelListener> listeners;
	private Geometry screen;
	
	public LevelManager() throws IOException {
		listeners= new ArrayList<>();
		level1= new Level("map/level.txt"); //to do remove
		this.setScreen();
		levelRunning= level1;
	}

	public void translateXPlayer(int xVector)
	{
		if(!screen.isOnLeft(xVector) || !screen.isOnRight(xVector)) {
			levelRunning.translateXPlayer(xVector);
			this.fireUpdate();
		}
	}
	public void translateYPlayer(int yVector)
	{
		if(!screen.isOnBottom(yVector) || !screen.isOnTop(yVector)) {
			levelRunning.translateYPlayer(yVector);
			this.fireUpdate();
		}
	}
	
	public void draw(Graphics g)
	{
		levelRunning.drawLevel(g);
	}
	
	public Dimension getDimension() {
		return screen.getDimension();
	}
	
	public void AddListener(LevelListener listener) {
		listeners.add(listener);
	}
	
	public void removeListener(LevelListener listener) {
		listeners.remove(listener);
	}
	
	private void setScreen() {
		int[] tab= level1.getDimension();
		screen= new Geometry(tab[0], tab[1]);
	}

	private void fireUpdate() {
		for(LevelListener l:listeners)
			l.update();
	}
	
	
	
	
	
	
	
	
}
