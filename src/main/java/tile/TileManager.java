package tile;
import Main.gamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {

    private BufferedImage spriteSheet;
    gamePanel gp;
    Tile[] tiles;
    int mapTileNum[][];

    public TileManager(gamePanel gp) {

        this.gp = gp;

        tiles = new Tile[10];
        mapTileNum = new int[gp.maxColScreen][gp.maxRowScreen];

        getTileImage();
        loadMap();
    }

    public void loadMap() {
        try {
            InputStream is = getClass().getResourceAsStream("/maps/map.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int row = 0;
            int col = 0;

            while (col < gp.maxColScreen && row < gp.maxRowScreen) {
                String line = br.readLine();

                while (col < gp.maxColScreen) {

                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gp.maxColScreen) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getTileImage() {

        try {
            spriteSheet = ImageIO.read(getClass().getResourceAsStream("/tiles/tileset_version1.1.png"));


            tiles[0] = new Tile();
            tiles[0].image = getSubImage(140,150,48,48);
            tiles[1] = new Tile();
            tiles[1].image = getSubImage(420,10,48,48);
            tiles[2] = new Tile();
            tiles[2].image = getSubImage(30,10,48,48);
            tiles[3] = new Tile();
            tiles[3].image = getSubImage(80,10,48,48);

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

            int tileNum = mapTileNum[col][row];

            g2.drawImage(tiles[tileNum].image, x, y,gp.tileSize,gp.tileSize,null);
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
