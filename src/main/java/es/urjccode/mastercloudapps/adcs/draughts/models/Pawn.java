package es.urjccode.mastercloudapps.adcs.draughts.models;

import java.util.ArrayList;
import java.util.List;

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

	@Override
	boolean canMove(Coordinate position, PieceProvider pieceProvider) {
		List<Coordinate> possibleMoves = getPossibleMoves(position, pieceProvider);
		for (Coordinate coordinate : possibleMoves) {
			if (this.isCorrect(position, coordinate, pieceProvider) == null){
				return true;
			}
		}
		return false;
	}

	private List<Coordinate> getPossibleMoves(Coordinate position, PieceProvider pieceProvider){
		List<Coordinate> possibleCoordinates = new ArrayList<>();
		if (this.getColor() == Color.WHITE){
			possibleCoordinates.add(new Coordinate(position.getRow() + 1, position.getColumn() + 1));
			possibleCoordinates.add(new Coordinate(position.getRow() - 1, position.getColumn() + 1));
			possibleCoordinates.add(new Coordinate(position.getRow() - 2, position.getColumn() + 2));
			possibleCoordinates.add(new Coordinate(position.getRow() + 2, position.getColumn() + 2));
		} else{
			possibleCoordinates.add(new Coordinate(position.getRow() + 1, position.getColumn() - 1));
			possibleCoordinates.add(new Coordinate(position.getRow() - 1, position.getColumn() - 1));
			possibleCoordinates.add(new Coordinate(position.getRow() - 2, position.getColumn() - 2));
			possibleCoordinates.add(new Coordinate(position.getRow() + 2, position.getColumn() - 2));
		}
		return possibleCoordinates;
	}


}