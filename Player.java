import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;

public class Player extends GameObject {

	public Player(int x, int y, int width, int height, ID id) {
		
		super(x, y, width, height, id);
		
	}

	@Override
	public void update() {

		// Movement
		move();

		// Falling
		fall();

		// Limit player movement concerning the static obstacles
		// x
		if (x >= 1230) {
			x = 1230;
		} else {
			if (x <= 0) {
				x = 0;
			}
		}

		// y
		if (y <= 0) {
			y = 0;
		} else {
			// 651 instead of 650 because 650 is drawn and the 1 is for collision detection
			// TODO Should the player to be allowed to touch the ground?
			if (y >= 650) {
				y = 650;
			}
		}
	}

	@Override
	public void render(Graphics2D g2d, BufferedImage bi) {

		// TODO Player ausschneiden
		TexturePaint tp = new TexturePaint(bi, new Rectangle(this.getX(), this.getY(),this.getWidth(),this.getHeight()));
		// TexturePaint tp = new TexturePaint(bi, new Rectangle(this.getX(),this.getY(), 100,48));

		g2d.setPaint(tp);
		g2d.fillRect(this.getX(), this.getY(), getWidth(), getHeight());

	}

	@Override
	public void doCollision() {

		collided = true;
	}
}
