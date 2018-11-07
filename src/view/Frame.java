package view;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.JFrame;

import managers.LevelManager;

public class Frame extends JFrame {

	private SpecialPanel panel;
	
	public Frame() throws IOException {
		LevelManager model= new LevelManager();
		ButtonPanel buttons= new ButtonPanel(model);
		panel= new SpecialPanel(model);
		this.setLayout(new BorderLayout());
		this.add(panel, BorderLayout.CENTER);
		this.add(buttons, BorderLayout.SOUTH);
		this.addKeyListener(new InternImageListener());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setTitle("My Little Pony");
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	private class InternImageListener implements KeyListener
	{

		@Override
		public void keyPressed(KeyEvent e) {
			try {
				if(e.getKeyCode() == KeyEvent.VK_RIGHT)
					panel.playerMoves(4, 0);
				else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
					panel.playerMoves(-4, 0);	
				}else if(e.getKeyCode() == KeyEvent.VK_UP)
					panel.playerMoves(0, -4);
				else if(e.getKeyCode() == KeyEvent.VK_DOWN)
					panel.playerMoves(0, 4);
			}catch(IOException ioe) {}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_RIGHT)
				panel.playerStopRight();
			else if(e.getKeyCode() == KeyEvent.VK_LEFT)
				panel.playerStopLeft();	
			else if(e.getKeyCode() == KeyEvent.VK_UP)
				panel.playerStopUp();
			else if(e.getKeyCode() == KeyEvent.VK_DOWN)
				panel.playerStopDown();
		}

		@Override
		public void keyTyped(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_RIGHT)
				panel.playerAnimeRight();
			else if(e.getKeyCode() == KeyEvent.VK_LEFT)
				panel.playerAnimeLeft();	
			else if(e.getKeyCode() == KeyEvent.VK_UP)
				panel.playerAnimeUp();
			else if(e.getKeyCode() == KeyEvent.VK_DOWN)
				panel.playerAnimeDown();
			
		}
		
	}
	
}
