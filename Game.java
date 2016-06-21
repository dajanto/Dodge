import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable {
	
	private static final long serialVersionUID = 1L;
	
	public static final int _width = 1000;
	public static final int _height = 1000;
	
	private Thread _thread;
	private boolean _running = false;
	
	private Handler _handler;
	
	public Game() {
	
		new Window(_width, _height, "dajanto JNR", this);
		
		_handler = new Handler();
		this.addKeyListener(new KeyInput(_handler));

		// Add objects (Player, obstacles)
		spawnStaticObstacles();
		spawnPlayer();
		spawnMovingObstacles1();
	}
	
	public void spawnStaticObstacles() {
		_handler.addObject(new StaticObstacle(0, 0, ID.StaticObstacleType1));
		_handler.addObject(new StaticObstacle(Game._width - Game._width/8, 0, ID.StaticObstacleType1));
		
	}
	
	public void spawnMovingObstacles1() {
		
		for(int i = 0; i < 750; i = i + 50) {
			_handler.addObject(new MovingObstacleType1(Game._width/8, -i - 100, ID.MovingObstacleType1));
		}
	}
	
	public void spawnPlayer() {
		
		_handler.addObject(new Player(450, 800, ID.Player1));
		
	}
	
//	public static void main(String[] args) {
//		new Game();
//	}

	@Override
	public void run() {
		
		long lastTime = System.nanoTime();
		double amoutOfTicks = 60.0;
		double ns = 1000000000 / amoutOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		
		while(_running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			
			while(delta >= 1) {
				update();
				delta--;
			}
		
			if(_running)
				render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
	}

	private void update() {
		_handler.update();
	}
	
	private void render() {
		BufferStrategy bs = getBufferStrategy();
		if(bs == null) { 
			createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, _width, _height);
		
		_handler.render(g);
		_handler.collisionDetection();
		
		g.dispose();
		bs.show();
	}

	public synchronized void start() {
		
		_running = true;
		
		_thread = new Thread(this);
		_thread.start();
	}
	
	public synchronized void stop() {
		
		_running = false;
		
		try {
			_thread.join();
			
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
	}
}
