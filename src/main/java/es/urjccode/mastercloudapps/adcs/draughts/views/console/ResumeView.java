package es.urjccode.mastercloudapps.adcs.draughts.views.console;

import es.urjccode.mastercloudapps.adcs.draughts.controllers.ResumeController;
import es.urjccode.mastercloudapps.adcs.draughts.utils.YesNoDialog;

public class ResumeView {

    
    private YesNoDialog yesNoDialog;

    public ResumeView() {
        this.yesNoDialog = new YesNoDialog();
    }

    public void interact(ResumeController resumeController){
        boolean option = this.yesNoDialog.read(Message.RESUME);
        resumeController.resume(option);
    }
}