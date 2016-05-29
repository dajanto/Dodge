import java.awt.Color;
import java.awt.Graphics;

public class MovingObstacleType2 extends GameObject {

	public MovingObstacleType2(int x, int y, ID id) {
		super(x, y, id);
		
	}

	@Override
	public void update() {
		
		// Creating velocity
		_x += _velX;
		_y += _velY;
		
		// TODO
	}

	@Override
	public void render(Graphics g
			) {
		g.setColor(Color.BLACK);
		g.fillRect(_x, _y, 50, 50);
	}
}
