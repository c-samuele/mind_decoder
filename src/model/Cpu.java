package model;

import java.util.List;

public interface Cpu {

	
	public void initMatrix(List<Color> availableColors);
	public void printMatrix();
	public void addAttempt(Code attemptCode,Hint hint);
	public Code chooseAttempt();
	public boolean ifContain(List<Color> att);
	public Code makeUniqueRandomAttempt();
	
	
//	public void addAttemptToMatrix();
//	public Code getBestAttempt();
//	public Code getAttemptForZero();
//	public Code getRandomAttempt();
	
	
}
