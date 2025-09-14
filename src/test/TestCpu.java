package test;
import model.*;

import static org.junit.Assert.assertArrayEquals;
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
	private Code testCode;
	
	@Before
	public void setUp() {
		 game = new GameImpl(GameMode.AI_CHALLENGE,4);
		 
		 cpu = new CpuImpl(game.numberOfColors(),
				  		   game.getAvailableColors());
		 
		 testAttempt = List.of(Color.RED,Color.GREEN,Color.YELLOW,Color.PURPLE,Color.ORANGE,Color.BLUE);
		 
		 testCode = new CodeImpl(testAttempt);
	}
	
	@Test
	public void testIfContain() {
		game.makeAttempt(testCode);
		cpu.addAttempt(testCode, game.getHints().getLast());
		assertTrue(cpu.ifContain(testAttempt));	
	}
	
	@Test
	public void testInitMatrix() {
		 Integer[] expected = {0, 0, 0, 0, 0, 0};
		 assertArrayEquals(expected, cpu.getMatrix().get(Color.RED));
	}
	
	@Test
	public void testHasSingleValue() {
		Integer[] values = {1, null, null, null,null, null};
		assertTrue(CpuImpl.hasSingleValue(values));
	}
	 
	@Test
	public void testMakeUniqueRandomAttempt() {
		Code attempt = cpu.makeUniqueRandomAttempt();
		assertEquals(game.numberOfColors(), attempt.getColor().size());
	}
	
	@Test
    public void testChooseAttempt() {
		Code attempt = cpu.chooseAttempt();
		assertEquals(game.numberOfColors(), attempt.getColor().size());
    }
	
	@Test
	public void testFindSecretCode() {
		System.out.println("\nSECRET CODE:\t\t" + game.getSecretCode().getColor());
		
		for(int i = 0; i < 20;i++) {
			Code c = cpu.makeUniqueRandomAttempt();
			game.makeAttempt(c);
			cpu.addAttempt(c, game.getHints().getLast());
			System.out.println("\n\nATTEMPT N:"+(i+1));
			cpu.printMatrix();
		}
		
		for(int j = 0;j<1;j++) {
			Code c = cpu.chooseAttempt();	
			assertEquals(c.getColor(),game.getSecretCode().getColor());
			game.makeAttempt(c);
			cpu.addAttempt(c, game.getHints().getLast());
			cpu.printMatrix();
		}
	}
	
		
		
	
	
	}
