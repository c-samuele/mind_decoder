package view;

import java.awt.BorderLayout;

import javax.swing.*;

public class SessionView extends GeneralView {
		
	public SessionView(String title, int width, int height) {
		super(title, width, height);
		frame.setLayout(new BorderLayout()); 
	}

	@Override
	public void initComponents() {
	    JLabel label = new JLabel("Session View");
	    label.setHorizontalAlignment((SwingConstants.CENTER));
		frame.add(label);
		
		
	}

}
