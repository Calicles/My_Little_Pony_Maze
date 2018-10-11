package type;

import java.awt.Graphics;
import java.io.IOException;

import model.Map;
import model.Pony;

public abstract class AbstractLevel {

	private Map map;
	private Pony player;
	
	public AbstractLevel(Map map, String ponyUrl, int ponyX, int ponyY) throws IOException {
		this.map= map;
		this.player= new Pony(ponyUrl, ponyX, ponyY);
	}
	
	public void drawLevel(Graphics g)
	{
		map.drawMap(g);
		g.drawImage(player.getImage(), player.getX(), player.getY(), null);
	}
	
	public void translateXPlayer(int xVector)
	{
		player.translateX(xVector);
	}
	public void translateYPlayer(int yVector)
	{
		player.translateY(yVector);
	}
	public int[] getDimension() {
		int[] tab= map.getDimension();
		return tab;
	}
	
}
