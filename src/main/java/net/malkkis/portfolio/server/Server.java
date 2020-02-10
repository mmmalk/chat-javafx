package net.malkkis.portfolio.server;

import net.malkkis.portfolio.Message;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
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
    protected final HashMap<String, ObjectOutputStream> clients = new HashMap<>();
    private Thread serverThread;

    public Server(int port){

        this.port = port;
    }

    /**
     * Starts the server by opening ServerSocket in it's own thread
     * which launches new Handlers. Returns nothing
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

    /**
     * broadcasts a message to every client currently connected to the server
     * @param o the object to be sent (Serializable)
     */
    public void broadcast(Serializable o){
        clients.forEach((name, oos) -> {
            try{
                oos.writeObject(o);
            } catch(Exception e){

            }
        });

    }

    /**
     * Broadcasts a message to every client sans the original sender
     * @param o Serializable object to be sent
     * @param sender the sender's ID
     */

    public void broadcast(Serializable o, String sender) {

        for (Map.Entry<String, ObjectOutputStream> item : clients.entrySet()) {
            String key = item.getKey();
            ObjectOutputStream out = item.getValue();
            if (sender.equals(key)) {
                break;
            } else {
                try {
                    out.writeObject(o);
                } catch (Exception e) {

                }
            }
        }
    }

    private class Handler implements Runnable {

        private final Socket socket;
        private PrintWriter out;


        public Handler(final Socket socket){
            this.socket = socket;
        }


        @Override
        public void run() {
            try(ObjectInputStream is = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream())){
                Scanner in = new Scanner(is);
                while(true){
                    //unpack the payload here
                }

            } catch(Exception e){

            }

        }
    }


}
