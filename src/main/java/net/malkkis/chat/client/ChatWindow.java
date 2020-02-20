package net.malkkis.chat.client;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import net.malkkis.chat.Message;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ChatWindow {
    private final ObservableList<Message> log = FXCollections.observableArrayList();

    //called in client.java
    public ObservableList<Message> getLog() {
        return log;
    }

    /**
     * Method used to raise an error dialogue to user
     * @param exception Exception that causes our program stop running
     */
    public void raiseError(Exception exception) {
    }

    /**
     * ChangeListener for showing our chat messages - client.java reads
     * objects to ObservableList, and ChangeListener reads it synchronized
     * and passes it to draw method
     */
    private class listChangeListener implements ChangeListener {

        @Override
        public void stateChanged(ChangeEvent e) {

        }
    }
}
