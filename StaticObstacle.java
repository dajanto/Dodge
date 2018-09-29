import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;

public class StaticObstacle extends GameObject {

	public StaticObstacle(int x, int y, int width, int height, ID id) {

		super(x, y, width, height, id);
	}

	@Override
	public void update() {
		
	}

	@Override
	public void render(Graphics g, Graphics2D g2d, BufferedImage bi) {
		
		TexturePaint tp = new TexturePaint(bi, new Rectangle(0, 0, this.getWidth(), this.getHeight()));
		
		g2d.setPaint(tp);
		g2d.fillRect(0, 700, this.getWidth(), this.getWidth());
		
		// TODO Texture
//		String path = "player1.png";
//		File file = new File(path);
//		BufferedImage bi = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
//		
//		try {
//			bi = ImageIO.read(file);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//		Graphics2D g2d = (Graphics2D) g;
//		
//		TexturePaint tp = new TexturePaint(bi, new Rectangle(0,0,this.getWidth(),this.getHeight()));
//		g2d.setPaint(tp);
//		g2d.fillRect(x, y, getWidth(), getHeight());

	}

	@Override
	public void doCollision() {

		collided = true;
	}
}
