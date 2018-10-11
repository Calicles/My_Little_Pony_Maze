package managers;

import java.awt.Dimension;
import java.awt.Graphics;
import java.io.IOException;
import java.util.ArrayList;

import model.Level;

public class LevelManager {

	private Level level1;
	private Level levelRunning;
	private ArrayList<LevelListener> listeners;
	
	public LevelManager() throws IOException {
		listeners= new ArrayList<>();
		level1= new Level("map/level.txt"); //to do remove
		levelRunning= level1;
	}

	public void translateXPlayer(int xVector)
	{
		if(levelRunning.translateXPlayer(xVector))
			this.fireUpdate();
	}
	public void translateYPlayer(int yVector)
	{
		if(levelRunning.translateYPlayer(yVector))
			this.fireUpdate();
	}
	
	public void draw(Graphics g)
	{
		levelRunning.drawLevel(g);
	}
	
	public Dimension getDimension() {
		return levelRunning.getDimension();
	}
	
	public void AddListener(LevelListener listener) {
		listeners.add(listener);
	}
	
	public void removeListener(LevelListener listener) {
		listeners.remove(listener);
	}
	

	private void fireUpdate() {
		for(LevelListener l:listeners)
			l.update();
	}
	
	
	
	
	
	
	
	
}
