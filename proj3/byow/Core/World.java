package byow.Core;

import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;
import java.util.Random;

public class World {
    RandomUtils rando = new RandomUtils();
    public World() {

    }

    public void buildRoom(int x, int y, int length, int width, TETile[][] world) {
        for (int start = x; start < x + width; start ++) {
            world[start][y] = Tileset.WALL;
            world[start][y+length] = Tileset.WALL;
        }
        for(int start = y; start < y + length; start ++) {
            world[x][start] = Tileset.WALL;
            world[x+width][start] = Tileset.WALL;
        }
        for (int start = x+1; start < x + (width - 1); start ++) {
            for (int upStart = y+1; upStart < length - 1; upStart ++) {
                world[start][upStart] = Tileset.FLOOR;
            }
        }
    }

    public void hallWay(int x, int y, int length, int width, TETile[][] world) {
        int hallWayWidth = rando.uniform(1, 3);
        buildRoom(x, y, length, width, world);
    }

}
