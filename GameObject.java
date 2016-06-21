import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public abstract class GameObject {
	
	protected int _x;
	protected int _y;
	protected ID _id;
	protected int _velX;
	protected int _velY;
	
	protected Color color;
	protected boolean collided;
	
	public GameObject(int x, int y, ID id) {
		this._x = x;
		this._y = y;
		this._id = id;
		
		collided = false;
	}

	public abstract void update();
	
	public abstract void render(Graphics g);
	
	public abstract void doCollision();
	
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
}
