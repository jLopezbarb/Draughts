package es.urjccode.mastercloudapps.adcs.draughts.views;

import es.urjccode.mastercloudapps.adcs.draughts.controllers.AcceptorController;
import es.urjccode.mastercloudapps.adcs.draughts.controllers.ControllerVisitor;

public abstract class View implements ControllerVisitor{

    public abstract void interact(AcceptorController acceptorController);

}