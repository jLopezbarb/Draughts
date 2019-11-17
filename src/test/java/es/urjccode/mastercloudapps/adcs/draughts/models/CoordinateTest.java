package es.urjccode.mastercloudapps.adcs.draughts.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class CoordinateTest {

    @Test
    public void testGivenTwoCoordinatesWhenBettweenDiagonalThenOk() {
        assertTrue(new Coordinate(2, 2).betweenDiagonal(new Coordinate(0, 0)).contains(new Coordinate(1, 1)));
        assertTrue(new Coordinate(2, 2).betweenDiagonal(new Coordinate(4, 0)).contains(new Coordinate(3, 1)));
        assertTrue(new Coordinate(2, 2).betweenDiagonal(new Coordinate(4, 4)).contains(new Coordinate(3, 3)));
        assertTrue(new Coordinate(2, 2).betweenDiagonal(new Coordinate(0, 4)).contains(new Coordinate(1, 3)));

    }

    @Test
    public void testGivenTwoCoordinatesWhenGetDistanceThenResult() {
        assertEquals(3, new Coordinate(3, 4).diagonalDistance(new Coordinate(0, 7)));
    }

}