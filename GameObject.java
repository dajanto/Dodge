import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public abstract class GameObject {
	
	protected int _x;
	protected int _y;
	
	protected int _velX;
	protected int _velY;
	
	protected int _width;
	protected int _height;
	
	protected ID _id;
	
	protected Color color;
	protected boolean collided;

	protected int jumpheight = 100;
	protected int gravity;
	
	protected boolean canFall;
	protected boolean canJump;
	
	public GameObject(int x, int y, int width, int height, ID id) {
		this._x = x;
		this._y = y;
		this._id = id;
		this._width = width;
		this._height = height;
		
		gravity = 3;
		collided = false;
		
		canJump = false;
		canFall = true;
	}

	public abstract void update();
	
	public abstract void render(Graphics g);
	
	public abstract void doCollision();
	
	public void move() {
		
		_x += _velX;
		_y += _velY;
	}
	
	public void jump() {
		
		setVelX(10);
		setVelY(-100);
		
	}
	
	public void fall() {
		
		setVelY(gravity);
	}
	
	public void setX(int x) {
		
		this._x = x;
	}
	
	public int randomNumber(int range) {
		
		Random rand = new Random();
		int random = rand.nextInt(range);
		if(random == 0) {
			random++;
		}
		return random;
	}
	
	public void setY(int y) {
		this._y = y;
	}
	
	public int getX() {
		return _x;
	}
	
	public int getY() {
		return _y;
	}
	
	public void setID(ID id) {
		this._id = id;
	}
	
	public ID getID() {
		return _id;
	}
	
	public int getVelX() {
		return _velX;
	}
	
	public int getVelY() {
		return _velY;
	}
	
	public void setVelX(int x) {
		this._velX = x;
	}
	
	public void setVelY(int y) {
		this._velY = y;
	}

	public int getWidth() {
		return _width;
	}

	public int getHeight() {
		return _height;
	}
	
	public void setWidth(int width) {
		this._width = width;
	}
	
	public void setHeight(int height) {
		this._height = height;
	}
}