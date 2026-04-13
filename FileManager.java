import java.io.*;

public class FileManager {

    private static final String FILE_NAME = "users.txt";

    // SAVE USER
    public static void saveUser(User user) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            bw.write(user.toFileString());
            bw.newLine();
        } catch (IOException e) {
        }
    }

    // VALIDATE LOGIN
    public static boolean validateUser(String username, String password) {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {

            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                if (data[0].equals(username) && data[1].equals(password)) {
                    return true;
                }
            }

        } catch (IOException e) {
        }

        return false;
    }
}