package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.*;

public class TestSession {
	
	private String name;
	private Player player;
	private GameMode mode;
	private Session session;

// --- SET UP -------------------------------------------- //
	
	@BeforeEach
	public void Setup() {
		name = "Samuele";
		player = new PlayerImpl(name);
		mode = GameMode.SINGLE_PLAYER;
		session = new SessionImpl(player,null);
	}
	
// --- GAME TEST --------------------------------------- //
	@Test 
	public void SessionCreate() {
		assertFalse("All'inizio non ci sono partite attive",session.hasActiveGame());
	}
	
// --- PLAYER TEST --------------------------------------- //
	
	@Test 
	public void  PlayerNotNull() {
		assertNotNull(session.getPlayer());
	}
	
	@Test
	public void PlayerCorrect() {
		assertTrue(session.getPlayer().equals(player));
	}
	

	
// --- LEVEL TEST --------------------------------------- //
// ---  TEST --------------------------------------- //
// --- TEST --------------------------------------- //
// --- TEST --------------------------------------- //
// --- TEST --------------------------------------- //
// --- TEST --------------------------------------- //

	
// --- LOAD TEST --------------------------------------- //
// --- SAVE TEST --------------------------------------- //

	
	
}




