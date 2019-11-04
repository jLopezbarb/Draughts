package es.urjccode.mastercloudapps.adcs.draughts.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import es.urjccode.mastercloudapps.adcs.draughts.models.Game;
import es.urjccode.mastercloudapps.adcs.draughts.models.Coordinate;
import es.urjccode.mastercloudapps.adcs.draughts.models.Piece;
import es.urjccode.mastercloudapps.adcs.draughts.models.State;
import es.urjccode.mastercloudapps.adcs.draughts.models.Color;
import es.urjccode.mastercloudapps.adcs.draughts.models.Board;

@RunWith(MockitoJUnitRunner.class)
public class PlayControllerTest {

    @Mock
    Board board;

    @InjectMocks
    Game game;

    PlayController playController;
    
    @Before
    public void initMocks() {
        this.game = new Game();
        State state = new State();
        this.playController = new PlayController(this.game, state);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void givenPlayControllerWhenMovementRequiereCorrectThenNotError() {
        Coordinate origin = new Coordinate(5, 0);
        Coordinate target = new Coordinate(4, 1);
        assertNull(playController.move(origin, target));
        assertNull(playController.getPiece(origin));
        Piece pieceTarget = playController.getPiece(target);
        assertNotNull(pieceTarget);
        assertEquals(pieceTarget.getColor(), Color.WHITE);
    }

    @Test
    public void givenPlayControllerWhenPieceMovesOneThenNotError(){
        Coordinate origin = new Coordinate(row(7), column(0));
        Coordinate target = new Coordinate(row(6), column(1));
        when(board.isEmpty(origin)).thenReturn(false);
        when(board.getColor(origin)).thenReturn(Color.WHITE);
        when(board.getPiece(origin)).thenReturn(new Piece(Color.WHITE));
        when(board.isEmpty(target)).thenReturn(true);
        assertNull(playController.move(origin, target));
        when(board.getPiece(origin)).thenReturn(null);
        assertNull(playController.getPiece(origin));
        when(board.getPiece(target)).thenReturn(new Piece(Color.WHITE));
        Piece pieceTarget = playController.getPiece(target);
        assertNotNull(pieceTarget);
        assertEquals(pieceTarget.getColor(), Color.WHITE);
    }

    @Test
    public void givenPlayControllerWhenPieceEatOneThenNotError(){
        Coordinate origin = new Coordinate(row(7), column(0));
        Coordinate target = new Coordinate(row(5), column(2));
        Coordinate beetween = new Coordinate(row(6), column(1));
        when(board.isEmpty(origin)).thenReturn(false);
        when(board.getColor(origin)).thenReturn(Color.WHITE);
        when(board.getPiece(origin)).thenReturn(new Piece(Color.WHITE));
        when(board.getPiece(beetween)).thenReturn(new Piece(Color.BLACK));
        when(board.isEmpty(target)).thenReturn(true);
        assertNull(playController.move(origin, target));
        when(board.getPiece(origin)).thenReturn(null);
        assertNull(playController.getPiece(origin));
        when(board.getPiece(beetween)).thenReturn(null);
        assertNull(playController.getPiece(origin));
        when(board.getPiece(target)).thenReturn(new Piece(Color.WHITE));
        Piece pieceTarget = playController.getPiece(target);
        assertNotNull(pieceTarget);
        assertEquals(pieceTarget.getColor(), Color.WHITE);
    }

    private int row(int row) {
        return row;
    }

    private int column(int column) {
        return column;
    }

}