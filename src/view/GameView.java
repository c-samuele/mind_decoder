package view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

class GameView {
    public GameView(String player1, String player2) {
        JFrame gameFrame = new JFrame("Mind Decoder - Game");
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setSize(800,500);
        gameFrame.setLocationRelativeTo(null);
        gameFrame.setLayout(new BorderLayout());

        JLabel gameLabel = new JLabel("Game Started! Players: " + player1 + (player2 != null ? " & " + player2 : ""), SwingConstants.CENTER);
        gameFrame.add(gameLabel, BorderLayout.CENTER);
        
        gameFrame.setVisible(true);
    }
}