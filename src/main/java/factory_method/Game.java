package factory_method;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

public class Game extends Application {
    private static final int DEFAULT_WIDTH = 20;
    private static final int DEFAULT_HEIGHT = 15;

    public Map createMap(String mapType, int width, int height) {
        if (mapType == null || mapType.isBlank()) {
            throw new IllegalArgumentException("Map type must be city or wilderness.");
        }
        return switch (mapType.trim().toLowerCase().charAt(0)) {
            case 'c' -> new CityMap(width, height);
            case 'w' -> new WildernessMap(width, height);
            default -> throw new IllegalArgumentException("Map type must be city or wilderness.");
        };
    }

    @Override
    public void start(Stage stage) {
        Parameters parameters = getParameters();
        var args = parameters.getRaw();
        String type = args.size() > 0 ? args.get(0) : "wilderness";
        int width = args.size() > 1 ? Integer.parseInt(args.get(1)) : DEFAULT_WIDTH;
        int height = args.size() > 2 ? Integer.parseInt(args.get(2)) : DEFAULT_HEIGHT;

        Map map = createMap(type, width, height);
        TileGraphicFactory graphicFactory = new TileGraphicFactory();
        Canvas canvas = new MapRenderer(graphicFactory).render(map);

        ScrollPane scrollPane = new ScrollPane(canvas);
        scrollPane.setPannable(true);
        stage.setTitle("RPG Map - " + type + " (" + width + "x" + height + ")");
        stage.setScene(new Scene(scrollPane, Math.min(canvas.getWidth() + 20, 1000),
                Math.min(canvas.getHeight() + 20, 800)));
        stage.show();
    }
}
