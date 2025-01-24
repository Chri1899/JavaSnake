package com.chri1899;

public class SnakePart {
	enum PartType {
		HEAD, BODY
	}

	private final PartType type;
	private int x;
	private int y;

	private Direction lastDir;

	public SnakePart(PartType type, int x, int y) {
		this.type = type;
		this.x = x;
		this.y = y;
	}

	public void move(Direction dir) {
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

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public PartType getType() {
		return type;
	}
}
