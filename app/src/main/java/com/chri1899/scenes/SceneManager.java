package com.chri1899.scenes;

import com.chri1899.SnakeGame;

public class SceneManager {

	protected enum Action {
		START, MENU, STOP, DIED
	}

	private boolean stop = false;
	private boolean changedScene = false;

	private SnakeGame game;

	private Scene currentScene;
	private Scene oldScene;

	public SceneManager(SnakeGame game) {
		this.game = game;

		currentScene = new MenuScene(this);
	}

	protected void processAction(Action action) {
		oldScene = currentScene;

		switch (action) {
			case START:
				currentScene = new GameScene(this);
				break;
			case MENU:
				currentScene = new MenuScene(this);
				break;
			case DIED:
				currentScene = new EndScene(this);
				break;
			case STOP:
				currentScene = null;
				stop = true;
			default:
				break;
		}

		changedScene = true;
	}

	protected int getWidth() {
		return game.getDimension().width;
	}

	protected int getHeight() {
		return game.getDimension().height;
	}

	public boolean shouldStop() {
		return stop;
	}

	public Scene getCurrentScene() {
		return currentScene;
	}

	public Scene getOldScene() {
		return oldScene;
	}

	public boolean hasSceneChanged() {
		return changedScene;
	}
}
