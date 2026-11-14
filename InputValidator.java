import java.util.Scanner;
import java.util.function.Predicate;

public class InputValidator {
    private InputValidator() {}
        // Private constructor to prevent instantiation

        public static String read(Scanner scanner, String prompt, Predicate<String> valid, String error) {
            while (true) {
                System.out.print(prompt);
                if (!scanner.hasNextLine()) throw new java.util.NoSuchElementException();
                String value = scanner.nextLine().trim();
                if (valid.test(value)) {
                    return value;
                } else {
                    System.out.println(error);
                }
            }
        }

        public static String required(Scanner scanner, String prompt) {
            return read(scanner, prompt, value -> !value.isEmpty(), "This field is required");
        }

        public static boolean validPlate(String value) {
        return value != null && value.trim().matches("(?i)[a-z0-9]+(?:[ -][a-z0-9]+)*");
    }

        public static String plateKey(String value) {
        return value == null ? "" : value.trim().replaceAll("[ -]", "").toUpperCase(java.util.Locale.ROOT);
    }

        public static boolean validContact(String value) {
        return value != null && value.matches("\\+?[0-9 ()-]+")
                && value.replaceAll("\\D", "").matches("[0-9]{7,15}");
    }

        public static boolean validType(String value) {
        return "Student".equalsIgnoreCase(value) || "Faculty".equalsIgnoreCase(value)
                || "Visitor".equalsIgnoreCase(value);
    }
}