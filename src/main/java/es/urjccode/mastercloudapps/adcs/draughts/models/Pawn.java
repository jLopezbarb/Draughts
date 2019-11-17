package es.urjccode.mastercloudapps.adcs.draughts.models;


public class Pawn extends Piece {

    private static final int MAX_DISTANCE = 2;

    Pawn(Color color) {
        super(color);
    }

    @Override
    Error isCorrect(Coordinate origin, Coordinate target, PieceProvider pieceProvider) {
		if (!origin.isDiagonal(target)) {
			return Error.NOT_DIAGONAL;
		}
		if (!pieceProvider.isEmpty(target)) {
			return Error.NOT_EMPTY_TARGET;
		}
		if (!this.isAdvanced(origin, target)) {
			return Error.NOT_ADVANCED;
		}
		int distance = origin.diagonalDistance(target);
		if (distance > Pawn.MAX_DISTANCE) {
			return Error.BAD_DISTANCE;
		}
		if (distance == Pawn.MAX_DISTANCE) {
			if (pieceProvider.getPiece(this.getPieceBetween(origin, target)) == null) {
				return Error.EATING_EMPTY;
			}
		}
		return null;
	}

	Coordinate getPieceBetween(Coordinate origin, Coordinate target){
		return origin.betweenDiagonal(target).get(0);
	}

	@Override
	public String toString(){
		final String[] letters = {"b","n"};
        return letters[this.getColor().ordinal()];
	}
}