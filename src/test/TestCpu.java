package test;
import model.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class TestCpu {

	private Cpu cpu;
	private Game game;
	List<Color> s1,s2,s3,s4,s5;
	private Code c1,c2,c3,c4,c5;
	
	@Before
	public void setUp() {
		 game = new GameImpl(GameMode.AI_CHALLENGE,3);
		 
		 s1 = List.of(Color.RED,Color.BLUE,Color.GREEN,Color.YELLOW,Color.ORANGE);
		 c1 = new CodeImpl(s1);
		 
		 s2 = List.of(Color.BLUE,Color.YELLOW,Color.ORANGE,Color.RED,Color.GREEN);
		 c2 = new CodeImpl(s2);
		 
		 s3 = List.of(Color.YELLOW,Color.RED,Color.BLUE,Color.GREEN,Color.ORANGE);
		 c3 = new CodeImpl(s3);
		 
		 s4 = List.of(Color.ORANGE,Color.BLUE,Color.GREEN,Color.YELLOW,Color.RED);
		 c4 = new CodeImpl(s4);
		 
		 s5 = List.of(Color.BLUE,Color.RED,Color.GREEN,Color.ORANGE,Color.YELLOW);
		 c5 = new CodeImpl(s5);
		 			 
		 System.out.println("SECRET CODE:\t\t" + game.getSecretCode().getColor());
		 
	}
	
	@Test
	public void testInitMatrix() {
		cpu = new CpuImpl(game.numberOfColors(),
						  game.getAvailableColors());
		
		
		game.makeAttempt(c1);
		cpu.addAttempt(c1, game.getHints().getLast());
		cpu.printMatrix();
		
		game.makeAttempt(c2);
		cpu.addAttempt(c2, game.getHints().getLast());
		cpu.printMatrix();
		
		game.makeAttempt(c3);
		cpu.addAttempt(c3, game.getHints().getLast());
		cpu.printMatrix();
		
		game.makeAttempt(c4);
		cpu.addAttempt(c4, game.getHints().getLast());
		cpu.printMatrix();
		
		game.makeAttempt(c5);
		cpu.addAttempt(c5, game.getHints().getLast());
		cpu.printMatrix();
		

	}

	
	


}
