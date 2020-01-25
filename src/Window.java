import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Window extends Canvas {

	private static final long serialVersionUID = 1L;
	private JLabel scoringLabel;

	public Window(int width, int height, String title, Game game) {

        JFrame frame;
		frame = new JFrame(title);
		scoringLabel = new JLabel();
//		grid = new GridLayout(1,2);

		scoringLabel.setSize(70,50);

		scoringLabel.setOpaque(false );
//		scoringLabel.setBackground(Color.white);
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		// TODO
		// frame.setUndecorated(true);
		frame.setResizable(false);
		frame.setFocusable(false);
		frame.add(scoringLabel);
		frame.add(game);
		frame.pack();

		frame.setVisible(true);
		game.start();
	}
	
	public JLabel getScoringLabel() {
		return this.scoringLabel;
	}
}
