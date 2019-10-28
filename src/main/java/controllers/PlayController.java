package controllers;

import models.Logic;
import models.Coordinate;
import models.Piece;

class PlayController{

    public PlayController(Logic logic) {
	}

	public Error move(Coordinate origin, Coordinate target){
        return null;
    }

	public Piece getPiece(Coordinate origin) {
		return null;
	}

	public boolean canMove(){
		return false;
	}

	public boolean giveUp() {
		return false;
	}
}