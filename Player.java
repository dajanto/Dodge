import java.awt.Color;
import java.awt.Graphics;

public class Player extends GameObject {

	public Player(int x, int y, int width, int height, ID id) {
		
		super(x, y, width, height, id);
	}

	@Override
	public void update() {

		// Movement
		move();

		// Falling
		fall();

		// Limit player movement concerning the static obstacles
		// x
		if (x >= 1230) {
			x = 1230;
		} else {
			if (x <= 0) {
				x = 0;
			}
		}

		// y
		if (y <= 0) {
			y = 0;
		} else {
			// 651 instead of 650 because 650 is drawn and the 1 is for collision detection
			// TODO Should the player to be allowed to touch the ground?
			if (y >= 650) {
				y = 650;
			}
		}
	}

	@Override
	public void render(Graphics g) {

		g.setColor(Color.BLACK);
		// getHeight() - 1 --> no drawing for the extra collision pixel
		g.fillRect(x, y, getWidth(), getHeight());
	}

	@Override
	public void doCollision() {

		collided = true;
	}
}
