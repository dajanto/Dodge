import java.awt.Color;
import java.awt.Graphics;

public class Player extends GameObject {

	public Player(int x, int y, ID id) {
		super(x, y, id);
		
	}

	@Override
	public void update() {
	
		// Creating velocity
		_x += _velX;
		_y += _velY;
		
		System.out.println("x: " + _x);
		System.out.println("y: " + _y);
		// Limit movement
		if(_x <= 120) {
			_x++;
		} else if(_x >= Game._width - Game._width/8 - 32) {
			_x--;
		}
		
		if(_y <= 100) {
			_y++;
		} else if(_y >= Game._height + 100) {
			_y--;
		}
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(_x, _y, 32, 32);
	}
}
