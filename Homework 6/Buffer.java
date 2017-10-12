package edu.sjsu.cs249.five;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Observable Buffer of each node
 *
 * @author Sample
 * @version 1.0
 */
//A channel should have a buffer associated with it.
public class Buffer {
    String label;
    private Queue<Message> messages;

    /**
     * Creates empty buffer
     */
    public Buffer() {
        this.messages = new LinkedList<>();
    }

    /**
     * Creates empty buffer
     */
    public Buffer(String label) {
        messages = new LinkedList<>();
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    /**
     * @return Message from the buffer
     */
    public Message getMessage() {
        return messages.remove();
    }

    /**
     * Sets the message and notifies the observers with the sender node's information
     *
     * @param message Message to be stored in the buffer
     */
    public void saveMessage(Message message) {
        this.messages.add(message);
        
    }

    int getTotalMessageCount() {
        return messages.size();
    }
}

