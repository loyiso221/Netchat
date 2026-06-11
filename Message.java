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

    private void generateMessageID() {

        Random random = new Random();

        long number = 1000000000L +
                (long) (random.nextDouble() * 9000000000L);

        messageID = String.valueOf(number);
    }

    public boolean checkRecipientCell() {
        return recipient.startsWith("+") && recipient.length() <= 13;
    }

    public String createMessageHash() {

        String[] words = messageText.split(" ");

        String firstWord = words[0].toUpperCase();
        String lastWord = words[words.length - 1].toUpperCase();

        messageHash =
                messageID.substring(0, 2)
                        + ":"
                        + messageNumber
                        + ":"
                        + firstWord
                        + lastWord;

        return messageHash;
    }

    public boolean validMessageLength() {
        return messageText.length() <= 250;
    }

    public String printMessages() {

        return "Message ID: " + messageID +
                "\nMessage Hash: " + messageHash +
                "\nRecipient: " + recipient +
                "\nMessage: " + messageText;
    }

    public static int returnTotalMessages() {
        return totalMessages;
    }

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
