import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	public static final int width = 1280;
	public static final int height = 800;

	private Thread thread;
	private boolean running = false;
	private Window window;

	private Handler handler;

	public Game() {

		window = new Window(width, height, "Dodge!", this);
		handler = new Handler();
		this.addKeyListener(new KeyInput(handler));

		// Spawn objects (Player, obstacles)
		spawnPlayer();
		spawnStaticObstacles();
		spawnMovingObstacles();
		 
		setUpGameConditions();
	}
	
	public void setUpGameConditions() {

		JLabel scoringLabel = window.getScoringLabel();
		Countdown countdown = new Countdown(0, 0, 125);
		countdown.start();
		
		Thread gameconditions = new Thread() {
			
			@SuppressWarnings("deprecation")
			public void run() {
				
				int life = 50;
				
				int score = 0;
				int first = 0;
				
//				Not needed for win condition
				while(life > 0) { 
					
					showScore(life,score);
					
					boolean playercollided = handler.getPlayer(first).hasCollided(); 
					
					// Scoring
					score = createScore(countdown.getSeconds());
					
					// Win condition?       
					if(playercollided) {
						
						life--;
					}
					
					// Loss
					if (life <= 0) {
						
						thread.stop();
						scoringLabel.setSize(width,height);
						scoringLabel.setFont(new Font("Lucida Console", Font.BOLD, 80));
						scoringLabel.setText("<html>You scored: " + score + "<br>Try better next time!</html>");
//						System.exit(0);
					}
				}
 		    }
		};
		gameconditions.start();
	}
	
	private void showScore(int life, int score) {
		
		// HTML for two line JLabel
		window.getScoringLabel().setText("<html>Life: " + life + "<br>Score: " + score + "</html>");
	}
	
	private int createScore(int increase) {
		
		int score = 0;
		return score = score + increase;
	}
	
	public void spawnStaticObstacles() {

		// Ground
		handler.addObject(new StaticObstacle(0, 700, 1280, 100, ID.StaticObstaclesType1));
	}

	public void spawnMovingObstacles() {

		// TODO Spawning in not visible space
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

		// TODO Differ textures for players and obstacles
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
		
		// Only needed for showing the FPS count
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
//				60 fps
//				System.out.println("FPS: " + frames);
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
