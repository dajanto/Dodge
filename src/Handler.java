import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Handler {
	
	List<GameObject> object = new CopyOnWriteArrayList<GameObject>();
	
	List<GameObject> obstacleObjects = new CopyOnWriteArrayList<GameObject>();
	List<GameObject> playerObjects = new CopyOnWriteArrayList<GameObject>();

	public void update() {

		for (GameObject tempObject : object) {

			// Remove if object is out of sight
			if(tempObject.getX() < -800) {
				
				object.remove(tempObject);

				// TODO Counter objects passed
			}
			
			tempObject.update();
		}
	}

	public GameObject getPlayer(int index) {
		
		return playerObjects.get(index);
	}
	
	public void render(Graphics2D g2d, BufferedImage bi) {

		for (GameObject tempObject : object) {
			
			tempObject.render(g2d,bi);
		}
	}

	public void collisionDetection() {

		boolean obstacleSpawned = !obstacleObjects.isEmpty();

		if(obstacleSpawned) {
		    int first = 0;
			GameObject nearestObstacle = obstacleObjects.get(first);
			GameObject player = getPlayer(first);

			Rectangle playerRec = new Rectangle(player.getX(), player.getY(), player.getWidth(), player.getHeight());
			Rectangle obstacleRec = new Rectangle(nearestObstacle.getX(), nearestObstacle.getY(), nearestObstacle.getWidth(), nearestObstacle.getHeight());

			if (playerRec.intersects(obstacleRec)) {
				nearestObstacle.doCollision();
				player.doCollision();

			} else if(playerRec.intersects(obstacleRec) || player.getX() > obstacleRec.getX()) {
				obstacleObjects.remove(first);
                } else {
                    nearestObstacle.setCollisionState(false);
                    player.setCollisionState(false);
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

	// TODO Unbenutzt
	public void removeObject(GameObject object) {

		this.object.remove(object);
	}
}
