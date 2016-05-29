import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
	
	private Handler _handler;
	
	public KeyInput(Handler handler) {
		this._handler = handler;
	}
	
	public void keyPressed(KeyEvent e) {
		
		int key = e.getKeyCode();
		
		for(int i = 0; i < _handler.object.size(); i++) {
			GameObject tempObject = _handler.object.get(i);
			
			if(tempObject.getID() == ID.Player) {
				
				if(key == KeyEvent.VK_UP) tempObject.setVelY(-6);
				if(key == KeyEvent.VK_DOWN) tempObject.setVelY(+6);
				if(key == KeyEvent.VK_LEFT) tempObject.setVelX(-6);
				if(key == KeyEvent.VK_RIGHT) tempObject.setVelX(+6);
				
			}
		}
	}
	
	public void keyReleased(KeyEvent e) {
		
		int key = e.getKeyCode();
		
		for(int i = 0; i < _handler.object.size(); i++) {
			GameObject tempObject = _handler.object.get(i);
			
			if(tempObject.getID() == ID.Player) {
				
				if(key == KeyEvent.VK_UP) tempObject.setVelY(0);
				if(key == KeyEvent.VK_DOWN) tempObject.setVelY(0);
				if(key == KeyEvent.VK_LEFT) tempObject.setVelX(0);
				if(key == KeyEvent.VK_RIGHT) tempObject.setVelX(0);
				
			}
		}
	}
}
