import java.util.ArrayList;

public class MessageStore {

    public static ArrayList<String> sentMessages = new ArrayList<>();
    public static ArrayList<String> disregardedMessages = new ArrayList<>();
    public static ArrayList<StoredMessage> storedMessages = new ArrayList<>();

    // STORE MESSAGE
    public static void storeMessage(Message msg, String user) {

        String formatted =
                "From: " + user +
                "\nTo: " + msg.getRecipient() +
                "\nMessage: " + msg.getMessageText();

        sentMessages.add(formatted);

        storedMessages.add(
                new StoredMessage(
                        msg.getMessageID(),
                        user,
                        msg.getRecipient(),
                        msg.getMessageText()
                )
        );
    }

    public static void loadMessages() {
        // optional for now
    }

    public static String getLongestMessage() {

        if (storedMessages.isEmpty()) return "No messages found";

        String longest = "";

        for (StoredMessage msg : storedMessages) {
            if (msg.getMessage().length() > longest.length()) {
                longest = msg.getMessage();
            }
        }

        return longest;
    }

    public static StoredMessage searchByID(String id) {

        for (StoredMessage msg : storedMessages) {
            if (msg.getMessageID().equals(id)) {
                return msg;
            }
        }

        return null;
    }

    public static ArrayList<StoredMessage> searchByRecipient(String recipient) {

        ArrayList<StoredMessage> results = new ArrayList<>();

        for (StoredMessage msg : storedMessages) {
            if (msg.getRecipient().equals(recipient)) {
                results.add(msg);
            }
        }

        return results;
    }

    public static boolean deleteByHash(String hash) {

        return storedMessages.removeIf(
                msg -> msg.getMessageID().equals(hash)
        );
    }

    public static String generateReport() {

        return """
               === QUICKCHAT REPORT ===

               Sent Messages: %d
               Stored Messages: %d
               Disregarded Messages: %d
               """.formatted(
                sentMessages.size(),
                storedMessages.size(),
                disregardedMessages.size()
        );
    }
}
