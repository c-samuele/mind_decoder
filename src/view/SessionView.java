package view;

import javax.swing.*;
import java.awt.*;

public class SessionView extends GeneralView {

    private JLabel statsLabel;
    private JPanel levelPanel;
    private JButton startButton;
    private JButton exitButton;

    public SessionView(String title, int width, int height) {
        super(title, width, height);
        frame.setLayout(new BorderLayout());  // LAYOUT
        frame.setResizable(false);							// blocco il ridimensionamento
        initComponents();
    }

    @Override
    public void initComponents() {
        // TOP - Statistiche giocatore
        statsLabel = new JLabel("Giocatore:  | Livello:  | Punteggio: ", SwingConstants.CENTER);
        statsLabel.setFont(new Font("Arial", Font.BOLD, 16));
        statsLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0)); // GAP TOP

        frame.add(statsLabel, BorderLayout.NORTH);

        // CENTER - Griglia livelli
        levelPanel = new JPanel(new GridLayout(2, 3, 10, 10)); // 2 righe x 3 colonne di livelli
        for (int i = 1; i <= 12; i++) {
            JButton levelBtn = new JButton("Livello " + i);
            levelPanel.add(levelBtn);
        }
        levelPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        frame.add(levelPanel, BorderLayout.CENTER);

        // BOTTOM - Pulsanti
        JPanel bottomPanel = new JPanel(new FlowLayout());
        startButton = new JButton("Avvia Partita");
        exitButton = new JButton("Esci");
        bottomPanel.add(startButton);
        bottomPanel.add(exitButton);
        frame.add(bottomPanel, BorderLayout.SOUTH);
    }

    // Getter per collegare eventi
    public JButton getStartButton() { return startButton; }
    public JButton getExitButton() { return exitButton; }
}
