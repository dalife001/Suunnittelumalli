package factory_method;

public class CityMap extends Map {
    
    public CityMap(int width, int height) {
        super(width, height);
    }
    
    @Override
    public Tile createTile() {
        int choice = random.nextInt(3);
        switch (choice) {
            case 0: return new RoadTile('R', "road", "road");
            case 1: return new ForestTile('F', " forest", "forest");
            case 2: return new BuildingTile('B', "building", "building");
            default: return new RoadTile('R', " road", "road");
        }
    }
}