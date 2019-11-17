package es.urjccode.mastercloudapps.adcs.draughts.models;

class Draught extends Piece {

    Draught(Color color) {
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
        if (this.getNumberOfPiecesInDiagonal(origin, target, pieceProvider) > 1){
            return Error.TOO_MANY_TO_EAT;
        }
		return null;
    }

    @Override
	public String toString(){
		final String[] letters = {"B","N"};
        return letters[this.getColor().ordinal()];
	}

    public int getNumberOfPiecesInDiagonal(Coordinate origin, Coordinate target, PieceProvider pieceProvider){
        int numberOfPieces = 0;
        for (Coordinate coordinateToCheck : origin.betweenDiagonal(target)) {
            if (pieceProvider.getPiece(coordinateToCheck) != null){
                numberOfPieces ++;
            }
        }
        return numberOfPieces;
    }
}