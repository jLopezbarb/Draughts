package es.urjccode.mastercloudapps.adcs.draughts.controllers;

import es.urjccode.mastercloudapps.adcs.draughts.models.Color;
import es.urjccode.mastercloudapps.adcs.draughts.models.Coordinate;
import es.urjccode.mastercloudapps.adcs.draughts.models.Piece;
import es.urjccode.mastercloudapps.adcs.draughts.models.Session;
import es.urjccode.mastercloudapps.adcs.draughts.models.Error;

public class PlayController extends Controller {

    public PlayController(Session session) {
		super(session);
	}

	public void move(Coordinate origin, Coordinate target){
		this.session.move(origin, target);
		if (this.session.isBlocked()){
			this.session.next();
		}
    }

	public Piece getPiece(Coordinate coordinate) {
		return session.getPiece(coordinate);
	}

	public Color getColor() {
		return session.getColor();
	}
	
	public boolean isBlocked() {
		return session.isBlocked();
	}	

	@Override
	public void accept(ControllersVisitor controllersVisitor) {
		controllersVisitor.visit(this);
	}

	public Error getErrorFromMovement(Coordinate origin, Coordinate target){
		return this.session.getErrorFromMovement(origin, target);
	}

}