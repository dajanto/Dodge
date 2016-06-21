import java.awt.Color;
import java.awt.Graphics;

public class StaticObstacle extends GameObject {

	public StaticObstacle(int x, int y, ID id) {
		super(x, y, id);
		
	}

	@Override
	public void update() {
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(_x, _y, Game._width/8, Game._height);
	}

	@Override
	public void doCollision() {
		// TODO
	}
}
