import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;

public class Window extends Canvas {

	private static final long serialVersionUID = 1L;

	public Window(int width, int height, String title, Game game) {

		JFrame frame = new JFrame(title);

		frame.setPreferredSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		// TODO
		// frame.setUndecorated(true);
		frame.setResizable(false);
		frame.setFocusable(false);
		frame.add(game);
		frame.pack();

		frame.setVisible(true);
		game.start();
	}
}
