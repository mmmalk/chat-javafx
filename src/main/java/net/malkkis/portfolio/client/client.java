package net.malkkis.portfolio.client;

import javafx.collections.ObservableList;
import net.malkkis.portfolio.Main;
import net.malkkis.portfolio.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Class to handle client functionality
 */

public class client {

    private String userName;
    private String address;
    private int port;
    private ChatWindow window;
    private ObservableList log;
    private ClientHandler handler;

    /**
     * Constructor, reads config from Main.getClientConfig() and gets list for messages from
     * ChatWindow.getLog()
     * @param window the ChatWindow we use to draw content
     */
    public client(ChatWindow window){
        String[] config = Main.getClientConfig();
        this.userName = config[0];
        this.address = config[1];
        this.window = window;
        this.log = window.getLog();
        this.port = 666;
    }

    /**
     * Connect to chat server
     * @throws IOException - failed to create socket at specified address
     */
    public void connect() throws IOException {
        Socket chatSocket = new Socket(address, port);
        handler = new ClientHandler(chatSocket);
        handler.run();
    }

    /**
     * Sends message to server for broadcasting to other connected clients
     * @param msg Message to be sent
     */
    public void send(Message msg) throws IOException {
        handler.os.writeObject(msg);
    }

    public void close(){
        //how to close down a client
        //send disconnect message to the server
        //close streams and make sure thread closes down
        //close the window 
    }




    private class ClientHandler implements Runnable{

        private final Socket socket;
        private ObjectInputStream is;
        private ObjectOutputStream os;

        public ClientHandler(Socket socket){
            this.socket = socket;

        }

        /**
         * Run method, creates new object streams, reads object from it, casts it to
         * Message and adds it to ObservableList
         */
        @Override
        public void run() {
            try {
                is = new ObjectInputStream(socket.getInputStream());
                os = new ObjectOutputStream(socket.getOutputStream());
            } catch (IOException e) {
                window.raiseError(e);
                e.printStackTrace();
            }
        }
    }


}
