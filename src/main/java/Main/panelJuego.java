package Main;

import javax.swing.*;
import java.awt.*;

public class panelJuego extends JPanel implements Runnable{

    // SCREEN SETTINGS
    final int originalTileSize = 16; // 16x16 bits
    final int scale = 3; // escalamos los 16 bits a 48 (16*3) porque la resolucion de los monitores es muy grande

    final int tileSize = originalTileSize * scale; // 48x48
    final int maxColScreen = 16;
    final int maxRowScreen = 12;
    final int screenWidth = maxColScreen * tileSize; // 768 pixeles
    final int screenHeight = maxRowScreen * tileSize; // 576 pixeles     esto se puede cambiar en el futuro si se quiere xd

    Thread thread; // es como un timer del juego que hace que el juego no se pare en ningun momento


    public panelJuego(){

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLUE);
        this.setDoubleBuffered(true); // googlear bien que hace. (se que mejora rendimiento de rendering)
    }

    public void startThread() {
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run(){
        // loop del juego
        while (thread != null) {

            //System.out.println("thread running");

            //1 ACTUALIZAR la informacion como la posicion del personaje
            actualizar();

            //2 DIBUJAR la ventana con la informacion actualizada
            repaint(); // llamamos a paintComponent con esto (no se por que)
        }
    }

    public void actualizar() {
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g); // formato para pintar usando JPanel cada vez. super = funcion padre de JPanel

        Graphics2D g2 = (Graphics2D) g; // transformamos a g en graficos 2D

        g2.setColor(Color.WHITE);

        g2.fillRect(220, 220, tileSize, tileSize);
        g2.dispose();
    }
}
