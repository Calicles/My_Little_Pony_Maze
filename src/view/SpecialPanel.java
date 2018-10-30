package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.JPanel;

import managers.LevelManager;
import type.LevelListener;

public class SpecialPanel extends JPanel implements LevelListener{
	
	private LevelManager model;
	
	public SpecialPanel(LevelManager model) {
		this.model= model;
		this.model.AddListener(this);
	}
	
	
	@Override
	public Dimension getPreferredSize() {
		return model.getDimension();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		try {
			model.draw(g);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void update() {
		this.repaint();
	}
	
	public void playerAnimeLeft() {model.playerAnimeLeft();}
	public void playerAnimeRight() {model.playerAnimeRight();}
	public void playerAnimeUp() {model.playerAnimeUp();}
	public void playerAnimeDown() {model.playerAnimeDown();}
	
	public void playerMoves(int xVector, int yVector) throws IOException {
		model.playerMoves(xVector, yVector);
	}
	public void playerStopRight() {
		model.playerStopRight();
	}
	public void playerStopLeft() {
		model.playerStopLeft();
	}
	public void playerStopUp() {
		model.playerStopUp();
	}
	public void playerStopDown() {
		model.playerStopDown();
	}
	
}
