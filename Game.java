import java.awt.Canvas;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JLabel;

// import com.sun.javafx.geom.transform.GeneralTransform3D;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	private static final int width = 1280;
	private static final int height = 800;

	private Thread thread;
	private boolean running = false;
	private Window window;

	private Handler handler;

	public Game() {

		window = new Window(width, height, "Dodge!", this);
		handler = new Handler();
		this.addKeyListener(new KeyInput(handler));

		spawnPlayer();
		setUpGameConditions();
	}
	
	private void setUpGameConditions() {

		JLabel scoringLabel = window.getScoringLabel();
		Countdown countdown = new Countdown(0, 0, 125);
		countdown.start();
		
		Thread gameconditions = new Thread() {
			
			@SuppressWarnings("deprecation")
			public void run() {
				
				int life = 40;
				int score = 0;
				int first = 0;

//				Not needed for win condition
				while(life > 0) { 

					boolean playerCollided = handler.getPlayer(first).hasCollided();

					// Scoring
					score = createScore(countdown.getSeconds());

					showScore(life,score);
					
					if(playerCollided) {
						
						life--;
					}
					
					// Loss
					if (life <= 0) {

						thread.stop();
						scoringLabel.setSize(width,height);
						scoringLabel.setFont(new Font("Lucida Console", Font.BOLD, 80));
						scoringLabel.setText("<html>You scored: " + score + "<br>Try better next time!</html>");

						// TODO Space -> MainMenu
					}
				}
 		    }
		};
		// TODO Performance improvements
		gameconditions.start();
		
		Thread spawning = new Thread() {

			public void run() {

				// TODO Spawn without thread, use Thread.sleep if needed
				while(true) {

					// TODO Spawning without for loop 
					spawnMovingObstacles();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		spawning.start();
	}
	
	private void showScore(int life, int score) {
		
		// HTML for two line JLabel
		window.getScoringLabel().setText("<html>Life: " + life + "<br>Score: " + score + "</html>");
	}
	
	private int createScore(int increase) {
		
		int score = 0;
		return score = score + increase;
	}

	public void spawnMovingObstacles() {
		
		int randY = randomNumber(200);
		int spawningObstacleHeight = 100;
		int height = this.getHeight() + randY - spawningObstacleHeight;
		handler.addObject(new MovingObstacle(this.getWidth() + 200, randY, 100, height, ID.MovingObstacleType1));

//		// TODO Spawning forever and random
//		for (int x = 800; x < 10000; x = x + 400) {
//			
//			int randY = randomNumber(200);
//			int spawningObstacleHeight = 100;
//			int height = this.getHeight() + randY - spawningObstacleHeight;
//			
//			// upper
//			handler.addObject(new MovingObstacle(x, randY, 100, height, ID.MovingObstacleType1));
//			
//			// lower
////			handler.addObject(new MovingObstacle(x, 400,   , ID.MovingObstacleType1));

	}

	public int randomNumber(int range) {

		Random rand = new Random();

		int random = rand.nextInt(range);
		
		if (random == 0) {
			random++;
		}
		return random;
	}
	
	public void spawnPlayer() {

		handler.addObject(new Player(500, 600, 100, 100, ID.Player1));
	}

	private void update() {

		handler.update();
		handler.collisionDetection();

	}

	private void render() {
		
		String path = "textures.png";
		File file = new File(path);

		String pathSky = "sky.png";
		File fileSky = new File(pathSky);

		BufferedImage bi = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
		BufferedImage biSky = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
		BufferStrategy bs = getBufferStrategy();
		
		try {
			
			bi = ImageIO.read(file);
			biSky = ImageIO.read(fileSky);

		} catch (IOException e) {
			e.printStackTrace();
		}

		if (bs == null) {
			
			createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;
		
		TexturePaint tp = new TexturePaint(biSky, new Rectangle(0,0,this.getWidth(),this.getHeight()));
		g2d.setPaint(tp);
		g2d.fillRect(0, 0, this.getWidth(), this.getHeight());

		handler.render(g2d,bi);

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
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
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
