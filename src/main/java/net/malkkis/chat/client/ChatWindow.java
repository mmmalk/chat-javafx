package net.malkkis.chat.client;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import javax.swing.event.ChangeListener;

public class ChatWindow {
    private final ObservableList<Message> log = FXCollections.observableArrayList();

    public ChatWindow(){
        log.addListener(new listChangeListener());
    }

    /**
     * @see Client
     * @return log - ObservableArrayList containing our message queue
     */
    public ObservableList<Message> getLog() {
        return log;
    }

    /**
     * Method used to raise an error dialogue to user
     * @param exception Exception that causes our program stop running
     * @see Client
     */
    public void raiseError(Exception exception) {
        //TODO open dialogue for error, log inside calling method
    }

    /**
     * ChangeListener for showing our chat messages - client.java reads
     * objects to ObservableList, and ChangeListener reads it synchronized
     * and passes it to draw method
     * @see Client
     * @see ChangeListener
     */
    private class listChangeListener implements ListChangeListener {

        @Override
        public void onChanged(Change change) {
            //TODO Implement actual listener
            //TODO check if can be replaced with lambda
        }
    }
}
