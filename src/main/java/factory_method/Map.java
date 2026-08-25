package factory_method;

import java.util.Random;

public abstract class Map {
    
    protected Tile[][] tiles;
    protected int width;
    protected int height;
    protected Random random;
    
    public Map(int width, int height) {
        this.width = width;
        this.height = height;
        this.random = new Random();
        this.tiles = new Tile[height][width];
        generateMap();
    }
    public abstract Tile createTile();
    
    private void generateMap() {
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                tiles[row][col] = createTile();
            }
        }
    }
    public void display() {
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                System.out.print(tiles[row][col].getCharacter() + " ");
            }
            System.out.println();
        }
    }
}