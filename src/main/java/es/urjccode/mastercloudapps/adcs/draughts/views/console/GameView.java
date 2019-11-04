package es.urjccode.mastercloudapps.adcs.draughts.views.console;

import es.urjccode.mastercloudapps.adcs.draughts.controllers.PlayController;

public class GameView {

    private final PlayController playController;

    public GameView(PlayController playController) {
        this.playController = playController;
    }

    public void interact() {
        new BoardView(playController).writeBoard();
        do {
            new PlayView(playController).interact();
            new BoardView(playController).writeBoard();
        } while (playController.isFinished());
    }
}