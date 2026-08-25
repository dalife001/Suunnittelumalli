package factory_method;

import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
        Game game = new Game();
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("factory\n");
        
        System.out.print("Choose map type (city/wilderness): ");
        String mapType = scanner.nextLine().trim();
        
        System.out.print("Enter map width: ");
        int width = scanner.nextInt();
        
        System.out.print("Enter map height: ");
        int height = scanner.nextInt();
        
        System.out.println(mapType.toUpperCase() + " Map (" + width + "x" + height + "):");
        
        
        Map map = game.createMap(mapType, width, height);
        map.display();
        scanner.close();
    }
}