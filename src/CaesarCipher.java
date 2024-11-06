import java.util.HashMap;
import java.util.Map;

public class CaesarCipher {

    public static String processText(String text, int shift, Command command, String language) {
        StringBuilder result = new StringBuilder();

        Map<Character, Character> cipherMap = AlphabetMap.createAlphabetMap(shift, language);
        Map<Character, Character> decryptMap = AlphabetMap.createReverseMap(cipherMap);

        switch (command) {
            case ENCRYPT -> {
                for (char ch : text.toCharArray()) {
                    result.append(cipherMap.getOrDefault(ch, ch));
                }
                break;
            }
            case DECRYPT -> {
                for (char ch : text.toCharArray()) {
                    result.append(decryptMap.getOrDefault(ch, ch));
                }
                break;
            }
            case BRUTE_FORCE -> {
                result.append(BruteForceDecryption.bruteForce(text, language));
                break;
            }
            default -> throw new RuntimeException("Невірна команда, використовуйте ENCRYPT, DECRYPT чи BRUTE_FORCE");
        }
        return result.toString();
    }
}

