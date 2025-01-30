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
		// Check collision with wall
		if (checkWallCollision()) {
			die();
			return;
		}

		// Move
		move();
		for (SnakePart p : body) {
		}

		// Check Self Collision after moving
		if (checkSelfCollision()) {
			die();
		}
	}

	public void grow() {
		body.add(new SnakePart(PartType.BODY, body.getLast().getLastX(), body.getLast().getLastY()));
	}

	public void processMovement(int keyCode) {
		if (isAlive) {
			switch (keyCode) {
				case KeyEvent.VK_W:
					if (curDir != Direction.DOWN) curDir = Direction.UP; 
					break;
				case KeyEvent.VK_S:
					if (curDir != Direction.UP) curDir = Direction.DOWN;
					break;
				case KeyEvent.VK_A:
					if (curDir != Direction.RIGHT) curDir = Direction.LEFT;
					break;
				case KeyEvent.VK_D:
					if (curDir != Direction.LEFT) curDir = Direction.RIGHT;
					break;
			}
		}
	}

	private void move() {
		SnakePart lastPart = null;

		for (SnakePart p : body) {
			if (p.getType() == PartType.HEAD) {
				p.move(curDir);
			} else {
				p.move(lastPart.getLastX(), lastPart.getLastY());
			}

			lastPart = p;
		}
	}

	public boolean doesBlock(int foodX, int foodY) {
		for (SnakePart part : body) {
			if ((part.getX() == foodX) && (part.getY() == foodY)) return true;
		}

		return false;
	}

	public boolean checkFoodCollision(int foodX, int foodY) {
		SnakePart head = body.get(0);
		int headX = head.getX();
		int headY = head.getY();

		if (headX == foodX && headY == foodY) {
			return true;
		}

		return false;
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

	private boolean checkSelfCollision() {
		SnakePart head = body.get(0);
		int headX = head.getX();
		int headY = head.getY();

		for (int i = 1; i < body.size(); i++) {
			if ((body.get(i).getX() == headX) && (body.get(i).getY() == headY)) {
				return true;
			}
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

	public boolean isDead() {
		return !isAlive;
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
