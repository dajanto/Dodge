import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Window extends Canvas {

	private static final long serialVersionUID = 1L;

	public Window(int width, int height, String title, Game game) {
		
		JFrame frame = new JFrame(title);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(width, height));
		frame.setBounds(450, 20, width, height);
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		frame.setResizable(false);
		frame.add(game);
		frame.setVisible(true);
		frame.setFocusable(true);
		frame.requestFocus();
		
		game.start();
	}

}
