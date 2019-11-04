package es.urjccode.mastercloudapps.adcs.draughts.controllers;

import es.urjccode.mastercloudapps.adcs.draughts.models.Game;
import es.urjccode.mastercloudapps.adcs.draughts.models.State;

public abstract class Controller{

    protected Game game;
    
    protected State state;

    public Controller(Game game, State state) {
        this.game = game;
        this.state = state;
    }

    public abstract void accept(ControllerVisitor controllerVisitor);
}