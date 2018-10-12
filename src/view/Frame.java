package view;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.JFrame;

import managers.LevelManager;

public class Frame extends JFrame {

	private SpecialPanel panel;
	
	public Frame() throws IOException {
		panel= new SpecialPanel(new LevelManager());
		this.add(panel);
		this.addKeyListener(new ImageListener());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setResizable(false);
		this.setVisible(true);
	}
	
	
	
	
	
	public class ImageListener implements KeyListener
	{

		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_RIGHT)
				panel.playerMovesRight(4);
			else if(e.getKeyCode() == KeyEvent.VK_LEFT)
				panel.playerMovesLeft(-4);	
			else if(e.getKeyCode() == KeyEvent.VK_UP)
				panel.playerMovesUp(-4);
			else if(e.getKeyCode() == KeyEvent.VK_DOWN)
				panel.playerMovesDown(4);
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
