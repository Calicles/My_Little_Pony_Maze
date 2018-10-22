package view;

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
	
	public ButtonPanel(LevelManager model) {
		this.model= model;
		init();
	}

	@Override
	public void update() {
		if(!model.isLevelsNull()) {
			appleButton.setEnabled(model.isAppleSelectedAndRunning());
			rarityButton.setEnabled(model.isRaritySelectedAndRunning());
			rainbowButton.setEnabled(model.isRainbowSelectedAndRunning());
		}else
			model.removeListener(this);
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
		appleButton.addActionListener(e->model.switchLeveApple());
		rarityButton.addActionListener(e->model.switchLevelRarity());
		rainbowButton.addActionListener(e->model.switchLevelRainbow());
		model.AddListener(this);
		this.setBackground(Color.BLACK);
		update();
	}


}
