package es.urjccode.mastercloudapps.adcs.draughts.models;

import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class GameWithDobleTest {

	@Mock
	Board board;

	@Mock
    Turn turn;
    
    @InjectMocks
	Game game;

    @Test
    public void testGivenGameWhenMoveWithOuterCoordinateThenOutCoordinateError() {
        Coordinate origin = new Coordinate(4, 7);
		Coordinate target = new Coordinate(3, 8);
		Error error = game.move(origin, target);
		assertEquals(Error.OUT_COORDINATE, error);
		verify(board, never()).move(origin, target);
		verify(turn, never()).change();
    }

    @Test
    public void testGivenGameWhenMoveEmptySquaerThenEmptySquareError() {
        Coordinate origin = new Coordinate(4, 3);
		Coordinate target = new Coordinate(3, 4);
		when(board.isEmpty(origin)).thenReturn(true);
		assertEquals(Error.EMPTY_ORIGIN, game.move(origin, target));
		verify(board, never()).move(origin, target);
		verify(turn, never()).change();
    }

    @Test
    public void testGivenGameWhenMoveOppositePieceThenError() {
        Coordinate origin = new Coordinate(3, 6);
		Coordinate target = new Coordinate(2, 7);
		Color opponentColor = Color.BLACK;
		when(board.getColor(origin)).thenReturn(opponentColor);
		when(turn.isColor(opponentColor)).thenReturn(false);
		Error error = game.move(origin, target);
		assertEquals(Error.OPPOSITE_PIECE, error);
		verify(board, never()).move(origin, target);
		verify(turn, never()).change();
    }

    @Test
    public void testGivenGameWhenNotDiagonalMovementThenError() {
        Coordinate origin = new Coordinate(5, 2);
		Coordinate target = new Coordinate(4, 2);
        Color turnColor = Color.WHITE;
        Piece piece = new Piece(turnColor);
		when(board.getColor(origin)).thenReturn(turnColor);
        when(turn.isColor(turnColor)).thenReturn(true);
        when(board.getPiece(origin)).thenReturn(piece);
		Error error = game.move(origin, target);
		assertEquals(Error.NOT_DIAGONAL, error);
		verify(board, never()).move(origin, target);
		verify(turn, never()).change();
    }

    @Test
    public void testGivenGameWhenMoveWithNotAdvancedThenError() {
        Coordinate origin = new Coordinate(5, 0);
		Coordinate target = new Coordinate(6, 1);
		Color turnColor = Color.WHITE;
		Piece piece = new Piece(turnColor);
		when(board.getColor(origin)).thenReturn(turnColor);
		when(turn.isColor(turnColor)).thenReturn(true);
		when(board.getPiece(origin)).thenReturn(piece);
		Error error = game.move(origin, target);
		assertEquals(Error.NOT_ADVANCED, error);
		verify(board, never()).move(origin, target);
		verify(turn, never()).change();
    }

    @Test
    public void testGivenGameWhenNotEmptyTargeThenError() {
        Coordinate origin = new Coordinate(5, 0);
		Coordinate target = new Coordinate(4, 1);
		Color turnColor = Color.WHITE;
		Piece piece = new Piece(turnColor);
		when(board.getColor(origin)).thenReturn(turnColor);
		when(turn.isColor(turnColor)).thenReturn(true);
		when(board.getPiece(origin)).thenReturn(piece);
		when(board.isEmpty(target)).thenReturn(false);
		Error error = game.move(origin, target);
		assertEquals(Error.NOT_EMPTY_TARGET, error);
		verify(board, never()).move(origin, target);
		verify(turn, never()).change();
    }

    @Test
    public void testGivenGameWhenCorrectMovementThenOk() {
        Coordinate origin = new Coordinate(5, 0);
		Coordinate target = new Coordinate(4, 1);
		Color turnColor = Color.WHITE;
		Piece piece = new Piece(turnColor);
		when(board.getColor(origin)).thenReturn(turnColor);
		when(turn.isColor(turnColor)).thenReturn(true);
		when(board.getPiece(origin)).thenReturn(piece);
		when(board.isEmpty(target)).thenReturn(true);
		this.game.move(origin, target);
		verify(board).move(origin, target);
		verify(turn).change();
    }

    @Test
    public void testGivenGameWhenMovementThenEatPiece() {
        Coordinate origin = new Coordinate(3, 0);
		Coordinate target = new Coordinate(5, 2);
		Coordinate between = origin.betweenDiagonal(target);
		Color turnColor = Color.BLACK;
		Piece piece = new Piece(turnColor);
		when(board.getColor(origin)).thenReturn(turnColor);
		when(turn.isColor(turnColor)).thenReturn(true);
		when(board.getPiece(origin)).thenReturn(piece);
		when(board.isEmpty(target)).thenReturn(true);
		when(board.getPiece(between)).thenReturn(new Piece(Color.WHITE));
		Error error = game.move(origin, target);
		assertNull(error);
		verify(board).remove(between);
		verify(board).move(origin, target);
		verify(turn).change();
    }

    @Test
    public void testGivenGameWhenEatEmptyPieceThenError() {
        Coordinate origin = new Coordinate(3, 0);
		Coordinate target = new Coordinate(5, 2);
		Coordinate between = origin.betweenDiagonal(target);
		Color turnColor = Color.BLACK;
		Piece piece = new Piece(turnColor);
		when(board.getColor(origin)).thenReturn(turnColor);
		when(turn.isColor(turnColor)).thenReturn(true);
		when(board.getPiece(origin)).thenReturn(piece);
		when(board.isEmpty(target)).thenReturn(true);
		when(board.getPiece(between)).thenReturn(null);
		assertEquals(Error.EATING_EMPTY, game.move(origin, target));
		verify(board, never()).remove(between);
		verify(board, never()).move(origin, target);
		verify(turn, never()).change();}

    @Test
    public void testGivenGameWhenMoveBadDistanceThenError() {
        Coordinate origin = new Coordinate(5, 0);
		Coordinate target = new Coordinate(2, 3);
		Color turnColor = Color.WHITE;
		Piece piece = new Piece(turnColor);
		when(board.getColor(origin)).thenReturn(turnColor);
		when(turn.isColor(turnColor)).thenReturn(true);
		when(board.getPiece(origin)).thenReturn(piece);
		Error error = game.move(origin, target);
		assertEquals(Error.BAD_DISTANCE, error);
		verify(board, never()).move(origin, target);
		verify(turn, never()).change();}

}