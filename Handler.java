import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

public class Handler {

	LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	LinkedList<GameObject> obstacleObjects = new LinkedList<GameObject>();
	LinkedList<GameObject> playerObjects = new LinkedList<GameObject>();
	
	int collisionCounter;
	
	public void update() {
		for (GameObject tempObject : object) {
			tempObject.update();
		}
	}
	
	public void render(Graphics g) {
		for (GameObject tempObject : object) {
			tempObject.render(g);
		}
	}
	
	public void collisionDetection() {
		
		for (GameObject obstacleObject : obstacleObjects) {
			
			GameObject playerObject = playerObjects.get(0);
			
			Rectangle player = new Rectangle(playerObject.getX(),playerObject.getY(),32,32);
			Rectangle obstacle = new Rectangle(obstacleObject.getX(),obstacleObject.getY(),100,100);
			
			if(player.intersects(obstacle)) {
				obstacleObject.doCollision();
				playerObject.doCollision();
				
			}
		}
	}
	
	public void addObject(GameObject object) {
		
		this.object.add(object);
		
		if(object.getID().toString().contains("obstacle") || object.getID().toString().contains("Obstacle")) {
			obstacleObjects.add(object);
		}
		
		if(object.getID().toString().contains("Player") || object.getID().toString().contains("player")) {
			playerObjects.add(object);
		}
	}
	
	
	public void removeObject(GameObject object) {
		this.object.remove(object);
	}
}
