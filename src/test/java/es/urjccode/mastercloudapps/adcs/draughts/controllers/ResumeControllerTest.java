package es.urjccode.mastercloudapps.adcs.draughts.controllers;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import es.urjccode.mastercloudapps.adcs.draughts.models.Game;
import es.urjccode.mastercloudapps.adcs.draughts.models.State;
import es.urjccode.mastercloudapps.adcs.draughts.models.StateValue;

public class ResumeControllerTest {


	private ResumeController resumeController;
    private State state;

	@Before
	public void before() {
        state = new State();
        state.next();
		resumeController = new ResumeController(new Game(), state);
	}

	@Test
	public void givenResumeControllerWhenWantsToResumeThenNewGame() {
		resumeController.resume(true);
		assertEquals(StateValue.INITIAL, state.getValue());
	}

    @Test
	public void givenResumeControllerWhenNoWantsToResumeThenNewGame() {
		resumeController.resume(false);
		assertEquals(StateValue.FINAL, state.getValue());
	}

}