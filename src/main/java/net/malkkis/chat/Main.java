package net.malkkis.chat;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main extends Application {

    private static String userName;
    private static String address;

    public static void main(String[] args){
        Application.launch(Main.class);
    }

    public static void setClientConfig(String name, String addr) {
        userName = name;
        address = addr;
    }

    public static String[] getClientConfig(){
        String[] r = {userName, address};
        return r;
    }



    @Override
    public void start(Stage stage) throws Exception {
        Handler h = new FileHandler("lastrun.log");
        Logger logger = Logger.getLogger("");
        logger.addHandler(h);

        logger.log(Level.INFO, "Application is starting");

        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("start.fxml"));

            //Just load the scene, set the stage and go
            Parent root = loader.load();
            Scene scene = new Scene(root);
            //put HostServices in properties
            stage.getProperties().put("hostServices", this.getHostServices());
            stage.setTitle("Chat-JavaFX");
            stage.setScene(scene);
            stage.show();

        } catch(Exception e){
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            logger.log(Level.SEVERE, sw.toString());
        }


    }
}
