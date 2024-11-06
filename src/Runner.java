import java.io.File;

public class Runner {

    private static Runner instance;

    private Runner() {
    }

    public static Runner getInstance() {
        if (instance == null) {
            instance = new Runner();
        }
        return instance;
    }

    public void run(final String[] commands) {
        final Command command = checkCommand(commands[0]);
        final File file = checkFilePath(commands[1]);
        final int shift = checkShift(command, commands[2]);
        final String language = checkLanguage(commands.length > 3 ? commands[3] : "ENGLISH");
        final String text = FileService.readFromFile(file);

        switch (command) {
            case ENCRYPT:
                String encryptedText = CaesarCipher.processText(text, shift, Command.ENCRYPT, language);
                FileService.writeToFile(file, encryptedText, "[ENCRYPTED]");
                System.out.printf("Зашифрований текст: " + encryptedText);
                break;

            case DECRYPT:
                String decryptedText = CaesarCipher.processText(text, shift, Command.DECRYPT, language);
                FileService.writeToFile(file, decryptedText, "[DECRYPTED]");
                System.out.printf("Розшифрований текст: " + decryptedText);
                break;

            case BRUTE_FORCE:
                String bruteForceText = CaesarCipher.processText(text, shift, Command.BRUTE_FORCE, language);
                FileService.writeToFile(file, bruteForceText, "[DECRYPTED]");
                System.out.printf("Розшифрований текст (Brute Force): " + bruteForceText);
                break;

            default:
                throw new IllegalArgumentException("Невірна команда, використовуйте ENCRYPT, DECRYPT чи BRUTE_FORCE");
        }

    }

    private Command checkCommand(final String command) {
        try {
            return Command.valueOf(command);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Невідома команда: " + command + ". Будь ласка, використовуйте одну з доступних команд.");
        }
    }

    private File checkFilePath(final String filePath) {
        final File file = new File(filePath);

        if (!file.exists()) {
            throw new IllegalArgumentException("Файл не знайдено");
        }
        return file;
    }

    private int checkShift(final Command command, final String shift) {
        try {
            return command == Command.BRUTE_FORCE ? 0 : Integer.parseInt(shift);
        } catch (NumberFormatException e) {
            throw new RuntimeException("Невірний формат ключа. Ключ для шифрування має бути цілим числом. " +
                    "Будь ласка, введіть правильне значення.");
        }
    }

    private String checkLanguage(final String languages) {
        if (languages.length() > 3) {
            String language = languages.toUpperCase();
            if (language.equals("ENGLISH") || language.equals("UKRAINIAN")) {
                return language;
            } else {
                throw new RuntimeException("Невідома мова. Використовуйте 'ENGLISH' або 'UKRAINIAN'.");
            }
        }
            return "ENGLISH";
    }

    public void runCli() {
        final CLI instance = CLI.getInstanse();
        final Command command = checkCommand(instance.getUserInput("Напишіть вашу команду:"));
        final File file = checkFilePath(instance.getUserInput("Напишіть шлях до файлу:"));
        final int shift = checkShift(command, instance.getUserInput("Напишіть зсув:"));
        final String language = checkLanguage(instance.getUserInput("Напишіть мову (ENGLISH/UKRAINIAN):"));
        final String text = FileService.readFromFile(file);

    }
}
