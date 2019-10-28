package models;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class BoardTest {

    Board board;
    
    @Before
    public void before(){
        this.board = new Board();
    }

    @Test
    public void givenBoardWhenMenIsToTransformThenNotError(){
        Men men = new Men(new Coordinate(2,1), Color.WHITE);
        this.board.add(men);
        assertNotNull(this.board.transformToKing(men));
    }

    @Test
	public void givenBoardWhenWhiteCantMoveThenLoseByBlock() {
		for (Piece piece: this.board.getWhitePieces()) {
            Square[][] squares = this.board.getSquares();
			for (int i = 0; i<8; i++) {
				for (int j = 0; j<8; j++) {
                    if (squares[i][j].isPlayable()){
                        assertFalse(this.board.canMove(piece, new Coordinate(i, j)));
                    }
				}
			}
		}		
    }

    @Test
	public void givenBoardWhenWhiteHasNoPiecesThenLose() {
        assertTrue(this.board.getWhitePieces().size() == 0);
    }

    

}