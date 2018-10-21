package view;

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
		ImageIcon image;
		appleButton= new JButton("Apple");
		rarityButton= new JButton("Rarity");
		rainbowButton= new JButton("Rainbow");
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
	}

	@Override
	public void update() {
		appleButton.setEnabled(model.isAppleSelectedAndRunning());
		rarityButton.setEnabled(model.isRaritySelectedAndRunning());
		rainbowButton.setEnabled(model.isRainbowSelectedAndRunning());
	}


}
