package es.urjccode.mastercloudapps.adcs.draughts.controllers;

import es.urjccode.mastercloudapps.adcs.draughts.models.Game;

import es.urjccode.mastercloudapps.adcs.draughts.models.Color;
import es.urjccode.mastercloudapps.adcs.draughts.models.Coordinate;
import es.urjccode.mastercloudapps.adcs.draughts.models.Piece;
import es.urjccode.mastercloudapps.adcs.draughts.models.State;
import es.urjccode.mastercloudapps.adcs.draughts.models.Error;

public class PlayController extends AcceptorController{

    public PlayController(Game game, State state) {
		super(game, state);
	}

	public Error move(Coordinate origin, Coordinate target){
        return game.move(origin, target);
    }

	public Piece getPiece(Coordinate coordinate) {
		return game.getPiece(coordinate);
	}

	public Color getColor() {
		return game.getTurnColor();
	}

	public boolean isFinished() {
		return game.isFinished();
	}

	public void finish(){
		this.state.next();
	}

	@Override
	public void accept(ControllerVisitor controllerVisitor) {
		controllerVisitor.visit(this);
	}
}