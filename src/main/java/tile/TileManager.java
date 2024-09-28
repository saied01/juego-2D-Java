package tile;
import Main.gamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class TileManager {

    private BufferedImage spriteSheet;
    gamePanel gp;
    Tile[] tiles;

    public TileManager(gamePanel gp) {

        this.gp = gp;

        tiles = new Tile[10];
        getTileImage();
    }

    public void getTileImage() {

        try {
            spriteSheet = ImageIO.read(getClass().getResourceAsStream("/tiles/tileset_version1.1.png"));


            tiles[0] = new Tile();
            tiles[0].image = getSubImage(140,150,48,48);
            tiles[1] = new Tile();
            tiles[1].image = getSubImage(420,10,48,48);
            tiles[2] = new Tile();
            tiles[2].image = getSubImage(140,10,48,48);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage getSubImage(int x, int y, int width, int height) {
        return spriteSheet.getSubimage(x, y, width, height);
    }

    public void draw(Graphics2D g2) {

        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while (col < gp.maxColScreen && row < gp.maxRowScreen) {
            g2.drawImage(tiles[0].image, x, y,gp.tileSize,gp.tileSize,null);
            col++;
            x += gp.tileSize;

            if (col == gp.maxColScreen) {
                col = 0;
                x = 0;
                row++;
                y += gp.tileSize;
            }
        }


    }
}
