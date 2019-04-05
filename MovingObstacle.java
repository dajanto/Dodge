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

		// TODO Hindernisse ausschneiden

		TexturePaint tp = new TexturePaint(bi, new Rectangle(this.getX(), this.getY(),this.getWidth(),this.getHeight()));
		g2d.setPaint(tp);
//		g2d.fillRect(this.getX(), this.getY(), getWidth(), getHeight());
		g2d.fillRect(this.getX() + 32, this.getY(), 35, this.getHeight());
	}
	
	@Override
	public void doCollision() {

		collided = true;
	}

}