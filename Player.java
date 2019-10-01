import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
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
			if (y >= 650) {
				y = 650;
			}
		}
	}

	@Override
	public void render(Graphics2D g2d, BufferedImage bi) {

		// TODO Player richtig ausschneiden
		TexturePaint tp = new TexturePaint(bi, new Rectangle(this.getX(), this.getY(),this.getWidth(),this.getHeight()));

		// Debuggen der Playerkoordinaten
//		System.out.println("X: " + this.getX());
//		System.out.println("Y: "+ this.getY());
//		System.out.println("Width: " + this.getWidth());
//		System.out.println("Height: " + this.getHeight());
//		System.out.println("              ");
//		System.out.println("              ");
//		System.out.println("              ");
//		System.out.println("              ");
//		System.out.println("              ");
//		TexturePaint tp = new TexturePaint(bi, new Rectangle(this.getX(), this.getY(),this.getWidth(),this.getHeight()));
//		TexturePaint tp = new TexturePaint(bi, new Rectangle(this.getX(), this.getY(),48,100));

		// TODO Resize Image?	
		// 48 100 geht nicht

		g2d.setPaint(tp);
		g2d.fillRect(this.getX(), this.getY(), 32, 100);
	}

	@Override
	public void doCollision() {

		collided = true;
	}
}
