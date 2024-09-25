package Main;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        JFrame ventana = new JFrame();
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setResizable(false);
        ventana.setTitle("jugo 2D");

        gamePanel gamePanel = new gamePanel();
        ventana.add(gamePanel);

        ventana.pack(); // hace que la ventana se dimensione para ajustarse a las dimensiones que le pusimos al panel.

        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);

        gamePanel.startThread();
    }
}
