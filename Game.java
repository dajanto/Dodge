import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	public static final int width = 1280;
	public static final int height = 800;

	private Thread thread;
	private boolean running = false;

	private Handler handler;

	public Game() {

		new Window(width, height, "Dodge!", this);
		handler = new Handler();
		this.addKeyListener(new KeyInput(handler));

		// Spawn objects (Player, obstacles)
		spawnPlayer();
		spawnStaticObstacles();
		spawnMovingObstacles();
		
		// Test
		Countdown countdown = new Countdown(25, 0, 1000l);
		countdown.start();
	}

	public void spawnStaticObstacles() {

		// Ground
		handler.addObject(new StaticObstacle(0, 700, 1280, 100, ID.StaticObstaclesType1));
	}

	public void spawnMovingObstacles() {

		// TODO

		for (int i = 800; i < 10000; i = i + 400) {

			handler.addObject(new MovingObstacle(i, 400, 100, 300, ID.MovingObstacleType1));
		}

		for (int i = 800; i < 10000; i = i + 400) {

			handler.addObject(new MovingObstacle(i, 0, 100, 300, ID.MovingObstacleType1));
		}
	}

	public void spawnPlayer() {

		handler.addObject(new Player(500, 600, 50, 50, ID.Player1));
	}

	private void update() {

		handler.update();
		handler.collisionDetection();

	}

	private void render() {

		BufferStrategy bs = getBufferStrategy();

		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();

		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width, height);

		handler.render(g);

		g.dispose();
		bs.show();
	}

	public synchronized void start() {

		running = true;

		thread = new Thread(this);
		thread.start();
	}

	public synchronized void stop() {

		running = false;

		try {
			thread.join();

		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

	@Override
	public void run() {

		this.requestFocus();

		long lastTime = System.nanoTime();
		double amoutOfTicks = 60.0;
		double ns = 1000000000 / amoutOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;

		while (running) {

			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;

			while (delta >= 1) {
				update();
				delta--;
			}

			if (running) {
				render();
				frames++;
			}

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				// 60 fps
				// System.out.println("FPS: " + frames);
				frames = 0;
			}

			try {
				Thread.sleep(1000 / 60);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		stop();
	}
}
