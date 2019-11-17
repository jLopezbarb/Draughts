package es.urjccode.mastercloudapps.adcs.draughts.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class GameWithDraughtsTest {

    @Mock
    Turn turn;

    @Mock
    Piece piece;
    
    @Mock
    Board board;

    @InjectMocks
    Game game;

    @Mock
    Draught draught;

    @Before
    public void before(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGivenGameWhenWhitePawnAtLimitThenNewDraugts(){
        Coordinate origin = new Coordinate(1,0);
        Coordinate target = new Coordinate(0,1);
        
        when (turn.getColor()).thenReturn(Color.WHITE);
        when(board.isEmpty(origin)).thenReturn(false);
        when(board.getColor(origin)).thenReturn(Color.WHITE);
        when(board.getPiece(origin)).thenReturn(piece);
        when(piece.isCorrect(origin, target, board)).thenReturn(null);
        when(board.remove(origin)).thenReturn(new Pawn(Color.WHITE));
        
        when(board.getPiece(target)).thenReturn(new Pawn(Color.WHITE));
        game.move(origin, target);
        verify(board).remove(target);
        verify(board).put(any(Coordinate.class), any(Draught.class));
    }

    @Test
    public void testGivenGameWhenWhitePawnAtLimitAndEatingThenNewDraugts(){
        Coordinate origin = new Coordinate(2,1);
        Coordinate target = new Coordinate(0,3);
        when (turn.getColor()).thenReturn(Color.WHITE);
        when(board.isEmpty(origin)).thenReturn(false);
        when(board.getColor(origin)).thenReturn(Color.WHITE);
        when(board.getPiece(origin)).thenReturn(piece);
        when(piece.isCorrect(origin, target, board)).thenReturn(null);
        when(board.remove(origin)).thenReturn(new Pawn(Color.WHITE));
        when(board.getPiece(target)).thenReturn(new Pawn(Color.WHITE));
        game.move(origin, target);
        verify(board).remove(origin.betweenDiagonal(target));
        verify(board).remove(target);
        verify(board).put(any(Coordinate.class), any(Draught.class));
    }

    @Test
    public void testGivenGameWhenBlackPawnAtLimitThenNewDraugts(){
        Coordinate origin = new Coordinate(6,3);
        Coordinate target = new Coordinate(7,2);
        when (turn.getColor()).thenReturn(Color.BLACK);
        when(board.isEmpty(origin)).thenReturn(false);
        when(board.getColor(origin)).thenReturn(Color.BLACK);
        when(board.getPiece(origin)).thenReturn(piece);
        when(piece.isCorrect(origin, target, board)).thenReturn(null);
        when(board.remove(origin)).thenReturn(new Pawn(Color.BLACK));
        when(board.getPiece(target)).thenReturn(new Pawn(Color.BLACK));
        game.move(origin, target);
        verify(board).remove(target);
        verify(board).put(any(Coordinate.class), any(Draught.class));
    }

    @Test
    public void testGivenGameWhenBlackPawnEatsAndPositionAtLimitThenNewDraugts(){
        Coordinate origin = new Coordinate(5, 4);
        Coordinate target = new Coordinate(7, 2);
        when(turn.getColor()).thenReturn(Color.BLACK);
        when(board.isEmpty(origin)).thenReturn(false);
        when(board.getColor(origin)).thenReturn(Color.BLACK);
        when(board.getPiece(origin)).thenReturn(piece);
        when(piece.isCorrect(origin, target, board)).thenReturn(null);
        when(board.remove(origin)).thenReturn(new Pawn(Color.BLACK));
        when(board.getPiece(target)).thenReturn(new Pawn(Color.BLACK));
        game.move(origin, target);
        verify(board).remove(origin.betweenDiagonal(target));
        verify(board).remove(target);
        verify(board).put(any(Coordinate.class), any(Draught.class));
    }

    @Test
    public void testGivenGameWhenWhiteDraughtWantsToMoveThenMove() {
        Coordinate origin = new Coordinate(5, 0);
        Coordinate target = new Coordinate(3, 2);
        when(turn.getColor()).thenReturn(Color.WHITE);
        when(board.isEmpty(origin)).thenReturn(false);
        when(board.getColor(origin)).thenReturn(Color.WHITE);
        when(board.getPiece(origin)).thenReturn(draught);
        when(draught.isCorrect(origin, target, board)).thenReturn(null);
        when(board.remove(origin)).thenReturn(draught);
        when(board.remove(origin.betweenDiagonal(target))).thenReturn(null);
        when(board.getPiece(target)).thenReturn(draught);
        game.move(origin, target);
        verify(board).move(origin, target);
    }

    @Test
    public void testGivenGameWhenWhiteDraughtWantsToMoveReversedThenMove() {
        Coordinate origin = new Coordinate(3, 2);
        Coordinate target = new Coordinate(5, 0);
        when(turn.getColor()).thenReturn(Color.WHITE);
        when(board.isEmpty(origin)).thenReturn(false);
        when(board.getColor(origin)).thenReturn(Color.WHITE);
        when(board.getPiece(origin)).thenReturn(draught);
        when(board.remove(origin)).thenReturn(draught);
        when(board.remove(origin.betweenDiagonal(target))).thenReturn(null);
        when(board.getPiece(target)).thenReturn(draught);
        game.move(origin, target);
        verify(board).move(origin, target);
    }

    @Test
    public void testGivenGameWhenWhiteDraughtWantsToEatThenEat() {
        Coordinate origin = new Coordinate(2, 3);
        Coordinate target = new Coordinate(5, 0);
        when(turn.getColor()).thenReturn(Color.WHITE);
        when(board.isEmpty(origin)).thenReturn(false);
        when(board.getColor(origin)).thenReturn(Color.WHITE);
        when(board.getPiece(origin)).thenReturn(draught);
        when(board.remove(origin)).thenReturn(draught);
        when(board.remove(origin.betweenDiagonal(target))).thenReturn(new Pawn(Color.BLACK));
        when(board.getPiece(target)).thenReturn(draught);
        game.move(origin, target);
        verify(board).move(origin, target);
    }

    @Test
    public void testGivenGameWhenWhiteDraughtWantsToEatMoreThanOneInOneMovementThenError() {
        Coordinate origin = new Coordinate(2, 3);
        Coordinate target = new Coordinate(5, 0);
        Draught draughtToCheck = new Draught(Color.WHITE);
        when(turn.getColor()).thenReturn(Color.WHITE);
        when(board.isEmpty(origin)).thenReturn(false);
        when(board.getColor(origin)).thenReturn(Color.WHITE);
        when(board.getPiece(origin)).thenReturn(draughtToCheck);
        when(board.getPiece(new Coordinate(3, 2))).thenReturn(new Pawn(Color.BLACK));
        when(board.getPiece(new Coordinate(4, 1))).thenReturn(new Pawn(Color.BLACK));
        when(board.isEmpty(target)).thenReturn(true);
        Error error = draughtToCheck.isCorrect(origin, target, board);
        assertEquals(Error.TOO_MANY_TO_EAT,error);
    }

    @Test
    public void testGivenGameWhenWhiteDoesntHavePiecesThenBlocked() {
        when(turn.getColor()).thenReturn(Color.WHITE);
        when(board.getPieces(Color.WHITE)).thenReturn(new ArrayList<>());
        assertTrue(game.isBlocked());
    }
    
    @Test
    public void testGivenGameWhenBlackDoesntHavePiecesThenBlocked() {
        when(turn.getColor()).thenReturn(Color.BLACK);
        when(board.getPieces(Color.BLACK)).thenReturn(new ArrayList<>());
        assertTrue(game.isBlocked());
    }

    @Test
    public void testGivenGameWhenBlacktHavePiecesThenNotBlocked() {
        when(turn.getColor()).thenReturn(Color.BLACK);
        List<Piece> pieces = new ArrayList<>();
        pieces.add(new Pawn(Color.BLACK));
        when(board.getPieces(Color.BLACK)).thenReturn(pieces);
        assertFalse(game.isBlocked());
    }

    @Test
    public void testGivenGameWhenWhitetHavePiecesThenNotBlocked() {
        when(turn.getColor()).thenReturn(Color.WHITE);
        List<Piece> pieces = new ArrayList<>();
        pieces.add(new Pawn(Color.WHITE));
        when(board.getPieces(Color.WHITE)).thenReturn(pieces);
        assertFalse(game.isBlocked());
    }

}