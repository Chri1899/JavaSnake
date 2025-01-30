package com.chri1899;

import java.awt.Dimension;

import javax.swing.JFrame;

import com.chri1899.scenes.SceneManager;

public class SnakeGame extends JFrame {

	private final int WIDTH = 1080;
	private final int HEIGHT = 720;
	private Dimension dim = new Dimension(WIDTH, HEIGHT);
	private final String TITLE = "SnakeGame";

	private final Keyboard keyboard;

	private boolean running = false;

	private SceneManager sceneManager;

	public SnakeGame() {
		setSize(WIDTH, HEIGHT);
		setTitle(TITLE);
		setResizable(false);

		keyboard = Keyboard.getInstance();
		addKeyListener(keyboard);

		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void start() {
		sceneManager = new SceneManager(this);
		add(sceneManager.getCurrentScene());
		pack();
		setVisible(true);
		running = true;

		loop();
	}

	public void loop() {
		final int FPS = 60;
		final int UPDATE_RATE = 10;
		final long FRAME_TIME = 1000 / FPS;
		final long UPDATE_TIME = 1000 / UPDATE_RATE;

		Thread gameThread = new Thread(() -> {
			long lastUpdateTime = System.currentTimeMillis();
			long lastFrameTime = System.currentTimeMillis();

			while (running) {
				long currentTime = System.currentTimeMillis();

				if (sceneManager.hasSceneChanged()) {
					getContentPane().removeAll();
					add(sceneManager.getCurrentScene());

					revalidate();
					repaint();
				}

				// Update game logic at a fixed rate
				if (currentTime - lastUpdateTime >= UPDATE_TIME) {
					sceneManager.getCurrentScene().updateScene();
					lastUpdateTime = currentTime;
				}

				if (sceneManager.shouldStop()) {
					running = false;
					break;
				}

				// render as often as possible
				if (currentTime - lastFrameTime >= FRAME_TIME) {
					sceneManager.getCurrentScene().repaint();
					lastFrameTime = currentTime;
				}

				pack();

				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			stop();
		});

		gameThread.start();
	}

	private void stop() {
		System.exit(0);
	}

	public Dimension getDimension() {
		return dim;
	}
}
