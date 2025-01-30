package com.chri1899.scenes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.Random;

import com.chri1899.Keyboard;
import com.chri1899.Snake;
import com.chri1899.scenes.SceneManager.Action;

public class GameScene extends Scene {

	private SceneManager manager;
	private final int tileSize = 20;

	private Snake snake;
	private int[] foodCoords = new int[2];

	private final int cols;
	private final int rows;

	private boolean paused = false;
	private boolean started = false;

	public GameScene(SceneManager manager) {
		super(manager.getWidth(), manager.getHeight());
		this.manager = manager;

		cols = manager.getWidth()/tileSize;
		rows = manager.getHeight()/tileSize;

		snake = new Snake (cols, rows);
		spawnFood();
	}

	private void start() {
		started = true;
	}

	private void spawnFood() {
		Random rand = new Random();
		int foodX;
		int foodY;
		
		do {
			foodX = rand.nextInt(cols);
			foodY = rand.nextInt(rows);	
		} while ((snake.doesBlock(foodX, foodY)));

		foodCoords[0] = foodX;
		foodCoords[1] = foodY;
	}

	@Override
	void update() {
		if (started) {
			if (!paused) {
				if (snake.isDead()) {
					manager.processAction(Action.DIED);
					return;
				}

				snake.update();

				if (snake.checkFoodCollision(foodCoords[0], foodCoords[1])) {
					snake.grow();
					spawnFood();
				}
			}
		}
	}

	@Override
	void render(Graphics g) {
		setBackground(Color.BLACK);

		/* Test Rendering
		
		g.setColor(Color.BLACK);
		for (int i = 0; i < rows; i++) {
			g.drawLine(0, i * tileSize, manager.getWidth(), i * tileSize);
		}

		for (int i = 0; i < cols; i++) {
			g.drawLine(i * tileSize, 0, i* tileSize, manager.getHeight());
		}

		*/
		

		// Render Snake
		snake.render(g, tileSize);

		// Render food
		g.setColor(Color.GREEN);
		g.fillRect(foodCoords[0] * tileSize, foodCoords[1] * tileSize, tileSize, tileSize);

		// TODO Paused Rendering
		if (paused) {

		}
	}

	@Override
	void processInput() {
		if (Keyboard.isKeyPressed(KeyEvent.VK_P)) {
			paused = !paused;
		} else if (Keyboard.isKeyPressed(KeyEvent.VK_W)) {
			snake.processMovement(KeyEvent.VK_W);
		} else if (Keyboard.isKeyPressed(KeyEvent.VK_S)) {
			snake.processMovement(KeyEvent.VK_S);
		} else if (Keyboard.isKeyPressed(KeyEvent.VK_A)) {
			snake.processMovement(KeyEvent.VK_A);
		} else if (Keyboard.isKeyPressed(KeyEvent.VK_D)) {
			snake.processMovement(KeyEvent.VK_D);
		} else if (Keyboard.isKeyPressed(KeyEvent.VK_ENTER)) {
			start();
		}
	}
}
	
