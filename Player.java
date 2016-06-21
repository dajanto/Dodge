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
		
		// Limit player movement concerning the static obstacles
		// x
		if(_x <= 120) {
			_x = 125;
		} else if(_x >= Game._width - Game._width/8 - 32) {
			_x = Game._width - Game._width/8 - 32;
		}
		// y
		if(_y <= 0) {
			_y = 0;
		} else if(_y >= Game._height - 60) {
			_y = Game._height - 60;
		}
		
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.MAGENTA);
		g.fillRect(_x, _y, 32, 32);
	}

	@Override
	public void doCollision() {
		// TODO
	}
}
