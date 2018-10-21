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
		this.setResizable(false);
		this.setVisible(true);
	}
	
	private class InternImageListener implements KeyListener
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
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
}
