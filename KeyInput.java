import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

	private Handler handler;

	public KeyInput(Handler handler) {
		this.handler = handler;
	}

	public void keyPressed(KeyEvent e) {

		int key = e.getKeyCode();

		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			if (tempObject.getID() == ID.Player1) {

				// Jumping
				if (key == KeyEvent.VK_SPACE) {
					tempObject.jump();
				}

				// if(key == KeyEvent.VK_W) {
				// tempObject.setVelY(-30);
				// }

				// if(key == KeyEvent.VK_S) {
				// tempObject.setVelY(+30);
				// }

				if (key == KeyEvent.VK_A) {

					// if(!tempObject.collided) {
					tempObject.setVelX(-10);
					// }
				}

				if (key == KeyEvent.VK_D) {

					// if(!tempObject.collided) {
					tempObject.setVelX(+10);
					// }
				}
			}
		}
	}
}
