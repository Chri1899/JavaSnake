package com.chri1899;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {

	private static Keyboard INSTANCE = null;

	private static boolean[] keyPressed = new boolean[128];

	private Keyboard() {

	}

	public static Keyboard getInstance() {
		if (Keyboard.INSTANCE == null) {
			Keyboard.INSTANCE = new Keyboard();
		}

		return Keyboard.INSTANCE;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		keyPressed[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keyPressed[e.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	public static boolean isKeyPressed(int keyCode) {
		return keyPressed[keyCode];
	}
}
