package model;

import java.util.List;
import java.util.Map;

public interface Cpu {

	
	public void initMatrix();
	public void printMatrix();
	public void addAttempt(Code attemptCode,Hint hint);
	public Code chooseAttempt();
	public boolean ifContain(List<Color> att);
	public Code makeUniqueRandomAttempt();
	public Map<Color,Integer[]> getMatrix();
	
//	public void addAttemptToMatrix();
//	public Code getBestAttempt();
//	public Code getAttemptForZero();
//	public Code getRandomAttempt();
	
	
}
