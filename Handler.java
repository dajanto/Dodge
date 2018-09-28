import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Handler {
	
	// For Update and Render 
	List<GameObject> object = new CopyOnWriteArrayList<GameObject>();
	
	// Only for collision detection
	List<GameObject> obstacleObjects = new CopyOnWriteArrayList<GameObject>();
	List<GameObject> playerObjects = new CopyOnWriteArrayList<GameObject>();

	public void update() {

		for (GameObject tempObject : object) {  
			
			// Remove if object is out of sight
			if(tempObject.getX() < -800) {
				
				object.remove(tempObject);
				
				// TODO Win condition counting objects
				// objects passed by player
			} 
			
			tempObject.update();
			
//			tempObject.update();
//			
//			// Remove old objects 
//			if(tempObject.getX() < -800) {
//				System.out.println(object.size());
//				object.remove(tempObject);
//				System.out.println(object.size());
//			} 
		}
	}

	public GameObject getPlayer(int index) {
		
		return playerObjects.get(index);
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
					
				} else {
					
					// Swap collided boolean again
					obstacleObject.collided = false;
					playerObject.collided = false;
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
