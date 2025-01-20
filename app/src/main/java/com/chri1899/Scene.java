package com.chri1899;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public abstract class Scene extends JPanel {
	public Scene(int width, int height) {
		super();
		setPreferredSize(new Dimension(width, height));
	}

	abstract void update();
	abstract void render(Graphics g);

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		render(g);
	}
}
