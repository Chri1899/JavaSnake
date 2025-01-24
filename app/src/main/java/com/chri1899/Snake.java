package com.chri1899;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

import com.chri1899.SnakePart.PartType;


public class Snake {

	private final int boundX, boundY;

	private ArrayList<SnakePart> body = new ArrayList<>();
	private boolean isAlive = false;

	private Direction curDir = Direction.RIGHT;

	public Snake(int boundX, int boundY) {
		this.boundX = boundX;
		this.boundY = boundY;

		body.add(spawnSnake());

		isAlive = true;
	}

	public void update() {
		// Movement
		if (Keyboard.isKeyPressed(KeyEvent.VK_W)) {
			if (curDir != Direction.DOWN) {
				curDir = Direction.UP;
			}
		} else if (Keyboard.isKeyPressed(KeyEvent.VK_S)) {
			if (curDir != Direction.UP) {
				curDir = Direction.DOWN;
			}
		} else if (Keyboard.isKeyPressed(KeyEvent.VK_A)) {
			if (curDir != Direction.RIGHT) {
				curDir = Direction.LEFT;
			}
		} else if (Keyboard.isKeyPressed(KeyEvent.VK_D)) {
			if (curDir != Direction.LEFT) {
				curDir = Direction.RIGHT;
			}
		}

		// Check collision with wall
		if (checkWallCollision()) {
			die();
			return;
		}

		// Move
		move();
	}

	private void move() {
		for (SnakePart p : body) {
			p.move(curDir);
		}
	}

	private boolean checkWallCollision() {
		SnakePart head = body.get(0);
		int headX = head.getX();
		int headY = head.getY();

		if (headX < 0 || headX > boundX || headY < 0 || headY > boundY) {
			return true;
		}

		return false;
	}

	private SnakePart spawnSnake() {
		Random rand = new Random();
		int x = rand.nextInt(boundX);
		int y = rand.nextInt(boundY);

		return new SnakePart(PartType.HEAD, x, y);
	}

	private void die() {
		isAlive = false;
	}

	public boolean getIsAlive() {
		return isAlive;
	}

	public void render(Graphics g, int tileSize) {
		for (SnakePart p : body) {
			if (p.getType() == PartType.HEAD) {
				g.setColor(Color.RED);
			} else {
				g.setColor(Color.WHITE);
			}

			g.fillRect(tileSize * p.getX(), tileSize * p.getY(), tileSize, tileSize);
		}
	}
}
