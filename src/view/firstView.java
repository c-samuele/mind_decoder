package view;

import javax.swing.*;
import java.awt.*;

public class firstView extends GeneralView {

    private JTextField nameField;
    private JButton newGameButton;
    private JButton loadGameButton;

    public firstView(String title, int width, int height) {
        super(title, width, height);
        frame.setLayout(new GridLayout());
        initComponents();
    }

    @Override
    public void initComponents() {
    	
        JLabel titleLabel = new JLabel("Mind Decoder", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));	
        frame.setResizable(false);							// blocco il ridimensionamento

        nameField = new JTextField();
        nameField.setPreferredSize(new Dimension(200, 700));
        newGameButton = new JButton("Nuova Partita");
        loadGameButton = new JButton("Carica Partita");

        JPanel centerPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(110, 40, 110, 40));
        centerPanel.add(nameField);
        centerPanel.add(newGameButton);
        centerPanel.add(loadGameButton);
        	
        frame.add(titleLabel);
        frame.add(centerPanel);
     
    }

  
    public String getPlayerName() {
        return nameField.getText().trim();
    }

    public JButton getNewGameButton() {
        return newGameButton;
    }

    public JButton getLoadGameButton() {
        return loadGameButton;
    }
}
