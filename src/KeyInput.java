import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

	private Handler handler;

	public KeyInput(Handler handler) {
		
		this.handler = handler;
	}

	public void keyPressed(KeyEvent e) {
		
		int key = e.getKeyCode();
		
		GameObject player = handler.playerObjects.get(0);
		
		// Player Jump
		if (key == KeyEvent.VK_SPACE) {
			player.jump();
		}
		if (key == KeyEvent.VK_D) {

			// TODO
			player.setVelX(3);
		}

		if (key == KeyEvent.VK_A) {

			// TODO
			player.setVelX(-3);
		}
	}
}
