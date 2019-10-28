package controllers;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import models.Logic;

public class ResumeControllerTest {

	ResumeController resumeController;
	PlayController playController;
	
	@Before
	public void before() {
		resumeController = new ResumeController();
		Logic logic = new Logic();
		playController = new PlayController(logic);
	}
	
	@Test
	public void givenResumeControllerWhenColorCantMoveThenResume() {
		assertFalse(playController.canMove());
		assertTrue(resumeController.wantsToPlayAgain());
	}
	
	@Test
	public void givenResumeControllerWhenColorCantMoveThenEnd() {
		assertFalse(playController.canMove());
		assertFalse(resumeController.wantsToPlayAgain());
	}
    
    @Test
	public void givenResumeControllerWhenGiveUpThenResume() {
		assertFalse(playController.giveUp());
		assertTrue(resumeController.wantsToPlayAgain());
	}
	
	@Test
	public void givenResumeControllerWhenGiveUpThenEnd() {
		assertFalse(playController.giveUp());
		assertFalse(resumeController.wantsToPlayAgain());
	}
}