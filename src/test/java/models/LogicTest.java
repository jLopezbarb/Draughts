package models;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class LogicTest {

    Logic logic;

    @Before
    public void before(){
        this.logic = new Logic();
    }

    @Test
    public void givenStateWhenNextStateThenNexStateValue() {
        assertEquals(this.logic.getStateValue(),StateValue.INITIAL);
        this.logic.next();
        assertEquals(this.logic.getStateValue(),StateValue.IN_GAME);
        this.logic.next();
        assertEquals(this.logic.getStateValue(),StateValue.FINAL);
        this.logic.next();
        assertEquals(this.logic.getStateValue(),StateValue.EXIT);
    }
}