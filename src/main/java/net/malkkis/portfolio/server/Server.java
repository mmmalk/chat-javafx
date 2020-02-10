package net.malkkis.portfolio.server;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Simple implementation of a server for Chat-JavaFX
 * @author Mikko Malkavaara
 * @since 2020.2.10
 */

public class Server {

    protected final int port;
    protected final ExecutorService clientPool = Executors.newFixedThreadPool(500);
    private final HashMap<String, PrintWriter> clients = new HashMap<>();
    private Thread serverThread;

    public Server(int port){

        this.port = port;
    }

    /**
     * Starts the server by opening ServerSocket in it's own thread
     * which launches new Handlers. Returns nothing
     * @returns nothing
     */
    public void start(){

        Runnable serverTask = () -> {
          try{
              ServerSocket listener = new ServerSocket(this.port);
              while(true){
                  Socket clientSocket = listener.accept();
                  clientPool.submit(new Handler(clientSocket));
              }
          } catch (Exception e){
              //Log the exception
          }
        };

        serverThread = new Thread(serverTask);
        serverThread.start();

    }

    public void shutdown(){
        clientPool.shutdown();
    }

    private class Handler implements Runnable {

        private final Socket socket;
        private PrintWriter out;


        public Handler(final Socket socket){
            this.socket = socket;
        }


        @Override
        public void run() {
            try(InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream()){
                Scanner in = new Scanner(is);
                this.out = new PrintWriter(os, true);

                while(true){
                    //unpack the payload here
                }

            } catch(Exception e){

            }

        }
    }


}
