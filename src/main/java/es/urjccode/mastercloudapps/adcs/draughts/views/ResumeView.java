package es.urjccode.mastercloudapps.adcs.draughts.views;

import es.urjccode.mastercloudapps.adcs.draughts.controllers.ResumeController;
import es.urjccode.mastercloudapps.adcs.draughts.utils.YesNoDialog;

public class ResumeView extends ConsoleView {
    
    private YesNoDialog yesNoDialog;

    public ResumeView(){
        super();
        this.yesNoDialog = new YesNoDialog();
    }

    public void interact(ResumeController resumeController) {
        if (this.yesNoDialog.read(MessageView.RESUME.getMessage())){
            resumeController.reset();
        } else {
            resumeController.next();
        }

    }
}
