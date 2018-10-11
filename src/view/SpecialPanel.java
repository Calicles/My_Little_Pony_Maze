package view;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import managers.LevelListener;
import managers.LevelManager;

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
	public void translateXPlayer(int xVector) {
		model.translateXPlayer(xVector);
	}
	public void translateYPlayer(int yVector) {
		model.translateYPlayer(yVector);
	}

}
