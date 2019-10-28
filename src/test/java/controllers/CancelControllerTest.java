package controllers;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import models.Logic;
import models.StateValue;

public class CancelControllerTest {
    
    @Test
	public void givenCancelControllerWhenColorWantToGiveUpThenGiveUp() {
        Logic logic = new Logic();
        CancelController cancelController = new CancelController(logic);
        cancelController.cancel();
        assertEquals(logic.getStateValue(), StateValue.FINAL);
	}
}