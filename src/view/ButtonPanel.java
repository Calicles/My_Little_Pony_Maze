package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.LayoutManager;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import managers.LevelManager;
import type.LevelListener;

public class ButtonPanel extends JPanel implements LevelListener{

	private LevelManager model;
	private JButton appleButton, rarityButton, rainbowButton;
	private JMiniMap map;
	
	private boolean mapAdded;
	
	public ButtonPanel(LevelManager model) {
		this.model= model;
		map= new JMiniMap(model);
		init();
		mapAdded= false;
	}

	@Override 
	public Dimension getPreferredSize() {
		int width=(int) model.getDimension().getWidth();
		int height=(int) model.getDimension().getHeight() * 2 /map.ECHELLE;
		if(!mapAdded) {
		int d= map.getHeight();
		return new Dimension(width, height);
		}else
			return new Dimension(width, height/2+5);
	}
	@Override
	public void update() {
		if(!model.isLevelsNull()) {
			appleButton.setEnabled(model.isAppleSelectedAndRunning());
			rarityButton.setEnabled(model.isRaritySelectedAndRunning());
			rainbowButton.setEnabled(model.isRainbowSelectedAndRunning());
		}else if(this.isLevelsNull() && !mapAdded){
			this.hideButton();
			this.map.setVisible(true);
			this.mapAdded= true;
		}
		this.repaint();
	}
	
	private void hideButton() {
		this.remove(appleButton);
		this.remove(rarityButton);
		this.remove(rainbowButton);
	}

	private void init() {
		appleButton= new JButton(new ImageIcon("images/boutons/apple.png"));
		rarityButton= new JButton(new ImageIcon("images/boutons/rarity.png"));
		rainbowButton= new JButton(new ImageIcon("images/boutons/Rainbow.png"));
		appleButton.setPreferredSize(new Dimension(75, 75));
		rarityButton.setPreferredSize(new Dimension(75, 75));
		rainbowButton.setPreferredSize(new Dimension(75, 75));
		appleButton.setFocusable(false);
		rarityButton.setFocusable(false);
		rainbowButton.setFocusable(false);
		this.setLayout(new FlowLayout());
		this.add(appleButton);
		this.add(rarityButton);
		this.add(rainbowButton);
		this.add(map);
		this.map.setVisible(false);
		appleButton.addActionListener(e->model.switchLeveApple());
		rarityButton.addActionListener(e->model.switchLevelRarity());
		rainbowButton.addActionListener(e->model.switchLevelRainbow());
		model.AddListener(this);
		this.setBackground(Color.BLACK);
		update();
	}
	
	private boolean isLevelsNull() {
		return model.isLevelsNull() && model.isLevelFlutterNull()
				&& !model.isLevelPinkyNull();
	}


}
