package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
		model.draw(g);
	}
	
	public void update() {
		this.repaint();
	}
	
	public void playerMovesRight(int xVector) {
		model.playerMovesRight(xVector);
	}
	public void playerMovesLeft(int xVector) {
		model.playerMovesLeft(xVector);
	}
	public void playerMovesUp(int xVector) {
		model.playerMovesUp(xVector);
	}
	public void playerMovesDown(int xVector) {
		model.playerMovesDown(xVector);
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
