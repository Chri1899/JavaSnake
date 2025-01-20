package com.chri1899;

import javax.swing.JFrame;

public class SnakeGame extends JFrame {

	private final int WIDTH = 1080;
	private final int HEIGHT = 720;
	private final String TITLE = "SnakeGame";

	private boolean running = false;

	private Scene currentScene;

	public SnakeGame() {
		setSize(WIDTH, HEIGHT);
		setTitle(TITLE);

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
		while (running) {
			currentScene.update();
			currentScene.repaint();
			pack();
			try {
				Thread.sleep(16);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		stop();
	}

	private void stop() {
		System.exit(0);
	}
}
