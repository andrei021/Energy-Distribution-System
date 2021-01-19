import fileio.InputLoader;
import fileio.ObjectToWrite;
import fileio.Writer;
import game.Game;

public final class Main {

    private static String PATH =
            "/Users/andreimihai/Documents/facultate/anul2/oop/ACS-OOP-project/myOuts";

    private Main() {
    }

    /**
     *  Entry point to implementation
     */
    public static void main(final String[] args) {
        String out = args[0].substring(args[0].lastIndexOf("/"));

        StringBuilder sb = new StringBuilder();
        sb.append(PATH);
        sb.append(out);

        Game game = InputLoader.getInstance().readData(args[0]);
        ObjectToWrite obj = game.play();

        Writer.getInstance().writeData(args[1], obj);
        Writer.getInstance().writeData(sb.toString(), obj);
    }
}
