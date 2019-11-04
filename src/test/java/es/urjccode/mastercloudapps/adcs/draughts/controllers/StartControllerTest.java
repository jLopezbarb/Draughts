package es.urjccode.mastercloudapps.adcs.draughts.controllers;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import es.urjccode.mastercloudapps.adcs.draughts.models.Game;
import es.urjccode.mastercloudapps.adcs.draughts.models.State;
import es.urjccode.mastercloudapps.adcs.draughts.models.StateValue;

public class StartControllerTest {

	private StartController startController;

    State state;

	@Before
	public void before() {
        state = new State();
		startController = new StartController(new Game(), state);
	}

	@Test
	public void testGivenStartControllerWhenStartThenChangeStateValue() {
		assertEquals(StateValue.INITIAL, state.getValue());
		startController.start();
		assertEquals(StateValue.IN_GAME, state.getValue());
	}

}