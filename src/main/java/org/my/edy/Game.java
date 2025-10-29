package org.my.edy;

public class Game implements FieldListener {
    GameView gameView;

    public Game() {
        this.gameView = new ConsoleGame(this);
    }

    public void start() {
        gameView.start();
    }

    @Override
    public void onFieldChange(Field field) {
        if (field.isWin()) {
            System.out.println(gameView.getSymbolicTime() + " WIN!");
            gameView.stop();
            return;
        }
        if (field.isFill()) {
            System.out.println("DRAW!");
            gameView.stop();
        }
    }

    public GameView getGameView() {
        return gameView;
    }
}
