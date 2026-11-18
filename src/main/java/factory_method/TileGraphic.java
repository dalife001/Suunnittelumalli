package factory_method;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

/** Immutable flyweight: shared visual state for every tile of one terrain type. */
public final class TileGraphic {
    private static final Font SYMBOL_FONT = Font.font("System", FontWeight.BOLD, 20);

    private final Color color;
    private final String symbol;
    private final Color symbolColor;

    TileGraphic(Color color, String symbol, Color symbolColor) {
        this.color = color;
        this.symbol = symbol;
        this.symbolColor = symbolColor;
    }

    public void draw(GraphicsContext graphics, double x, double y, double size) {
        graphics.setFill(color);
        graphics.fillRect(x, y, size, size);
        graphics.setStroke(Color.color(0, 0, 0, 0.22));
        graphics.strokeRect(x + 0.5, y + 0.5, size - 1, size - 1);

        graphics.setFill(symbolColor);
        graphics.setFont(SYMBOL_FONT);
        graphics.setTextAlign(TextAlignment.CENTER);
        graphics.fillText(symbol, x + size / 2, y + size * 0.62);
    }
}
