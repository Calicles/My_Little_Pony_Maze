package panel;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {
	
	private BufferedImage image;
	private BufferedImage incruste;
	private int x;
	private int y;
	
	public ImagePanel(String url) throws IOException
	{
		File file= new File(url);
		if(file.exists()) {
			image= ImageIO.read(file);
			incruste= ImageIO.read(new File("images/twilight.png"));
			x= image.getWidth()/2;
			y= image.getHeight()/2;
		}else
			System.out.println("erreur");
	}

	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(image, 0, 0, null);
		g.drawImage(incruste, x, y, null);
	}
	
	@Override
	public Dimension getPreferredSize() {
		int w= image.getWidth(null);
		int h= image.getHeight(null);
		return new Dimension(w, h);
	}
	
	public void setX(int x)
	{
		this.x += x;
		this.repaint();
	}
	
	public void setY(int y)
	{
		this.y += y;
		this.repaint();
	}

}
