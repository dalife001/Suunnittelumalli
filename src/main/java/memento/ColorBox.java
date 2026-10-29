package memento;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ColorBox {
    private final int id;
    private final Controller controller;
    private final Rectangle rectangle = new Rectangle(100, 100);
    private final Color[] colors = {Color.RED, Color.BLUE, Color.YELLOW};
    private int colorIndex;

    public ColorBox(int id, Controller controller) {
        this.id = id;
        this.controller = controller;
        rectangle.setFill(colors[colorIndex]);
        rectangle.setOnMouseClicked(event -> {
            colorIndex = (colorIndex + 1) % colors.length;
            rectangle.setFill(colors[colorIndex]);
            controller.setOption(id, colorIndex);
        });
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setColor(int colorIndex) {
        if (colorIndex < 0 || colorIndex >= colors.length) {
            throw new IllegalArgumentException("Unknown color index: " + colorIndex);
        }
        this.colorIndex = colorIndex;
        rectangle.setFill(colors[colorIndex]);
    }
}
