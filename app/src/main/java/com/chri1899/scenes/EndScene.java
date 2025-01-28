package com.chri1899.scenes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import com.chri1899.Keyboard;
import com.chri1899.scenes.SceneManager.Action;

public class EndScene extends Scene {

	private SceneManager manager;

	public EndScene(SceneManager manager) {
		super(manager.getWidth(), manager.getHeight());
		this.manager = manager;
	}

	@Override
	void update() {
		
	}

	@Override
	void render(Graphics g) {
		setBackground(Color.RED);
	}

	@Override
	void processInput() {
		if (Keyboard.isKeyPressed(KeyEvent.VK_ESCAPE)) {
			manager.processAction(Action.STOP);
		} else if (Keyboard.isKeyPressed(KeyEvent.VK_M)) {
			manager.processAction(Action.MENU);
		} else if (Keyboard.isKeyPressed(KeyEvent.VK_SPACE)) {
			manager.processAction(Action.START);
		}
	}

}
