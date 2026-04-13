public class VALIDATION {

    // USERNAME
    public static boolean checkUsername(String username) {
        return username.contains("_") && username.length() <= 5;
    }

    public static String usernameMessage(String username) {
        if (checkUsername(username)) {
            return "Username successfully captured.";
        } else {
            return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        }
    }

    // PASSWORD
    public static boolean checkPassword(String password) {
        String regex = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[^a-zA-Z0-9]).{8,}$";
        return password.matches(regex);
    }

    public static String passwordMessage(String password) {
        if (checkPassword(password)) {
            return "Password successfully captured.";
        } else {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }
    }

    // CELLPHONE (+27XXXXXXXXX)
    public static boolean checkCellphone(String phone) {
        String regex = "^\\+27[0-9]{9}$";
        return phone.matches(regex);
    }

    public static String cellphoneMessage(String phone) {
        if (checkCellphone(phone)) {
            return "Cell phone number successfully added.";
        } else {
            return "Cell phone number incorrectly formatted or does not contain international code.";
        }
    }

    // LOGIN MESSAGE
    public static String loginMessage(boolean success, String username) {
        if (success) {
            return "Welcome " + username + ", it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
}