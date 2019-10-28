package models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class MenTest {

    Board board;

    @Before
    public void before(){ 
        this.board = new Board();
    }

    @Test
    public void givenMenWhenMoveThenNotError(){
        Men men = new Men(new Coordinate(6,1), Color.WHITE);
        this.board.add(men);
        Coordinate target = new Coordinate(5, 2);
        assertTrue(board.canMove(men, target));
        assertNotNull(men.move(target));
        assertEquals(men, this.board.getPiece(target));
    }

    @Test
    public void givenMenWhenEatsThenNotError(){
        Men men1 = new Men(new Coordinate(6,1), Color.WHITE);
        Men men2 = new Men(new Coordinate(5,2), Color.BLACK);
        this.board.add(men1);
        this.board.add(men2);
        assertTrue(board.canEat(men1, men2));
        assertNotNull(men1.eat(men2));
        assertEquals(men1, this.board.getPiece(new Coordinate(4, 3)));
    }

    @Test
    public void givenMenWhenEatsMoreThanOneThenNotError(){
        Men men1 = new Men(new Coordinate(6,1), Color.WHITE);
        Men men2 = new Men(new Coordinate(5,2), Color.BLACK);
        Men men3 = new Men(new Coordinate(3,4), Color.BLACK);
        this.board.add(men1);
        this.board.add(men2);
        this.board.add(men3);
        assertTrue(board.canEat(men1, men2));
        assertNotNull(men1.eat(men2));
        assertEquals(men1, this.board.getPiece(new Coordinate(4, 3)));
        assertTrue(board.canEat(men1, men3));
        assertNotNull(men1.eat(men3));
        assertEquals(men1, this.board.getPiece(new Coordinate(2, 5)));
    }

    @Test
    public void givenMenWhenTransformsToKingThenNotError(){
        Men men = new Men(new Coordinate(2,1), Color.WHITE);
        this.board.add(men);
        Coordinate target = new Coordinate(1, 2);
        assertTrue(this.board.canMove(men, target));
        assertNotNull(men.move(target));
        assertEquals(men, this.board.getPiece(target));
        assertNotNull(this.board.transformToKing(men));
        Piece piece = this.board.getPiece(target);
        assertNotNull(piece);
        assertTrue(piece instanceof King);
    }

}