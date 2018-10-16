package type;

import java.io.IOException;

import model.Rectangle;

public abstract class AbstractEntity extends AbstractImage {
	
	protected int x;
	protected int y;

	public AbstractEntity(String imageUrl, int xStart, int yStart) throws IOException {
		super(imageUrl);
		x= xStart;
		y= yStart;
	}
	
	public int getX() {return x;}
	public int getY() {return y;}


	public Rectangle toRectangle() {
		return new Rectangle(x, x + width, y, y + height);
	}

}
