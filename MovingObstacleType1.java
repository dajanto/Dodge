import java.awt.Color;
import java.awt.Graphics;

public class MovingObstacleType1 extends GameObject {

	public MovingObstacleType1(int x, int y, ID id) {
		super(x, y, id);
		
		_velX = 32;
		_velY = 8;
	}

	@Override
	public void update() {
		
		// Creating velocity
		_x += _velX;
		_y += _velY;
		
		// Make obstacle bounce between walls
		if(_x >= Game._width - Game._width/8 - 100 || _x <= 120) {
			_velX *= -1;
		}
		
		// If out of bounds set y higher
		if(_y >= 1100) {
			
			_y = _y - 1500;
			
			_velX = randomNumber(10);
			_velY = randomNumber(5);
		}
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(_x, _y, 100, 100);
	}
}
