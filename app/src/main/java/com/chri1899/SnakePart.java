package com.chri1899;

public class SnakePart {
	enum PartType {
		HEAD, BODY
	}

	private final PartType type;
	private int x;
	private int y;

	private int lastX;
	private int lastY;

	private Direction lastDir;

	public SnakePart(PartType type, int x, int y) {
		this.type = type;
		this.x = x;
		this.y = y;
	}

	public void move(Direction dir) {
		lastX = x;
		lastY = y;

		switch (dir) {
			case UP:
				if (lastDir != Direction.DOWN) {
					y--;
				}
				break;
			case DOWN:
				if (lastDir != Direction.UP) {
					y++;
				}
				break;
			case RIGHT:
				if (lastDir != Direction.LEFT) {
					x++;
				}
				break;
			case LEFT:
				if (lastDir != Direction.RIGHT) {
					x--;
				}
				break;
			default:
				return;
		}
	}

	public void move(int xCoord, int yCoord) {
		lastX = x;
		lastY = y;

		x = xCoord;
		y = yCoord;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getLastX() {
		return lastX;
	}

	public int getLastY() {
		return lastY;
	}

	public PartType getType() {
		return type;
	}
}
