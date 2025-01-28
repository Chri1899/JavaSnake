package com.chri1899;

public class SceneManager {

	public enum State {
		MENU, PLAY, PAUSE, END
	}

	private SnakeGame game;

	private Scene currentScene;

	public SceneManager(SnakeGame game) {
		this.game = game;
		currentScene = new MenuScene(game.getWidth(), game.getHeight());
	}

	public Scene getCurrentScene() {
		return currentScene;
	}

	private void switchScene() {
	}
}
