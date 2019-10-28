package models;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class SquareTest {

	private Square square;
	
	@Test
	public void givenSquareWhenPieceWantToMoveToThisSquareThenCheckIsEmpty() {
		assertTrue(square.isEmpty());
	}

	@Test
	public void givenSquareWhenPieceWantToMoveToThisSquareThenCheckIsPlayable(){
		assertTrue(square.isPlayable());
	}
	
}