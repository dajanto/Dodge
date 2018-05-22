import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

public class Handler {
	
	// For Update and Render 
	LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	// Only for collision detection
	LinkedList<GameObject> obstacleObjects = new LinkedList<GameObject>();
	LinkedList<GameObject> playerObjects = new LinkedList<GameObject>();

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

			for (GameObject playerObject : playerObjects) {

				Rectangle player = new Rectangle(playerObject.getX(), playerObject.getY(), playerObject.getWidth(),
						playerObject.getHeight());
				Rectangle obstacle = new Rectangle(obstacleObject.getX(), obstacleObject.getY(),
						obstacleObject.getWidth(), obstacleObject.getHeight());

				// Actual collision
				if (player.intersects(obstacle)) {

					obstacleObject.doCollision();
					playerObject.doCollision();
				}
			}
		}
	}

	public void addObject(GameObject object) {

		this.object.add(object);

		if (object.getID().toString().contains("obstacle") || object.getID().toString().contains("Obstacle")) {
			obstacleObjects.add(object);
		}

		if (object.getID().toString().contains("Player") || object.getID().toString().contains("player")) {
			playerObjects.add(object);
		}
	}

	public void removeObject(GameObject object) {

		this.object.remove(object);
	}
}
