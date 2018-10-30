package managers;

import java.awt.Dimension;
import java.awt.Graphics;
import java.io.IOException;
import java.util.ArrayList;

import model.Level;
import model.Level2;
import model.Level3;
import type.AbstractLevel;
import type.LevelListener;

public class LevelManager {

	private Level levelApple;
	private Level levelRarity;
	private Level levelRainbow;
	private Level2 levelFlutter;
	private Level3 levelPinky;
	private AbstractLevel levelRunning;
	private ArrayList<LevelListener> listeners;
	
	public LevelManager() throws IOException {
		listeners= new ArrayList<>();
		levelApple= new Level("map/level.txt", "images/fin/apple.png");
		levelRarity= new Level("map/levelRarity.txt", "images/fin/apple.png");
		levelRainbow= new Level("map/levelRainbow.txt", "images/fin/apple.png");
		levelRunning= levelApple;
		levelApple.selected();
		levelFlutter= null;
		levelPinky= null;
	}
	
	public int getMapWidth() {return levelRunning.getMapWidth();}
	public int getMapHeight() {return levelRunning.getMapHeight();}
	
	public void playerAnimeLeft() {levelRunning.playerAnimeLeft(); this.fireUpdate();}
	public void playerAnimeRight() {levelRunning.playerAnimeRight(); this.fireUpdate();}
	public void playerAnimeUp() {levelRunning.playerAnimeUp(); this.fireUpdate();}
	public void playerAnimeDown() {levelRunning.playerAnimeDown(); this.fireUpdate();}
	
	private void switchLevel3() throws IOException {
		levelRunning= levelPinky= new Level3("map/levelPinky.txt", "images/fin/apple.png");
		levelFlutter= null;
	}
	
	private void switchLevel2() throws IOException {
		levelRunning= levelFlutter= new Level2("map/levelFlutter.txt", "images/fin/apple.png");
		levelApple=null; levelRarity= null; levelRainbow= null;
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
	
	public void playerMoves(int xVector, int yVector) throws IOException {
		if(levelPinky== null && levelFlutter== null &&
				!levelApple.isRunning()
				&& !levelRarity.isRunning() 
				&& !levelRainbow.isRunning()) {
			switchLevel2();
		}else if(levelApple== null && levelFlutter!= null &&
				!levelFlutter.isRunning()) {
			switchLevel3();
		}else {
			if(xVector == 0) {
				if(yVector < 0) {
					this.playerMovesUp(yVector);
				}else {
					this.playerMovesDown(yVector);
				}
			}else {
				if(xVector < 0) {
					this.playerMovesLeft(xVector);
				}else {
					this.playerMovesRight(xVector);
				}
			}
		}	
		this.fireUpdate();
	}

	public void playerMovesLeft(int xVector) {
		levelRunning.playerMovesLeft(xVector);
	}
	public void playerMovesRight(int xVector) {
		levelRunning.playerMovesRight(xVector);
	}
	public void playerMovesUp(int yVector) {
		levelRunning.playerMovesUp(yVector);
	}
	public void playerMovesDown(int yVector) {
		levelRunning.playerMovesDown(yVector);
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

	public boolean isLevelsNull() {
		return levelApple == null;
	}

	public boolean isLevelFlutterNull() {
		return levelFlutter == null;
	}

	public boolean isLevelPinkyNull() {
		return levelPinky == null;
	}

	public void drawMiniMap(Graphics g, int ECHELLE) {
		levelPinky.drawMiniMap(g, ECHELLE);
	}

	public int getScreenX() {
		return levelPinky.getScreenX();
	}

	public int getScreenY() {
		return levelPinky.getScreenY();
	}

	public int getScreenWidth() {
		return levelPinky.getScreenWidth();
	}

	public int getScreenHeight() {
		return levelPinky.getScreenHeight();
	}

	public int getPlayerX() {
		return levelPinky.getPlayerX();
	}

	public int getPlayerY() {
		return levelPinky.getPlayerY();
	}
	
}
