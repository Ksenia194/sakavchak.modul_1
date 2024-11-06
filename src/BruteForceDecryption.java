import java.util.Map;

public class BruteForceDecryption {

    public static String bruteForce(String encryptedText, String language) {
        for (int shift = 1; shift < getAlphabetLength(language); shift++) {
            Map<Character, Character> cipherMap = AlphabetMap.createAlphabetMap(-shift, language);
            StringBuilder decryptedText = new StringBuilder();
            for (char ch: encryptedText.toCharArray()) {
                decryptedText.append(cipherMap.getOrDefault(ch, ch));
            }
            String decryptedString = decryptedText.toString();

            if (isValidDecryption(decryptedString)) {
                System.out.printf("Автоматично знайдено зсув: " + shift);
                return decryptedString;
            }
        }
        throw new RuntimeException("Не вдалось знайти правильний зсув.");
    }

    private static boolean isValidDecryption(String text) {
        int score = 0;

        for (int i = 0; i < text.length() - 2; i++) {
            if (text.charAt(i) == '.' || text.charAt(i) == '!' || text.charAt(i) == '?') {
                if (Character.isUpperCase(text.charAt(i + 2))) {
                    score += 2;
                }
            }
        }

        for (int i = 0; i < text.length() - 1; i++) {
            if (Character.isLetter(text.charAt(i)) && text.charAt(i + 1) == ' ') {
                score += 1;
            } else if (text.charAt(i) == ' ' && Character.isUpperCase(text.charAt(i + 1))) {
                score -= 1;
            }
        }

        for (int i = 0; i < text.length() - 1; i++) {
            char ch = text.charAt(i);
            if ((ch == ',' || ch == '.' || ch == '!' || ch == '?') &&
                    (text.charAt(i + 1) == ' ' || Character.isUpperCase(text.charAt(i + 1)))) {
                score += 2;
            }
        }
        return score > 5;
    }

    private static int getAlphabetLength(String language) {
        switch (language.toUpperCase()) {
            case "ENGLISH":
                return AlphabetMap.TOTAL_LENGTH_ENGLISH;
            case "UKRAINIAN":
                return AlphabetMap.TOTAL_LENGTH_UKRAINIAN;
            default:
                throw new IllegalArgumentException("Невідомий алфавіт. Виберіть 'ENGLISH' або 'UKRAINIAN'.");
        }
    }
}

