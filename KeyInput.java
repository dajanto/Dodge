import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

	private Handler handler;

	public KeyInput(Handler handler) {
		
		this.handler = handler;
	}

	public void keyPressed(KeyEvent e) {
		
		int key = e.getKeyCode();

		// There is only one player at the moment so a loop is overkill
		GameObject player = handler.playerObjects.get(0);
		
		// Player Jump
		if (key == KeyEvent.VK_SPACE) {
			player.jump();
		}
	}
}
