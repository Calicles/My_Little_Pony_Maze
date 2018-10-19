package managers;

import java.awt.Dimension;
import java.awt.Graphics;
import java.io.IOException;
import java.util.ArrayList;

import model.Level;
import type.LevelListener;

public class LevelManager {

	private Level level1;
	private Level levelRunning;
	private ArrayList<LevelListener> listeners;
	
	public LevelManager() throws IOException {
		listeners= new ArrayList<>();
		level1= new Level("map/level.txt"); //to do remove
		levelRunning= level1;
	}

	public void playerMovesLeft(int xVector) {
		if(levelRunning.playerMovesLeft(xVector))
			this.fireUpdate();
	}
	public void playerMovesRight(int xVector) {
		if(levelRunning.playerMovesRight(xVector))//to change
			this.fireUpdate();
	}
	public void playerMovesUp(int yVector) {
		if(levelRunning.playerMovesUp(yVector))//to change
			this.fireUpdate();
	}
	public void playerMovesDown(int yVector) {
		if(levelRunning.playerMovesDown(yVector))//to change
			this.fireUpdate();
	}
	public void playerStopLeft() {
		levelRunning.playerStopLeft();
	}
	public void playerStopRight() {
		levelRunning.playerStopRight();
	}
	public void playerStopUp() {
		levelRunning.playerStopUp();
	}
	public void playerStopDown() {
		levelRunning.playerStopDown();
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
