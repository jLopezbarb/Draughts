package models;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class GameTest {

    Game game;

    @Test
    public void givenGameWhenResetGameThenNotError() {
        Game game = new Game();
        this.game.reset();
        assertEquals(game.getBoard(), new Board());
    }
    
}
