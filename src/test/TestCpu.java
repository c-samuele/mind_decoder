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
	private List<Color> testAttempt;
	
	@Before
	public void setUp() {
		 game = new GameImpl(GameMode.AI_CHALLENGE,4);
		 			 
		 System.out.println("SECRET CODE:\t\t" + game.getSecretCode().getColor());
		 testAttempt = List.of(Color.RED,Color.RED,Color.RED,Color.RED,Color.RED,Color.BLUE);
	}
	
	@Test
	public void testInitMatrix() {
		cpu = new CpuImpl(game.numberOfColors(),
						  game.getAvailableColors());
		
		
		for(int i = 0; i < 20;i++) {
			Code c = cpu.makeUniqueRandomAttempt();
			game.makeAttempt(c);
			cpu.addAttempt(c, game.getHints().getLast());
			System.out.println("ATTEMPT N:"+game.getCurrentAttemptRow());
			cpu.printMatrix();
		}
		
		for(int j = 0;j<1;j++) {
			Code c = cpu.chooseAttempt();	
//			game.makeAttempt(c);
//			cpu.addAttempt(c, game.getHints().getLast());
//			System.out.println("ATTEMPT N:"+game.getCurrentAttemptRow());
//			cpu.printMatrix();
		}
		
		
		
		
//		
//		
//		for(int j = 0;j<16;j++){
//			Code c = cpu.chooseAttempt();
//			game.makeAttempt(c);
//			cpu.addAttempt(c, game.getHints().getLast());
//			System.out.println("ATTEMPT N:"+game.getCurrentAttemptRow());
//			cpu.printMatrix();
//		}
			

	}

	
	


}
