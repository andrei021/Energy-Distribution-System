package fileio;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public final class Writer {

    private static final Writer WRITER;
    private ObjectMapper mapper = new ObjectMapper();

    static {
        WRITER = new Writer();
    }

    public Writer() {
    }

    public static Writer getInstance() {
        return WRITER;
    }

    /**
     * Puts obj in a JSON file at given path
     */
    public void writeData(final String outPath, final ObjectToWrite obj) {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(outPath), obj);
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
