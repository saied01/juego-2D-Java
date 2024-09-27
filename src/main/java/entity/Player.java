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
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/walk up1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/walk up2.png"));
            up3 = ImageIO.read(getClass().getResourceAsStream("/player/walk up3.png"));
            up4 = ImageIO.read(getClass().getResourceAsStream("/player/walk up4.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/walk down1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/walk down2.png"));
            down3 = ImageIO.read(getClass().getResourceAsStream("/player/walk down3.png"));
            down4 = ImageIO.read(getClass().getResourceAsStream("/player/walk down4.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/walk left1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/walk left2.png"));
            left3 = ImageIO.read(getClass().getResourceAsStream("/player/walk left3.png"));
            left4 = ImageIO.read(getClass().getResourceAsStream("/player/walk left4.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/walk right1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/walk right2.png"));
            right3 = ImageIO.read(getClass().getResourceAsStream("/player/walk right3.png"));
            right4 = ImageIO.read(getClass().getResourceAsStream("/player/walk right4.png"));

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
            if (spriteCounter > 20) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 3;
                } else if (spriteNum == 3) {
                    spriteNum = 4;
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
                if (spriteNum == 3) {
                    image = up3;
                }
                if (spriteNum == 4) {
                    image = up4;
                }
                break;
            case "down":
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
                if (spriteNum == 3) {
                    image = down3;
                }
                if (spriteNum == 4) {
                    image = down4;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
                if (spriteNum == 3) {
                    image = left3;
                }
                if (spriteNum == 4) {
                    image = left4;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
                if (spriteNum == 3) {
                    image = right3;
                }
                if (spriteNum == 4) {
                    image = right4;
                }
                break;
        }

        g2.drawImage(image, x, y,gp.tileSize, gp.tileSize, null);
    }
}
