package test;
import model.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;

public class TestCpu {

	private Cpu cpu;
	private int length;
	private Color[] availableColors;
	
	@Before
	public void setUp() {
		length = 3;
		availableColors = new Color[] { Color.RED, Color.BLUE, Color.GREEN};
		
	}
	
	@Test
	public void testInitMatrix() {
		cpu = new CpuImpl(length,availableColors);
		cpu.printMatrix();
	}

	
	


}
