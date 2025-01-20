package com.chri1899;

import java.awt.Color;
import java.awt.Graphics;

public class GameScene extends Scene {

	public GameScene(int width, int height) {
		super(width, height);
	}

	@Override
	void update() {
	}

	@Override
	void render(Graphics g) {
		setBackground(Color.BLUE);
	}

}
