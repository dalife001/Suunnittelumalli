package factory_method;

import javafx.scene.paint.Color;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/** Creates and caches one immutable graphic flyweight per terrain type. */
public final class TileGraphicFactory {
    private final Map<String, TileGraphic> graphics = new HashMap<>();

    public TileGraphic getGraphic(String tileType) {
        if (tileType == null || tileType.isBlank()) {
            throw new IllegalArgumentException("Tile type must not be empty.");
        }
        return graphics.computeIfAbsent(tileType.toLowerCase(), this::createGraphic);
    }

    public int getGraphicCount() {
        return graphics.size();
    }

    public Map<String, TileGraphic> getGraphics() {
        return Collections.unmodifiableMap(graphics);
    }

    private TileGraphic createGraphic(String type) {
        return switch (type) {
            case "road" -> new TileGraphic(Color.SANDYBROWN, "╱", Color.SADDLEBROWN);
            case "forest" -> new TileGraphic(Color.FORESTGREEN, "♣", Color.DARKGREEN);
            case "building" -> new TileGraphic(Color.SLATEGRAY, "⌂", Color.WHITE);
            case "swamp" -> new TileGraphic(Color.DARKOLIVEGREEN, "≈", Color.BEIGE);
            case "water" -> new TileGraphic(Color.ROYALBLUE, "~", Color.LIGHTCYAN);
            default -> throw new IllegalArgumentException("Unknown tile type: " + type);
        };
    }
}
