package es.urjccode.mastercloudapps.adcs.draughts.views;

import es.urjccode.mastercloudapps.adcs.draughts.models.Error;

class ErrorView extends ConsoleView{

    public ErrorView(){
       super();
    }

    public String getErrorMessage(Error error){
        return "Error!!!" + error.getMessage();
    }
}