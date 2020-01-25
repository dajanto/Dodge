import java.awt.*;
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
	public void render(Graphics2D g2d, BufferedImage bi) {

		TexturePaint tp = new TexturePaint(bi, new Rectangle(this.getX() + 85, this.getY(), 144, 100));
		g2d.setPaint(tp);

		int h = 0;
		while (h < this.getHeight()) {
			g2d.fillRect(this.getX(), this.getY() + h, 35, 100);
			h += 100;
		}
	}
	
	@Override
	public void doCollision() {

		collided = true;
	}

}