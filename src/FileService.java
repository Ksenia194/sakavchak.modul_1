import javax.imageio.IIOException;
import java.io.*;

public class FileService {

    public static String readFromFile(File file) {
        StringBuilder content = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException("Помилка при читанні файлу" + file.getAbsolutePath(), e);
        }
        return content.toString();
    }

    public  static void writeToFile(File file, String content, String suffix) {
        String outputPath;
        final String fileName = file.getName();

        if (suffix.equals("[DECRYPTED]") && fileName.contains("[ENCRYPTED")) {
            outputPath = fileName.replace("[ENCRYPTED]", "[DECRYPTED]");
        } else {
            outputPath = addSuffixToFilePath(fileName, suffix);
        }

        File outputFile = new File(outputPath);
        if (!outputFile.exists()) {
            try {
                if (outputFile.createNewFile()) {
                    System.out.println("Новий файл створено: " + outputPath);
                } else {
                    System.out.println("Не вдалося створити файл.");
                }
            } catch (IOException e) {
                throw new RuntimeException("Помилка при створенні файлу: " + e.getMessage());
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath))) {
            writer.write(content);
            System.out.println("Записано у файл: " + outputPath);

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
