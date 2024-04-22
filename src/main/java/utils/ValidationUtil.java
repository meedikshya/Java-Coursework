package utils;

public class ValidationUtil {

    public static boolean isTextOnly(String text) {
        return text != null && text.matches("[a-zA-Z\\s]+");
    }

    public static boolean isNumbersOnly(String text) {
        return text != null && text.matches("\\d+");
    }

    public static boolean isAlphanumeric(String text) {
        return text != null && text.matches("[a-zA-Z0-9]+");
    }

    public static boolean isEmail(String email) {
        return email != null && email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,}$");
    }

    public static boolean hasNoSpecialCharacters(String text) {
        return text != null && text.matches("[a-zA-Z0-9\\s]+");
    }

    public static boolean isValidPassword(String password) {
        return password != null && password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]*$");
    }

    public static boolean hasLength(String text, int length) {
        return text != null && text.length() == length;
    }
    
    public static boolean isGenderMatches(String gender) {
        return gender != null && (gender.equalsIgnoreCase("male") || gender.equalsIgnoreCase("female") || gender.equalsIgnoreCase("others") );
    }
}
