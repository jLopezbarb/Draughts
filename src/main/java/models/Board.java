package models;

import java.util.List;

public class Board {

    Square[][] board;

    public boolean canMove(Piece piece, Coordinate coordinate){
        return false;
    }

	public boolean canEat(Piece men1, Piece men2) {
		return false;
	}

	public Piece getPiece(Coordinate coordinate) {
		return null;
    }

    public void add(Piece piece){
        
    }

	public Error transformToKing(Men men) {
        return null;
	}

	public List<Piece> getWhitePieces() {
		return null;
	}

	public Square[][] getSquares() {
		return null;
	}
    

}