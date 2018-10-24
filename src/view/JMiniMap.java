package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

import managers.LevelManager;
import type.LevelListener;

public class JMiniMap extends JPanel implements LevelListener {
	
	public static final int ECHELLE= 10;
	
	private LevelManager model;

	public JMiniMap(LevelManager model) {
		this.model= model;
		this.setListener();
	}

	public int getHeight() {return model.getMapHeight() / ECHELLE;}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawBackGround(g);
		drawLevel(g);
		drawPlayer(g);
		drawScreen(g);
	}
	
	@Override
	public Dimension getPreferredSize() {
		Dimension d= model.getDimension();
		int mapWidth= d.width / ECHELLE;
		int mapHeight= d.height / ECHELLE;
		return new Dimension(mapWidth, mapHeight);
	}
	
	private void drawScreen(Graphics g) {
		Color old= g.getColor();
		int x, y, width, height;
		g.setColor(Color.YELLOW);
		x= model.getScreenX() / ECHELLE;
		y= model.getScreenY() / ECHELLE;
		width= model.getScreenWidth() / ECHELLE;
		height= model.getScreenHeight() / ECHELLE;
		
		g.drawRect(x, y, width, height);
		g.setColor(old);
	}

	private void drawPlayer(Graphics g) {
		Color old= g.getColor();
		int x, y;
		x= model.getPlayerX() / ECHELLE;
		y= model.getPlayerY() / ECHELLE;
		g.setColor(Color.RED);
		g.fillRect(x, y, 5, 5);
		g.setColor(old);
	}

	private void drawLevel(Graphics g) {
		model.drawMiniMap(g, ECHELLE);
	}

	private void drawBackGround(Graphics g) {
		Color old= g.getColor();
		int mapWidth, mapHeight;
		mapWidth= model.getMapWidth() / ECHELLE;
		mapHeight= model.getMapHeight() / ECHELLE;
		g.setColor(Color.GREEN);
		g.fillRect(0, 0, mapWidth, mapHeight);
		g.setColor(old);
	}

	public void update() {
		this.repaint();
	}
	
	public void setListener() {
		this.model.AddListener(this);
	}

}
