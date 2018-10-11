package type;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public abstract class AbstractImage {

	private BufferedImage image;
	private int width;
	private int height;
	
	public AbstractImage(String imageUrl) throws IOException {
		File file= new File(imageUrl);
		image= ImageIO.read(file);
		width= image.getWidth();
		height= image.getHeight();
	}
	
	public int getWidth() {return width;}
	public int getHeight() {return height;}
	public BufferedImage getImage() {return image;}

}
