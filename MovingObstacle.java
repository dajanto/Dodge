import java.awt.Color;
import java.awt.Graphics;

public class MovingObstacle extends GameObject {

	public MovingObstacle(int x, int y, int width, int height, ID id) {
		
		super(x, y, width, height, id);
	}

	@Override
	public void update() {

		move();

		setVelX(-2);
	}

	@Override
	public void render(Graphics g) {

		g.setColor(Color.BLACK);
		g.fillRect(x, y, getWidth(), getHeight());

		if (collided) {
			g.setColor(Color.RED);
			g.fillRect(x, y, getWidth(), getHeight());
		}
	}

	@Override
	public void doCollision() {

		collided = true;
	}
}