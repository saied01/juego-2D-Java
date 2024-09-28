package entity;

import Main.gamePanel;
import Main.keyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {

    gamePanel gp;
    keyHandler keyH;

    public Player(gamePanel gp, keyHandler keyH) {

        this.gp = gp;
        this.keyH = keyH;
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 3;
        direction = "down";
    }

    public void getPlayerImage() {

        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/amg1_bk1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/amg1_bk2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/amg1_fr1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/amg1_fr2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/amg1_rt1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/amg1_rt2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/amg1_lf1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/amg1_lf2.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {

        if (keyH.upPress || keyH.downPress || keyH.leftPress || keyH.rightPress) {
            if (keyH.upPress) {
                direction = "up";
                y -= speed;
            }
            if (keyH.downPress) {
                direction = "down";
                y += speed;
            }
            if (keyH.leftPress) {
                direction = "left";
                x -= speed;
            }
            if (keyH.rightPress) {
                direction = "right";
                x += speed;
            }

            spriteCounter++;
            if (spriteCounter > 12) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }

    public void draw(Graphics2D g2) {
        //g2.setColor(Color.WHITE);
        //g2.fillRect(x, y, gp.tileSize, gp.tileSize);

        BufferedImage image = null;

        switch (direction) {
            case "up":
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
                break;
            case "down":
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
                break;
        }

        g2.drawImage(image, x, y,gp.tileSize, gp.tileSize, null);
    }
}
