package es.urjccode.mastercloudapps.adcs.draughts.views;

import es.urjccode.mastercloudapps.adcs.draughts.controllers.StartController;

public class StartView extends ConsoleView {


    public StartView(){
        super();
    }

    public void interact(StartController startController) {
        this.console.writeln(MessageView.TITLE.getMessage());
        new GameView().write(startController);
        startController.start();
    }
}
