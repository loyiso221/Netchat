import java.io.FileWriter;
import java.io.IOException;

public class MessageStore {

    private static final String FILE_NAME = "messages.json";

    public static void storeMessage(Message msg) {

        String json;
        json = "{\n" +
                "\"MessageID\":\"" + msg.getMessageID() + "\",\n" +
                "\"Recipient\":\"" + msg.getRecipient() + "\",\n" +
                "\"Message\":\"" + msg.getMessageText() + "\",\n" +
                "\"MessageHash\":\"" + msg.getMessageHash() + "\"\n" +
                "}\n";

        try (FileWriter writer = new FileWriter(FILE_NAME, true)) {

            writer.write(json);
            writer.write("\n");

        } catch (IOException e) {
        }
    }
}
