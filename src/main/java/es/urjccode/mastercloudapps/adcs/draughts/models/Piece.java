package es.urjccode.mastercloudapps.adcs.draughts.models;

public abstract class Piece {

	private Color color;

	Piece(Color color) {
		assert color != null;
		this.color = color;
	}

	boolean isLimit(Coordinate coordinate){
		return coordinate.getRow()== 0 && this.getColor() == Color.WHITE ||
		coordinate.getRow()== 7 && this.getColor() == Color.BLACK;
	}

	boolean isAdvanced(Coordinate origin, Coordinate target) {
		assert origin != null;
		assert target != null;
		int difference = origin.getRow() - target.getRow();
		if (color == Color.WHITE) {
			return difference > 0;
		}
		return difference < 0;
	}

	Color getColor() {
		return this.color;
	}

	abstract Error isCorrect(Coordinate origin, Coordinate target, PieceProvider pieceProvider);

	abstract boolean canMove(Coordinate position, PieceProvider pieceProvider);
}