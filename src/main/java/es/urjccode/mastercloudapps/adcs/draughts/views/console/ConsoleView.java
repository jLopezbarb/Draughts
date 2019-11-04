package es.urjccode.mastercloudapps.adcs.draughts.views.console;

import es.urjccode.mastercloudapps.adcs.draughts.controllers.AcceptorController;
import es.urjccode.mastercloudapps.adcs.draughts.controllers.PlayController;
import es.urjccode.mastercloudapps.adcs.draughts.controllers.ResumeController;
import es.urjccode.mastercloudapps.adcs.draughts.controllers.StartController;
import es.urjccode.mastercloudapps.adcs.draughts.views.View;

public class ConsoleView extends View {

    @Override
    public void visit(StartController startController) {
        new StartView(startController).interact();
        startController.start();
    }

    @Override
    public void visit(PlayController playController) {
        new GameView(playController).interact();
        playController.finish();
    }

    @Override
    public void visit(ResumeController resumeController) {
        new ResumeView().interact(resumeController);
    }

    @Override
    public void interact(AcceptorController acceptorController) {
        acceptorController.accept(this);
    }

}