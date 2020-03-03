package net.malkkis.chat.client;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;

import javax.swing.event.ChangeListener;
import java.io.PrintWriter;
import java.io.StringWriter;

public class ChatWindow {
    private final ObservableList<Message> log = FXCollections.observableArrayList();

    public ChatWindow(){
        log.addListener(new MessageListChangeListener());
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
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        exception.printStackTrace(printWriter);
        String stackTrace = stringWriter.toString();

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Runtime exception");
        alert.setHeaderText(exception.getMessage());

        Label label = new Label("Stacktrace:");

        TextArea textArea = new TextArea(stackTrace);
        textArea.setWrapText(true);
        textArea.setEditable(false);
        textArea.maxWidth(Double.MAX_VALUE);
        textArea.maxHeight(Double.MAX_VALUE);

        GridPane gridPane = new GridPane();
        gridPane.add(label, 0, 0); //label, column 0, row 0
        gridPane.add(textArea, 0, 1); //textArea, column 0, row 1

        alert.getDialogPane().setExpandableContent(textArea);
        alert.showAndWait();
    }

    /**
     * ChangeListener for showing our chat messages - client.java reads
     * objects to ObservableList, and ChangeListener reads it synchronized
     * and passes it to draw method
     * @see Client
     * @see ChangeListener
     */
    private class MessageListChangeListener implements ListChangeListener {

        @Override
        public void onChanged(Change change) {
            //TODO Implement actual listener
            //TODO check if can be replaced with lambda
        }
    }
}
