package com.chri1899.scenes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import com.chri1899.Keyboard;
import com.chri1899.Snake;
import com.chri1899.scenes.SceneManager.Action;

public class GameScene extends Scene {

	private SceneManager manager;
	private final int tileSize = 20;

	private Snake snake;

	private boolean paused = false;
	private boolean started = false;

	public GameScene(SceneManager manager) {
		super(manager.getWidth(), manager.getHeight());
		this.manager = manager;

		snake = new Snake(manager.getWidth()/tileSize, manager.getHeight()/tileSize);
	}

	private void start() {
		started = true;
	}

	// TODO Food
	@Override
	void update() {
		if (started) {
			if (!paused) {
				if (snake.isDead()) {
					manager.processAction(Action.DIED);
					return;
				}

				snake.update();
			}
		}
	}

	@Override
	void render(Graphics g) {
		setBackground(Color.BLACK);

		// Render Snake
		snake.render(g, tileSize);

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
	
