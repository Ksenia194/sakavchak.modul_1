import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CLI {

    private static CLI instanse;

    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    private CLI(){
    }

    public static CLI getInstanse() {
        if (instanse == null) {
            instanse = new CLI();
        }
        return instanse;
    }


    public String getUserInput(final String message) {
        System.out.println(message);
        try {
            return reader.readLine();
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }
}
