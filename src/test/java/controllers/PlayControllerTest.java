package controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import models.Logic;
import models.Coordinate;
import models.Piece;
import models.Color;

public class PlayControllerTest {

    public PlayControllerTest() {

    }

    @Test
    public void givenPlayControllerWhenMovementRequireCorrectThenNotError() {
        Logic logic = new Logic();
        Coordinate origin = new Coordinate(2, 1);
        Coordinate target = new Coordinate(3, 2);
        PlayController playController = new PlayController(logic);
        assertNull(playController.move(origin, target));
        assertNull(playController.getPiece(origin));
        Piece pieceTarget = playController.getPiece(target);
        assertNotNull(pieceTarget);
        assertEquals(pieceTarget.getColor(), Color.WHITE);
    }

    @Test
    public void givenPlayControllerWhenMovementRequireThenError() {
        Logic logic = new Logic();
        Coordinate origin = new Coordinate(2, 1);
        Coordinate target = new Coordinate(3, 7);
        PlayController playController = new PlayController(logic);
        assertNotNull(playController.move(origin, target));
    }
}