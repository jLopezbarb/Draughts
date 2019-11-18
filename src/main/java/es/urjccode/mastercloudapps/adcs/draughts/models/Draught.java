package es.urjccode.mastercloudapps.adcs.draughts.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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


	@Override
	boolean canMove(Coordinate position, PieceProvider pieceProvider) {
		List<Coordinate> possibleMoves = getPossibleMoves(position);
		for (Coordinate coordinate : possibleMoves) {
			if (this.isCorrect(position, coordinate, pieceProvider) == null){
				return true;
			}
		}
		return false;
	}

	private List<Coordinate> getPossibleMoves(Coordinate position){
        List<Coordinate> possibleCoordinates = new ArrayList<>();
        possibleCoordinates.addAll(this.getBottomRight(position));
        possibleCoordinates.addAll(this.getBottomLeft(position));
        possibleCoordinates.addAll(this.getTopRight(position));
        possibleCoordinates.addAll(this.getTopLeft(position));
		return possibleCoordinates;
	}

	private List<Coordinate>  getTopLeft(Coordinate position) {
        List<Coordinate> possibleCoordinates = new ArrayList<>();
        do {
            possibleCoordinates.add(new Coordinate(position.getRow() - 1, position.getColumn() - 1));
            position = new Coordinate(position.getRow() - 1, position.getColumn() - 1);
        } while(position.getRow() >= Coordinate.LOWER_LIMIT && position.getColumn() >= Coordinate.LOWER_LIMIT);
        return possibleCoordinates;
    }

    private List<Coordinate>  getTopRight(Coordinate position) {
        List<Coordinate> possibleCoordinates = new ArrayList<>();
        do {
            possibleCoordinates.add(new Coordinate(position.getRow() - 1, position.getColumn() + 1));
            position = new Coordinate(position.getRow() - 1, position.getColumn() + 1);
        } while(position.getRow() >= Coordinate.LOWER_LIMIT && position.getColumn() <= Coordinate.UPPER_LIMIT);
        return possibleCoordinates;
    }

    private List<Coordinate>  getBottomRight(Coordinate position) {
        List<Coordinate> possibleCoordinates = new ArrayList<>();
        do {
            possibleCoordinates.add(new Coordinate(position.getRow() + 1, position.getColumn() + 1));
            position = new Coordinate(position.getRow() + 1, position.getColumn() + 1);
        } while(position.getRow() <= Coordinate.UPPER_LIMIT && position.getColumn() <= Coordinate.UPPER_LIMIT);
        return possibleCoordinates;
    }

    private List<Coordinate>  getBottomLeft(Coordinate position) {
        List<Coordinate> possibleCoordinates = new ArrayList<>();
        do {
            possibleCoordinates.add(new Coordinate(position.getRow() + 1, position.getColumn() - 1));
            position = new Coordinate(position.getRow() + 1, position.getColumn() - 1);
        } while(position.getRow() <= Coordinate.UPPER_LIMIT && position.getColumn() >= Coordinate.LOWER_LIMIT);
        return possibleCoordinates;
	}

}