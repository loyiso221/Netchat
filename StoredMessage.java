public class StoredMessage {

    private final String messageID;
    private final String sender;
    private final String recipient;
    private final String message;

    public StoredMessage(String messageID,
                         String sender,
                         String recipient,
                         String message) {

        this.messageID = messageID;
        this.sender = sender;
        this.recipient = recipient;
        this.message = message;
    }

    public String getMessageID() {
        return messageID;
    }

    public String getSender() {
        return sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "ID: " + messageID +
                "\nSender: " + sender +
                "\nRecipient: " + recipient +
                "\nMessage: " + message;
    }
}