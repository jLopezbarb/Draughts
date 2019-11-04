package es.urjccode.mastercloudapps.adcs.draughts.views.console;

import es.urjccode.mastercloudapps.adcs.draughts.controllers.AcceptorController;
import es.urjccode.mastercloudapps.adcs.draughts.utils.Console;

public class BoardView {

    private Console console;
    private final AcceptorController acceptorController;

    public BoardView(AcceptorController acceptorController) {
        this.acceptorController = acceptorController;
        this.console = new Console();
    }

    public void writeBoard() {
        this.console.writeln(this.acceptorController.getBoard());
    }
}
