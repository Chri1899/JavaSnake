package com.chri1899;

import java.awt.event.KeyEvent;

import javax.swing.JFrame;

public class SnakeGame extends JFrame {

	private final int WIDTH = 1080;
	private final int HEIGHT = 720;
	private final String TITLE = "SnakeGame";

	private final Keyboard keyboard;

	private boolean running = false;

	private Scene currentScene;

	public SnakeGame() {
		setSize(WIDTH, HEIGHT);
		setTitle(TITLE);

		keyboard = Keyboard.getInstance();
		addKeyListener(keyboard);

		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void start() {
		currentScene = new GameScene(WIDTH, HEIGHT);
		add(currentScene);
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

				if (Keyboard.isKeyPressed(KeyEvent.VK_ESCAPE) && !(currentScene instanceof GameScene)) {
					currentScene = new GameScene(WIDTH, HEIGHT);

					GameScene scene = (GameScene) currentScene;
					scene.start();
				}

				// Update game logic at a fixed rate
				if (currentTime - lastUpdateTime >= UPDATE_TIME) {
					currentScene.update();
					lastUpdateTime = currentTime;
				}

				// render as often as possible
				if (currentTime - lastFrameTime >= FRAME_TIME) {
					currentScene.repaint();
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
}
