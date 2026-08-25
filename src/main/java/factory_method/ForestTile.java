package factory_method;

public class ForestTile implements Tile {
    private final char x;
    private final String description;
    private final String type;
    public ForestTile(Character x, String description, String type){
        this.x = x;
        this.description = description;
        this.type = type;
    }

    public char getCharacter() {
        return x;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }
    public void action() {
        System.out.println("gamble");
    }
}