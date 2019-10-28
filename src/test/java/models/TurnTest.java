package models;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class TurnTest {

    Turn turn;

    @Before
	public void before() {
        turn = new Turn();
    }

	@Test
	public void givenTurnWhenFirstTurnThenCheckIfIsWhiteTurn() {
        assertEquals(this.turn.getColor(), Color.WHITE);
    }
    
    @Test
    public void givenTurnWhenTurnChangeThenChangeTurnColor(){
        this.turn.change();
        assertEquals(this.turn.getColor(), Color.BLACK);
        this.turn.change();
        assertEquals(this.turn.getColor(), Color.WHITE);
        this.turn.change();
        assertEquals(this.turn.getColor(), Color.BLACK);
    }
}