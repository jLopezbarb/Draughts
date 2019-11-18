package es.urjccode.mastercloudapps.adcs.draughts.models;

public class Game {

	private Board board;
	private Turn turn;

	public Game() {
		this.turn = new Turn();
		this.board = new Board();
		for (int i = 0; i < this.board.getDimension(); i++) {
			for (int j = 0; j < this.board.getDimension(); j++) {
				Coordinate coordinate = new Coordinate(i, j);
				Piece piece = this.getInitialPiece(coordinate);
				if (piece != null) {
					this.board.put(coordinate, piece);
				}
			}
		}
	}

	private Piece getInitialPiece(Coordinate coordinate) {
		assert coordinate != null;
		if (coordinate.isBlack()) {
			int row = coordinate.getRow();
			if (row <= 2){
				return new Pawn(Color.BLACK);
			} else if (row >= 5){
				return new Pawn(Color.WHITE);
			}
		}
		return null;
	}

	public void move(Coordinate origin, Coordinate target) {
		assert this.isCorrect(origin, target) == null;
		if (origin.diagonalDistance(target) > 1) {
			this.board.remove(origin.betweenDiagonal(target));
		}
		this.board.move(origin, target);
		Piece piece = this.board.getPiece(target);
		if (piece.isLimit(target) && piece instanceof Pawn){
			this.board.remove(target);
			this.board.put(target, new Draught(Color.WHITE));
		}
		this.turn.change();
	}

	public Error isCorrect(Coordinate origin, Coordinate target){
		assert origin != null;
		assert target != null;
		if (board.isEmpty(origin)) {
			return Error.EMPTY_ORIGIN;
		}
		if (this.turn.getColor() != this.board.getColor(origin)) {
			return Error.OPPOSITE_PIECE;
		}
		return this.board.getPiece(origin).isCorrect(origin, target, board);
	}

	public Color getColor(Coordinate coordinate) {
		assert coordinate != null;
		return this.board.getColor(coordinate);
	}

	public Color getColor() {
		return this.turn.getColor();
	}

	public boolean isBlocked() {
		return this.board.getPieces(this.turn.getColor()).isEmpty() || this.canMove(this.turn.getColor());
	}

	private boolean canMove(Color color){
		return this.board.canMove(color);
	}
	public int getDimension() {
		return this.board.getDimension();
	}

	public Piece getPiece(Coordinate coordinate) {
		assert coordinate != null;
		return this.board.getPiece(coordinate);
	}

	@Override
	public String toString() {
		return this.board + "\n" + this.turn;
	}

}