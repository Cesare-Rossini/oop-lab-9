package it.unibo.mvc;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

//import javax.swing.JOptionPane;
//import javax.swing.JPopupMenu.Separator;

/**
 * Application controller. Performs the I/O.
 */
public final class Controller {

    public static final String HOME = System.getProperty("home");

    private File currentFile = new File(HOME + File.separator + "output.txt");

    /* */
    public File getFile() {
        return currentFile;
    }

    /* */
    public String getFilePath() {
        return currentFile.getPath();
    }

    /* */
    public void saveString(final String input) {
        try (PrintStream ps = new PrintStream(currentFile, StandardCharsets.UTF_8)) {
            ps.print(input);
        } catch (final IOException e) {
            IO.println("Errore");
        }
    }

    /* */
    public void setFile(final String path) {
        currentFile = new File(path);
    }
}
