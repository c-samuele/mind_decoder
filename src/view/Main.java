package view;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		
		javax.swing.SwingUtilities.invokeLater(() -> { 
		    SessionView view = new SessionView("Mind Decoder", 660, 340);
		    view.show();
		});

		
	}

}
