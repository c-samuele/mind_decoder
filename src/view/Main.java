package view;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		SessionView session = new SessionView("Master Mind",900,600);
			session.initComponents();
			session.show();
		
	}

}
