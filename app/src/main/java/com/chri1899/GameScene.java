package com.chri1899;

import java.awt.Color;
import java.awt.Graphics;

public class GameScene extends Scene {

	private final int width, height;
	private final int tileSize = 20;

	private Snake snake;

	private boolean isRunning = false;

	public GameScene(int width, int height) {
		super(width, height);
		this.width = width;
		this.height = height;

		start();
	}

	public void start() {
		snake = new Snake(width/tileSize, height/tileSize);

		isRunning = true;
	}

	@Override
	void update() {
		if (isRunning) {
			snake.update();
		}
	}

	@Override
	void render(Graphics g) {
		setBackground(Color.BLACK);

		if (isRunning) {
			snake.render(g, tileSize);
		}
	}
}
	
