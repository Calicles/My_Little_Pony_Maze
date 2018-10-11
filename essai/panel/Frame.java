package panel;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Frame extends JFrame {

		
		private ImagePanel panel;
		private JButton avancerButton;
		
		public Frame() throws IOException
		{
			panel = new ImagePanel("images/tempest.jpg");
			avancerButton= new JButton("avancer");
			avancerButton.addActionListener(e->{
				panel.setX(2);
			});
			this.setLayout(new BorderLayout());
			this.add(panel, BorderLayout.CENTER);
			this.add(avancerButton, BorderLayout.SOUTH);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setVisible(true);
			this.addKeyListener(new ImageListener());
			this.requestFocus();
			this.pack();
		}
		public class ImageListener implements KeyListener
		{

			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_RIGHT)
					panel.setX(4);
				else if(e.getKeyCode() == KeyEvent.VK_LEFT)
					panel.setX(-4);	
				else if(e.getKeyCode() == KeyEvent.VK_UP)
					panel.setY(-4);
				else if(e.getKeyCode() == KeyEvent.VK_DOWN)
					panel.setY(4);
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
		
		public static void main(String[] arg)
		{
			SwingUtilities.invokeLater(()->{
				try {
					new Frame();
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
		}


}
