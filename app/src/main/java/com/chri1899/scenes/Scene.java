package com.chri1899.scenes;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public abstract class Scene extends JPanel {
	public Scene(int width, int height) {
		super();
		setPreferredSize(new Dimension(width, height));
	}

	public void updateScene() {
		processInput();
		update();
	}

	abstract void update();
	abstract void render(Graphics g);
	abstract void processInput();

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		render(g);
	}
}
