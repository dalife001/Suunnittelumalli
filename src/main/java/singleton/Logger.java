package singleton;

import java.io.FileWriter;
import java.io.IOException;

public class Logger {

    private static Logger instance;
    private FileWriter writer;

    private Logger() {
        try {
            writer = new FileWriter("logger.txt", true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Logger getInstance() { 
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    public void write(String message) {
        try {
            writer.write(message + "\n");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setFileName(String filename) {
        close();
        try {
            writer = new FileWriter(filename, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            if (writer != null) {
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}