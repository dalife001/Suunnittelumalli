package factory_method;

import java.util.Random;

public abstract class Map {
    protected final Tile[][] tiles;
    protected final int width;
    protected final int height;
    protected final Random random;

    public Map(int width, int height) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Map width and height must be positive.");
        }
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

    public Tile getTile(int row, int col) {
        return tiles[row][col];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
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
