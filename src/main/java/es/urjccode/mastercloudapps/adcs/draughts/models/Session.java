package es.urjccode.mastercloudapps.adcs.draughts.models;

public class Session {

	private State state;

	private Game game;

	public Session(){
		this.state = new State();
		this.game = new Game();
	}
	public StateValue getValueState() {
		return this.state.getValueState();
	}

	public void next() {
		this.state.next();
	}

	public void reset() {
		this.state.reset();
	}

	public void move(Coordinate origin, Coordinate target) {
		this.game.move(origin, target);
	}

	public Piece getPiece(Coordinate coordinate) {
		return this.game.getPiece(coordinate);
	}

	public Color getColor() {
		return this.game.getColor();
	}

	public Color getColor(Coordinate coordinate) {
		return this.game.getColor(coordinate);
	}

	public boolean isBlocked() {
		return this.game.isBlocked();
	}

	public int getDimension() {
		return this.game.getDimension();
	}

	public Error getErrorFromMovement(Coordinate origin, Coordinate target){
		return this.game.getErrorInMovement(origin, target);
	}
	

}
