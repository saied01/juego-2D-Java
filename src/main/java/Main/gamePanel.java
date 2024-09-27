package Main;

import entity.Player;

import javax.swing.*;
import java.awt.*;

public class gamePanel extends JPanel implements Runnable{

    // SCREEN SETTINGS
    final int originalTileSize = 16; // 16x16 bits
    final int scale = 3; // escalamos los 16 bits a 48 (16*3) porque la resolucion de los monitores es muy grande

    public final int tileSize = originalTileSize * scale; // 48x48
    final int maxColScreen = 16;
    final int maxRowScreen = 12;
    final int screenWidth = maxColScreen * tileSize; // 768 pixeles
    final int screenHeight = maxRowScreen * tileSize; // 576 pixeles     esto se puede cambiar en el futuro si se quiere xd

    // FPS
    int fps = 60;

    Thread thread; // es como un timer del juego que hace que el juego no se pare en ningun momento
    keyHandler keyH = new keyHandler();
    Player player = new Player(this, keyH);

    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 3;


    public gamePanel(){

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLUE);
        this.setDoubleBuffered(true); // googlear bien que hace. (mejora rendimiento de rendering)
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startThread() {
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run(){

        double drawInterval = 1000000000 /fps;
        double nextDrawTime = System.nanoTime() + drawInterval;

        // loop del juego
        while (thread != null) {

            //1 ACTUALIZAR la informacion como la posicion del personaje
            update();

            //2 DIBUJAR la ventana con la informacion actualizada
            repaint(); // llamamos a paintComponent con esto (no se por que)

            try {
                double remainingDrawTime = nextDrawTime - System.nanoTime();
                remainingDrawTime /= 1000000;

                if (remainingDrawTime < 0) {
                    remainingDrawTime = 0;
                }
                Thread.sleep((long) remainingDrawTime);         // Lo que hace es "dormir" al juego mientras todavia queda remainingTime
                                                                // (por eso si nos pasamos de 0 lo seteamos en 0)
                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void update() {
        player.update();
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g); // formato para pintar usando JPanel cada vez. super = funcion padre de JPanel

        Graphics2D g2 = (Graphics2D) g; // transformamos a g en graficos 2D

        player.draw(g2);

        g2.dispose();
    }
}
