package com.chri1899;

public class App {

	private final SnakeGame game;

	public App() {
		game = new SnakeGame();
	}

	public void start() {
		game.start();
	}

    public static void main(String[] args) {
		App app = new App();
		app.start();
    }
}
