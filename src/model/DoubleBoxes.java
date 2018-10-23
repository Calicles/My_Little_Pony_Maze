package model;

public class DoubleBoxes {
	
	Rectangle screen;
	Rectangle scrollBox;

	public DoubleBoxes(Rectangle screen, Rectangle scrollBox) {
		this.screen= screen;
		this.scrollBox= scrollBox;
	}
	
	public int getScreenBeginX() {return screen.getBeginX();}
	public int getScreenWidth() {return screen.getWidth();}
	public int getScreenBeginY() {return screen.getBeginY();}
	public int getScreenHeight() {return screen.getHeight();}
	
	public boolean isPlayerOnTopScroll(int yPosition) {
		return yPosition <= scrollBox.getBeginY();
	}
	public boolean isPlayerOnBottomScroll(int yPosition) {
		return yPosition >= scrollBox.getHeight();
	}
	public boolean isPlayerOnLeftScroll(int xPosition) {
		return xPosition <= scrollBox.getBeginX();
	}
	public boolean isPlayerOnRightScroll(int xPosition) {
		return xPosition >= scrollBox.getWidth();
	}
	
	public void scroll(int xVector, int yVector) {
		screen.translate(xVector, yVector);
		scrollBox.translate(xVector, yVector);
	}
	
}
