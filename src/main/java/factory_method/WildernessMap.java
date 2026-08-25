package factory_method;

public class WildernessMap extends Map {
    
    public WildernessMap(int width, int height) {
        super(width, height);
    }
    
    @Override
    public Tile createTile() {
        int choice = random.nextInt(3);
        switch (choice) {
            case 0: return new SwampTile('S', "swamp", "swamp");
            case 1: return new WaterTile('W', "water", "water");
            case 2: return new ForestTile('F', " forest", "forest");
            default: return new ForestTile('F', " forest", "forest");
        }
    }
}