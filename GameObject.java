import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public abstract class GameObject {

	protected int x;
	protected int y;
	protected double velX;
	protected double velY;
	protected int width;
	protected int height;
	protected ID id;
	protected boolean collided;
	protected Color color;
	protected int jumpheight = 100;
	protected double gravity;

	public GameObject(int x, int y, int width, int height, ID id) {
		
		this.x = x;
		this.y = y;
		this.id = id;
		this.width = width;
		this.height = height;

		collided = false;
		gravity = 2.5;
	}

	public abstract void update();

	public abstract void render(Graphics g);

	public abstract void doCollision();

	public void move() {

		x += velX;
		y += velY;
	}

	public void jump() {

		// setVelX(2);
		setVelY(-40);

	}

	public void fall() {

		setVelY(gravity);
	}

	public void setX(int x) {

		this.x = x;
	}

	public int randomNumber(int range) {

		Random rand = new Random();

		int random = rand.nextInt(range);
		if (random == 0) {
			random++;
		}
		return random;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setID(ID id) {
		this.id = id;
	}

	public ID getID() {
		return id;
	}

	public double getVelX() {
		return velX;
	}

	public double getVelY() {
		return velY;
	}

	public void setVelX(double x) {
		this.velX = x;
	}

	public void setVelY(double y) {
		this.velY = y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}
}
