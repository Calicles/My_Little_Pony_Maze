package managers;

import java.awt.Dimension;
import java.awt.Graphics;
import java.io.IOException;
import java.util.ArrayList;

import model.Level;
import type.LevelListener;

public class LevelManager {

	private Level levelApple;
	private Level levelRarity;
	private Level levelRainbow;
	private Level levelRunning;
	private ArrayList<LevelListener> listeners;
	
	public LevelManager() throws IOException {
		listeners= new ArrayList<>();
		levelApple= new Level("map/level.txt", "images/fin/apple.png");
		levelRarity= new Level("map/levelRarity.txt", "images/fin/apple.png");
		levelRainbow= new Level("map/level.txt", "images/fin/apple.png");
		levelRunning= levelApple;
		levelApple.selected();
		this.fireUpdate();
	}
	
	public void switchLeveApple() {
		levelRunning= levelApple;
		levelApple.selected();
		levelRarity.deselected();
		levelRainbow.deselected();
		this.fireUpdate();
	}
	public void switchLevelRarity() {
		levelRunning= levelRarity;
		levelRarity.selected();
		levelApple.deselected();
		levelRainbow.deselected();
		this.fireUpdate();
	}
	public void switchLevelRainbow() {
		levelRunning= levelRainbow;
		levelRainbow.selected();
		levelApple.deselected();
		levelRarity.deselected();
		this.fireUpdate();
	}
	public boolean isAppleSelectedAndRunning() {
		return !levelApple.isSelected() && levelApple.isRunning();
	}
	public boolean isRaritySelectedAndRunning() {
		return !levelRarity.isSelected() && levelRarity.isRunning();
	}
	public boolean isRainbowSelectedAndRunning() {
		return !levelRainbow.isSelected() && levelRainbow.isRunning();
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
		this.fireUpdate();
	}
	public void playerStopRight() {
		levelRunning.playerStopRight();
		this.fireUpdate();
	}
	public void playerStopUp() {
		levelRunning.playerStopUp();
		this.fireUpdate();
	}
	public void playerStopDown() {
		levelRunning.playerStopDown();
		this.fireUpdate();
	}
	
	public void draw(Graphics g) throws IOException
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
