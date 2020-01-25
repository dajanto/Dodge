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
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	private static final int width = 1280;
	private static final int height = 800;

	private Thread thread;
	private boolean running = false;
	private Window window;

	private Handler handler;

	private Countdown countdown;
	private int life;
	private int ticks;

	public Game() {

		window = new Window(width, height, "Dodge!", this);
		handler = new Handler();
		this.addKeyListener(new KeyInput(handler));
		countdown = new Countdown(0, 0, 125);
		countdown.start();

		spawnPlayer();

		// Player life total
		life = 50;

		// Tick-Counter
		ticks = 0;
	}

	private void showScore(int life, int score) {
		
		// HTML for two line JLabel
		window.getScoringLabel().setText("<html>Life: " + life + "<br>Score: " + score + "</html>");
	}
	
	private int createScore(int increase) {
		
		int score = 0;
		return score = score + increase;
	}

	private void spawnMovingObstacles() {

		// TODO In current state game is not beatable at all times
		int randY = randomNumber(200);
		int spawningObstacleHeight = 100;
		int height = this.getHeight() + randY - spawningObstacleHeight;

		handler.addObject(new MovingObstacle(this.getWidth() + 200, randY, 100, height, ID.MovingObstacleType1));
	}

	private int randomNumber(int range) {

		Random rand = new Random();

		int random = rand.nextInt(range);
		
		if (random == 0) {
			random++;
		}
		return random;
	}
	
	private void spawnPlayer() {

		handler.addObject(new Player(500, 600, 100, 100, ID.Player1));
	}

	private void update() {

		int score = 0;
		int first = 0;
		boolean playerCollided;

        JLabel scoringLabel = window.getScoringLabel();

		// Counting ticks
		ticks++;

		// Spawning regulation
		// Spawn obstacle per 70 ticks
		if(ticks >= 100) {
			ticks = 0;
			spawnMovingObstacles();
		}

		if (life > 0) {

			playerCollided = handler.getPlayer(first).hasCollided();

			if (playerCollided) {
				life--;
			}

			// Scoring
			score = createScore(countdown.getSeconds());
			showScore(life, score);

			// Loss
            if (life <= 0) {

				scoringLabel.setSize(width, height);
                scoringLabel.setFont(new Font("Lucida Console", Font.BOLD, 80));
                scoringLabel.setText("<html>You scored: " + score + "<br>Try better next time!</html>");

                // TODO Score-Window closen und MainMenu neu öffnen
				// Google: Thread.interrupt?
                thread.interrupt();
				new MainMenu();
				// Altes Game-Window closen
				// Klassendiagramm aufstellen
				//window.exit()
				}
            }
		handler.update();
        handler.collisionDetection();;
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
