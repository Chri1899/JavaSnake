package com.chri1899;

import java.util.Random;

public class Food {
	private static int x;
	private static int y;

	private Food() {

	}

	public static Food spawnFood(int boundX, int boundY) {
		Random rand = new Random();
		x = rand.nextInt(boundX);
		y = rand.nextInt(boundY);

		return new Food();
	}
}
