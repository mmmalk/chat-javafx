package net.malkkis.chat.client;

import javafx.collections.ObservableList;
import net.malkkis.chat.Main;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Class to handle client functionality
 * TODO Implement logging
 * @author Mikko Malkavaara
 * @since 2020.2.10.
 */

public class Client {

    private String userName;
    private String address;
    private int port;
    private ChatWindow window;
    private ObservableList log;
    private ClientHandler handler;
    private Socket chatSocket;

    /**
     * Constructor, reads config from Main.getClientConfig() and gets list for messages from
     * ChatWindow.getLog()
     * @param window the ChatWindow we use to draw content
     */
    public Client(ChatWindow window){
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
    public synchronized void connect() throws IOException {
        Runnable connectTask =() -> {
            try {
                this.chatSocket = new Socket(address, port);
                waitForSocket(this.chatSocket);
                handler = new ClientHandler(chatSocket);
                handler.run();

                Message msg = new Message(userName, Message.header.CONNECT);
                new Thread(() -> send(msg)).start();
            } catch(IOException e){
                window.raiseError(e);
            }
        };
        //run the runnable
        new Thread(connectTask).start();

    }

    /**
     * Waits for socket to connect, blocks the connect()
     * TODO parametrize wait time and introduce timeout and max iterations
     * @param socket socket to be waited on
     */

    private synchronized void waitForSocket(Socket socket)  {
        try {
            while (socket == null) {
                wait(250);
            }
        } catch(InterruptedException e){
            window.raiseError(e);
            Thread.currentThread().interrupt();

        }
    }

    /**
     * Sends message to server for broadcasting to other connected clients
     * @param msg Message to be sent
     */
    public void send(Message msg) {
        try {
            handler.os.writeObject(msg);
        } catch(IOException e){
            window.raiseError(e);
        }
    }

    public void close(){
        try {
            Message msg = new Message(userName, Message.header.DISCONNECT);
            send(msg);
            chatSocket.close();
        } catch(IOException e) {
            window.raiseError(e);
        }
    }


    private class ClientHandler implements Runnable {

        private final Socket socket;
        private ObjectInputStream is;
        private ObjectOutputStream os;

        /**
         * @param socket open socket for conection
         * @throws IOException creating ObjectStreams might caue IOEceptions
         */
        public ClientHandler(Socket socket) throws IOException{
            this.socket = socket;
            is = new ObjectInputStream(socket.getInputStream());
            os = new ObjectOutputStream(socket.getOutputStream());
        }

        /**
         * Run method, creates new object streams, reads object from it, casts it to
         * Message and adds it to ObservableList
         * @SEE ChatWindow
         */
        @Override
        public void run() {
            while(socket.isConnected()){
                try {
                    Message msg = null; //we need to null the message for every iteration of loop
                    msg = (Message) is.readObject();
                    if(msg == null){
                        break;
                    } else {
                        //let's just slap this to log, handle it there
                        //TODO make sure to replace this with something better
                        log.add(msg);
                    }
                } catch (Exception e){
                    window.raiseError(e);
                }
            }

        }
    }


}
