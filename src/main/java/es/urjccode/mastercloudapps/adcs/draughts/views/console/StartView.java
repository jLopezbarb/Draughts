package es.urjccode.mastercloudapps.adcs.draughts.views.console;

import es.urjccode.mastercloudapps.adcs.draughts.controllers.StartController;
import es.urjccode.mastercloudapps.adcs.draughts.utils.Console;

public class StartView {

    private Console console;
    private final StartController startController;

    public StartView(StartController startController) {
        this.startController = startController;
        this.console = new Console();
    }

    public void interact(){
        this.console.writeln(Message.GAME_TITLE);
        this.console.writeln();
    }
}