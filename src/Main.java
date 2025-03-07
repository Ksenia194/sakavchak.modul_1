public class Main {
    public static void main(String[] args) {
        final Runner runner = Runner.getInstance();
        if (args.length < 2) {
            runner.runCli();
        } else {
            runner.run(args);
        }

    }

}
