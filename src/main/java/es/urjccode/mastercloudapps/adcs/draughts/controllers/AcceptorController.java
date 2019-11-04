package es.urjccode.mastercloudapps.adcs.draughts.controllers;

import es.urjccode.mastercloudapps.adcs.draughts.models.Game;
import es.urjccode.mastercloudapps.adcs.draughts.models.State;

public abstract class AcceptorController extends Controller {

    AcceptorController(Game game, State state) {
        super(game, state);
    }

	public String getBoard() {
		return this.game.getBoard();
	}

}