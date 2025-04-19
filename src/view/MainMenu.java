package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

public class MainMenu {

	 public static void main(String[] args) {
	        JFrame frame = new JFrame("Mind Decoder - Main Menu");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setSize(800, 500);
	        frame.setLocationRelativeTo(null);
	        frame.setLayout(new GridLayout(4, 1));

	        JLabel titleLabel = new JLabel("Mind Decoder", SwingConstants.CENTER);
	        JButton singlePlayerButton = new JButton("Single Player");
	        JButton multiPlayerButton = new JButton("Multiplayer");
	        JButton exitButton = new JButton("Exit");

	        singlePlayerButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                String playerName = JOptionPane.showInputDialog(frame, "Enter your name:");
	                if (playerName != null && !playerName.trim().isEmpty()) {
	                    JOptionPane.showMessageDialog(frame, "Welcome, " + playerName + " Starting game...");
	                    frame.dispose();
	                    new GameView(playerName, null); // Avvia la partita in single player
	                }
	            }
	        });

	        multiPlayerButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                String player1Name = JOptionPane.showInputDialog(frame, "Enter Player 1 name:");
	                String player2Name = JOptionPane.showInputDialog(frame, "Enter Player 2 name:");
	                if (player1Name != null && !player1Name.trim().isEmpty() &&
	                    player2Name != null && !player2Name.trim().isEmpty()) {
	                    JOptionPane.showMessageDialog(frame, "Welcome, " + player1Name + " and " + player2Name + " Starting game...");
	                    frame.dispose();
	                    new GameView(player1Name, player2Name); // Avvia la partita in multiplayer
	                }
	            }
	        });

	        exitButton.addActionListener(e -> System.exit(0));

	        frame.add(titleLabel);
	        frame.add(singlePlayerButton);
	        frame.add(multiPlayerButton);
	        frame.add(exitButton);
	        frame.setVisible(true);
	    }
	}
