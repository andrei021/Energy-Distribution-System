import fileio.InputLoader;
import fileio.Writer;
import game.Game;

/**
 * Entry point to the simulation
 */
public final class Main {

    private Main() { }

    /**
     * Main function which reads the input file and starts simulation
     *
     * @param args input and output files
     * @throws Exception might error when reading/writing/opening files, parsing JSON
     */
    public static void main(final String[] args) throws Exception {
        Game game = InputLoader.getInstance().readData(args[0]);
        Writer.getInstance().writeData(args[1], game.play());
    }
}
