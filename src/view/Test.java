package view;

import java.io.IOException;

import javax.swing.SwingUtilities;

public class Test {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(()->{
			try {
				new Frame();
			} catch (IOException e) {
				e.printStackTrace();
			}
		});

	}

}
