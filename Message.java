import java.util.Random;

public final class Message {

    private static int totalMessages = 0;

    private String messageID;
    private final int messageNumber;
    private final String recipient;
    private final String messageText;
    private String messageHash;

    public Message(int messageNumber, String recipient, String messageText) {

        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.messageText = messageText;

        generateMessageID();
        createMessageHash();

        totalMessages++;
    }

    // Generate random 10 digit ID
    private void generateMessageID() {

        Random random = new Random();

        long number = 1000000000L +
                (long)(random.nextDouble() * 9000000000L);

        messageID = String.valueOf(number);
    }

    // Check message ID length
    public boolean checkMessageID() {
        return messageID.length() <= 10;
    }

    // Check recipient cellphone
    public boolean checkRecipientCell() {

        return recipient.startsWith("+")
                && recipient.length() <= 13;
    }

    // Create message hash
    public String createMessageHash() {

        String[] words = messageText.split(" ");

        String firstWord = words[0].toUpperCase();
        String lastWord = words[words.length - 1].toUpperCase();

        messageHash =
                messageID.substring(0, 2)
                + ":" +
                messageNumber
                + ":" +
                firstWord
                + lastWord;

        return messageHash;
    }

    // Validate message length
    public boolean validMessageLength() {
        return messageText.length() <= 250;
    }

    // Print message
    public String printMessages() {

        return
                "Message ID: " + messageID +
                "\nMessage Hash: " + messageHash +
                "\nRecipient: " + recipient +
                "\nMessage: " + messageText;
    }

    // Return total messages
    public static int returnTotalMessages() {
        return totalMessages;
    }

    // Getters
    public String getMessageID() {
        return messageID;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getMessageText() {
        return messageText;
    }

    public String getMessageHash() {
        return messageHash;
    }
}
