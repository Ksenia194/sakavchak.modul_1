import java.io.*;
import java.util.Objects;

public class FileService {

    public static String readFromFile(File file) {
        StringBuilder content = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException("Помилка при читанні файлу: " + file.getAbsolutePath(), e);
        }
        return content.toString();
    }

    public static void writeToFile(File file, String content, String suffix) {
        String outputPath;
        final String fileName = file.getName();

        if (suffix.equals("[DECRYPTED]") && fileName.contains("[ENCRYPTED]")) {
            outputPath = fileName.replace("[ENCRYPTED]", "[DECRYPTED]");
        } else {
            outputPath = addSuffixToFilePath(fileName, suffix);
        }

        File outputFile = new File(Objects.requireNonNull(file.getParent()), outputPath);

        try {
            if (outputFile.createNewFile()) {
                System.out.println("Новий файл створено: " + outputFile.getAbsolutePath());
            }
        } catch (IOException e) {
            throw new RuntimeException("Помилка при створенні файлу: " + e.getMessage());
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            writer.write(content);
            System.out.println("Записано у файл: " + outputFile.getAbsolutePath());

        } catch (IOException e) {
            throw new RuntimeException("Помилка при записі в файл: " + e.getMessage());
        }
    }

    private static String addSuffixToFilePath(String filePath, String suffix) {
        int dotIndex = filePath.lastIndexOf(".");
        if (dotIndex != -1) {
            return filePath.substring(0, dotIndex) + suffix + filePath.substring(dotIndex);
        } else {
            return filePath + suffix;
        }
    }
}

