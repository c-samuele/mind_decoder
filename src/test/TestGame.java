package test;
import model.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;

public class TestGame {

	private String name = "player1";
	private GameMode mode;
	private Session session;
	private Game game;
	
	private Code w1,	// wrong code
		 		 w2,	// wrong code
		 		 c1,	// correct code
		 		 i1;	// impossibile code
	
	private List<Color> l1,
						l2;
	
	@Before
	public void setUp() {
		Player player = new PlayerImpl(name);
		mode = GameMode.SINGLE_PLAYER;
		session = SessionImpl.getInstance(player);
		session.createNewGame(mode,session.getUnlockedLevel());

		l1 = List.of(Color.RED, Color.BLUE, Color.GREEN,Color.PINK);
		l2 = List.of(Color.PINK);
		
		// Wrong Attempt
		w1 = new CodeImpl(l1);
		w2 = new CodeImpl(l2);
		
		// Correct Attempt
		c1 = session.getCurrentGame().generateRandomAttempt();
		
		game = session.getCurrentGame();
	}

	@Test
	public void testWrongAttempt() {
		// Attempt with too many colors
		assertThrows(IllegalArgumentException.class, () -> game.makeAttempt(w1));
		 // Attempt with too few colors
		assertThrows(IllegalArgumentException.class, () -> game.makeAttempt(w2));
	}
	
	@Test
	public void testMakeAttempt() {
		game.makeAttempt(c1);
		assertEquals(game.calculateAttempts(game.getLevel())-1,
					 game.getRemainingAttempts());
	}
	
	@Test
	public void testCorrectAttempt() {
			game.makeAttempt(game.getSecretCode());
			assertEquals(GameState.WIN,game.getState());
	}
	
	@Test
	public void testIncrementRowAttempts() {
		game.nextAttemptRow();
		assertEquals(1,game.getCurrentAttemptRow());
	}
	
	@Test
	public void testSetColorByIndexOnCurrentAttempt() {
		game.setColorCurrentAttempt(2, Color.CYAN);
		assertEquals(Color.CYAN,game.getCurrentAttempt()[2]);
	}
	
	@Test
	public void testSetColorByWrongIndexOnCurrentAttempt() {
		assertThrows(IllegalArgumentException.class, () -> game.setColorCurrentAttempt(3, Color.BLUE));
		assertThrows(IllegalArgumentException.class, () -> game.setColorCurrentAttempt(-1, Color.BLUE));
	}
	
	@Test 
	public void testIsCurrentAttemptFull() {
		for(int i = 0;i < game.numberOfColors();i++)
			game.setColorCurrentAttempt(i,Color.RED);
		assertTrue(game.isCurrentAttemptFull());
	}
	
	@Test
	public void testWrongIsCurrentAttemptFull() {
		game.setColorCurrentAttempt(0,Color.YELLOW);
		assertFalse(game.isCurrentAttemptFull());
	}
	
	@Test
	public void testResetCurrentAttempt() {
		for(int i = 0;i < game.numberOfColors();i++)
			game.setColorCurrentAttempt(i,Color.LIME);
		
		game.resetCurrentAttempt();
		
		for(int i = 0; i < game.numberOfColors(); i++)
	        assertNull(game.getCurrentAttempt()[i]);
	}
	
	@Test
	public void testVerifyIndex() {	    
	    int indexCorrect = game.verifyIndex(game.getSecretCode());
	    assertEquals(game.numberOfColors(), indexCorrect);

	    assertThrows(IllegalArgumentException.class, () -> game.verifyIndex(w1));
	    assertThrows(IllegalArgumentException.class, () -> game.verifyIndex(w2));
	}
	
	@Test
	public void testVerifyColor() {
	    int correctColors = game.verifyColor(c1);
	    assertTrue(correctColors > 0 && correctColors <= game.numberOfColors());

	    assertThrows(IllegalArgumentException.class, () -> game.verifyColor(w1));
	    assertThrows(IllegalArgumentException.class, () -> game.verifyColor(w2));
	}
	
	
	@Test
	public void testIsWon() {
	    assertTrue(game.isWon(game.numberOfColors(), game.numberOfColors()));
	    assertFalse(game.isWon(game.numberOfColors() - 1, game.numberOfColors()));
	}

	@Test
	public void testIsOver() {
		i1 = new CodeImpl(List.of(Color.PINK,Color.LIME,Color.MAGENTA));
	    assertFalse(game.isOver());
	    while(game.getRemainingAttempts() > 0) 
	    	game.makeAttempt(i1);
	    assertTrue(game.isOver());
	}

}
