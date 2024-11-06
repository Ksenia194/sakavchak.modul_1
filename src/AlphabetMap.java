import java.util.HashMap;
import java.util.Map;

public class AlphabetMap {

    public static final String ENGLISH_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz" +
            "., «»'\\:!? ";
    public static final String UKRAINIAN_ALPHABET = "АБВГҐДЕЄЖЗИІЇЙКЛМНОПРСТУФХЦЧШЩЬЮЯ" +
            "абвгґдеєжзиіїйклмнопрстуфхцчшщьюя" +
            "., «»'\\:!? ";

    // Загальний розмір алфавітів
    public static final int TOTAL_LENGTH_ENGLISH = ENGLISH_ALPHABET.length();
    public static final int TOTAL_LENGTH_UKRAINIAN = UKRAINIAN_ALPHABET.length();

    public static Map<Character, Character> createAlphabetMap(int shift, String language) {
        String alphabet = getAlphabet(language);
        int totalLength = alphabet.length();

        Map<Character, Character> map = new HashMap<>();
        int normalize = (shift % totalLength + totalLength) % totalLength;

        for (int i = 0; i < totalLength; i++) {
            char currentChar = alphabet.charAt(i);
            char shiftedChar = alphabet.charAt((i + normalize) % totalLength);
            map.put(currentChar, shiftedChar);
        }
        return map;
    }

    private static String getAlphabet(String language) {
        switch (language.toUpperCase()) {
            case "ENGLISH":
                return ENGLISH_ALPHABET;
            case "UKRAINIAN":
                return UKRAINIAN_ALPHABET;
            default:
                throw new IllegalArgumentException("Невідомий алфавіт. Виберіть 'ENGLISH' або 'UKRAINIAN'.");
        }
    }

    public static Map<Character, Character> createReverseMap(Map<Character, Character> originalMap) {
        Map<Character, Character> reverseMap = new HashMap<>();
        for (Map.Entry<Character, Character> entry : originalMap.entrySet()) {
            reverseMap.put(entry.getValue(), entry.getKey());
        }
        return reverseMap;
    }
}

