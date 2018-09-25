import java.awt.Color;
import java.awt.Graphics;

public class MovingObstacle extends GameObject {

	Countdown countdown; 
	
	public MovingObstacle(int x, int y, int width, int height, ID id) {
		
		super(x, y, width, height, id);
		
		countdown = new Countdown(25, 1000, 1000l);
		countdown.start();
	}

	@Override
	public void update() {

		move();

		countdown.start();

		// Get faster with time  
//		Math.log(100)
//		setVelX(-2);
		setVelX(-5);
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