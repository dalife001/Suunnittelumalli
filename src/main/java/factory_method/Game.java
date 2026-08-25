package factory_method;

public class Game {
    
    public Map createMap(String mapType, int width, int height) {
        char first = mapType.trim().toLowerCase().charAt(0);
        
        if (first == 'c') {
            return new CityMap(width, height);
        } 
        else if (first == 'w') {
            return new WildernessMap(width, height);
        }
        return null;
    }
}