import java.awt.Color;
import java.awt.Graphics;

public class MovingObstacleType1 extends GameObject {

	public MovingObstacleType1(int x, int y, int width, int height, ID id) {
		super(x, y, width, height, id);
		
		_velX = randomNumber(10);
		_velY = randomNumber(5);
	}

	@Override
	public void update() {
		
		// Creating velocity
		_x += _velX;
		_y += _velY;
		
		// Bouncing between walls
		if(_x >= Game._width - Game._width/8 - 100 || _x <= 120) {
			_velX *= -1;
		}
		
		// If object is out of sight 
		if(_y >= 1100) {
			
			_y = -100;
			
			_velX = randomNumber(10);
			_velY = randomNumber(5);
			
			color = Color.BLACK;
			collided = false;
		}
	}

	@Override
	public void render(Graphics g) {
		
		g.setColor(Color.BLACK);
		g.fillRect(_x, _y, 100, 100);
		
		if(collided) {
			color = Color.MAGENTA;
			g.setColor(color);
			g.fillRect(_x, _y, 100, 100);
		}
	}

	@Override
	public void doCollision() {

		collided = true;
		
	}
}
