package models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class KingTest {

    Board board;

    @Before
    public void before(){ 
        this.board = new Board();
    }

    @Test
    public void givenMenWhenMoveThenNotError(){
        King king = new King(new Coordinate(6,1), Color.WHITE);
        this.board.add(king);
        Coordinate target = new Coordinate(8, 3);
        assertTrue(board.canMove(king, target));
        assertNotNull(king.move(target));
        assertEquals(king, this.board.getPiece(target));
    }

    @Test
    public void givenMenWhenEatsThenNotError(){
        King king = new King(new Coordinate(6,1), Color.WHITE);
        Men men = new Men(new Coordinate(5,2), Color.BLACK);
        this.board.add(king);
        this.board.add(men);
        assertTrue(board.canEat(king, men));
        assertNotNull(king.eat(men));
        assertEquals(king, this.board.getPiece(new Coordinate(4, 3)));
    }

    @Test
    public void givenMenWhenEatsMoreThanOneThenNotError(){
        King king = new King(new Coordinate(6,1), Color.WHITE);
        Men men1 = new Men(new Coordinate(5,2), Color.BLACK);
        Men men2 = new Men(new Coordinate(3,4), Color.BLACK);
        this.board.add(king);
        this.board.add(men1);
        this.board.add(men2);
        assertTrue(board.canEat(king, men1));
        assertNotNull(men1.eat(men1));
        assertEquals(men1, this.board.getPiece(new Coordinate(4, 3)));
        assertTrue(board.canEat(men1, men2));
        assertNotNull(men1.eat(men2));
        assertEquals(men1, this.board.getPiece(new Coordinate(2, 5)));
    }

}