import fileio.InputLoader;
import fileio.Writer;
import game.Game;

public final class Main {

    private Main() {
    }

    /**
     *  Entry point to implementation
     */
    public static void main(final String[] args) {
        Game game = InputLoader.getInstance().readData(args[0]);
        Writer.getInstance().writeData(args[1], game.play());
    }
}
