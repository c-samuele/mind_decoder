package Test;

import model.Player;

public class TestModel {

	public static void main(String[] args) {
	
		Player p1 = new Player("Samuele",1);
		Player p2 = new Player("Cristiano",1);
		
		System.out.print(p1.toString());
		System.out.print(p2.toString());

	}

}
