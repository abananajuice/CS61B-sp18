package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import javax.swing.text.Position;
import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {

    private static final int WIDTH = 27;
    private static final int HEIGHT = 30;
    private static final Random RANDOM = new Random(4396);


    public static void TetileIni(TETile[][] world){
        for (int x = 0; x < world.length; x += 1) {
            for (int y = 0; y < world[0].length; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }
    }

    public static void addHexagon(TETile[][] world, int[] p, int sizeHex,TETile t){
//        int HexWidth = sizeHex *3 - 2;
//        int HexHeight = sizeHex * 2;
//        if ((p[0]+HexWidth > world[0].length) || (p[1]+HexHeight > world.length)){
//            throw new RuntimeException("The hex is too large.");
//        }

        int start_pos = sizeHex -1;
        int row_len = sizeHex;

        for (int y = 0; y < sizeHex; y += 1) {
            for (int x = 0; x < row_len; x += 1) {
                world[p[0] + start_pos + x][p[1] + y] = t;
            }
            start_pos -= 1;
            row_len += 2;
        }
        start_pos = 0;
        row_len -= 2;

        for (int y = 0; y < sizeHex; y += 1) {
            for (int x = 0; x < row_len; x += 1) {
                world[p[0] + start_pos + x][p[1] + sizeHex + y] = t;
            }
            start_pos += 1;
            row_len -= 2;
        }
    }

    public static void drawRandomVerticalHexes(TETile[][] world,int p_hori,int N){
        int[] pos = new int[2];
        pos[0] = p_hori;
        int pos_vert = (HEIGHT - N * 6) / 2;
        pos[1] = pos_vert;

        for(int i = 0;i < N;i++){
            TETile t = randomTile();
            addHexagon(world,pos,3,t);
            pos[1] += 6;

        }

    }

    private static TETile randomTile() {
        int tileNum = RANDOM.nextInt(6);
        switch (tileNum) {
            case 0: return Tileset.MOUNTAIN;
            case 1: return Tileset.FLOWER;
            case 2: return Tileset.GRASS;
            case 3: return Tileset.FLOOR;
            case 4: return Tileset.TREE;
            case 5: return Tileset.WATER;
            default: return Tileset.NOTHING;
        }
    }



    public static void main(String[] args){
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);
        int p_hori = 0;
        int[] N = {3,4,5,4,3};


        TETile[][] Tiles = new TETile[WIDTH][HEIGHT];
        TetileIni(Tiles);
        for(int i = 0;i < 5;i++){
            drawRandomVerticalHexes(Tiles,p_hori,N[i]);
            p_hori += 5;
        }


        ter.renderFrame(Tiles);

    }


}
