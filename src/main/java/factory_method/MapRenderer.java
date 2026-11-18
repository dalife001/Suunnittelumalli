package factory_method;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

/** Renders a tile grid while reusing cached graphics for each terrain type. */
public final class MapRenderer {
    public static final int TILE_SIZE = 48;

    private final TileGraphicFactory graphicFactory;

    public MapRenderer(TileGraphicFactory graphicFactory) {
        this.graphicFactory = graphicFactory;
    }

    public Canvas render(Map map) {
        Canvas canvas = new Canvas((double) map.getWidth() * TILE_SIZE,
                (double) map.getHeight() * TILE_SIZE);
        GraphicsContext graphics = canvas.getGraphicsContext2D();

        for (int row = 0; row < map.getHeight(); row++) {
            for (int col = 0; col < map.getWidth(); col++) {
                Tile tile = map.getTile(row, col);
                TileGraphic sharedGraphic = graphicFactory.getGraphic(tile.getType());
                sharedGraphic.draw(graphics, (double) col * TILE_SIZE,
                        (double) row * TILE_SIZE, TILE_SIZE);
            }
        }
        return canvas;
    }
}
