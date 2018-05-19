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
			
			if(tempObject.getID() == ID.Player1) {
				
				// Arrow key inputs
//				if(key == KeyEvent.VK_UP) tempObject.setVelY(-8);
//				if(key == KeyEvent.VK_DOWN) tempObject.setVelY(+8);
//				if(key == KeyEvent.VK_LEFT) tempObject.setVelX(-8);
//				if(key == KeyEvent.VK_RIGHT) tempObject.setVelX(+8);
				
				// Jumping
				if(key == KeyEvent.VK_SPACE) {
					tempObject.jump();
				}
				
//				if(key == KeyEvent.VK_W) {
//					tempObject.setVelY(-30);
//				}
				
//				if(key == KeyEvent.VK_S) {
//					tempObject.setVelY(+30);
//				}
				
				if(key == KeyEvent.VK_A) {
					
					if(!tempObject.collided) {
						tempObject.setVelX(-20);
					}
				}
				
				if(key == KeyEvent.VK_D) {

					if(!tempObject.collided) {
						tempObject.setVelX(+20);
					}
				}
			}
		}
	}
	
	public void keyReleased(KeyEvent e) {
		
		int key = e.getKeyCode();
		
		for(int i = 0; i < _handler.object.size(); i++) {
			GameObject tempObject = _handler.object.get(i);
			
			if(tempObject.getID() == ID.Player1) {
				
//				// Jumping
//				if(key == KeyEvent.VK_SPACE) {
//					tempObject.fall();
//				}
				
//				if(key == KeyEvent.VK_W) {
//					tempObject.setVelY(0);
//				}
				
//				if(key == KeyEvent.VK_S) {
//					tempObject.setVelY(0);
//				}
				
				if(key == KeyEvent.VK_A) {
					tempObject.setVelX(0);
				}
				
				if(key == KeyEvent.VK_D) {
					tempObject.setVelX(0);
				}
			}
		}
	}
}
