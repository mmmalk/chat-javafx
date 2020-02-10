package net.malkkis.portfolio;

import javafx.application.Application;
import javafx.stage.Stage;

import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main extends Application {

    public static void main(String[] args){
        Application.launch(Main.class);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Handler h = new FileHandler("lastrun.log");
        Logger logger = Logger.getLogger("");
        logger.addHandler(h);

        logger.log(Level.INFO, "Application is starting");

    }

}
