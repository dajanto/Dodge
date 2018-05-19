import java.awt.Color;
import java.awt.Graphics;

public class StaticObstacle extends GameObject {

	public StaticObstacle(int x, int y, int width, int height, ID id) {
		
		super(x, y, width, height, id);
		
	}

	@Override
	public void update() {
		
	}

	@Override
	public void render(Graphics g) {
		
		g.setColor(Color.BLACK);
		g.fillRect(_x, _y, getWidth(), getHeight());
	}

	@Override
	public void doCollision() {
		
//		System.out.println("Static Obstacle colliding...");
		
		collided = true;
		canJump = true;
		canFall = false;
	}
}
