package view;

import java.awt.BorderLayout;

import javax.swing.JFrame;

//
public abstract class GeneralView {
    protected JFrame frame;

    public GeneralView(String title, int width, int height) {
        frame = new JFrame(title);
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout()); 
    }

    public void show() {
        frame.setVisible(true);
    }

    public void hide() {
        frame.setVisible(false);
    }

    public abstract void initComponents(); 
}
