import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class MovingObstacle extends GameObject {

	public MovingObstacle(int x, int y, int width, int height, ID id) {
		
		super(x, y, width, height, id);
	}

	@Override
	public void update() {

		move();

		// Get faster with time  
//		Math.log(100)
//		setVelX(-2);
		setVelX(-5);
	}

	@Override
	public void render(Graphics g, Graphics2D g2d, BufferedImage bi) {
		
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