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

		// Coordinates for Player
//		System.out.println("Y:" + _y);
		
		// Limit player movement concerning the static obstacles
		// x
		if(_x >= 1230) {
			_x = 1230;
		} else {
			if(_x <= 0) {
				_x = 0;
			}
		}
		
		// y
		if(_y <= 0) {
			_y = 0;
		} else {
			// 651 instead of 650 because 650 is drawn and the 1 is for collision detection
			// TODO Should the player to be allowed to touch the ground?
			if(_y >= 650) {
				_y = 650;
			}
		}
	}

	@Override
	public void render(Graphics g) {
		
		g.setColor(Color.MAGENTA);
		// getHeight() - 1 --> no drawing for the extra collisionn pixel
		g.fillRect(_x, _y, getWidth(), getHeight());
	}

	@Override
	public void doCollision() {
		
//		System.out.println("Player colliding...");
		collided = true;
		
		setVelX(0);
	}
}
