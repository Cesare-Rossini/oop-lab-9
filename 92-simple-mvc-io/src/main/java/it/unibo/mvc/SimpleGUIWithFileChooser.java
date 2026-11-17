package it.unibo.mvc;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.io.File;
import javax.swing.JOptionPane;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUIWithFileChooser {
    private static final String TITLE = "Slightly less simple GUI";
    private static final int PROPORTION = 5;
    private final JFrame frame = new JFrame(TITLE);

    /* */
    public SimpleGUIWithFileChooser(final Controller controller) {
        final JPanel canvas = new JPanel();
        final JPanel topBar = new JPanel();
        final JTextField pathField = new JTextField(controller.getFilePath());
        final JTextArea mainArea = new JTextArea();
        final JButton save = new JButton("Save on file");
        final JButton browse = new JButton("Browse...");
        pathField.setEditable(false);
        topBar.add(pathField);
        topBar.add(browse);
        canvas.setLayout(new BorderLayout());
        canvas.add(topBar, BorderLayout.NORTH);
        canvas.add(mainArea, BorderLayout.CENTER);
        canvas.add(save, BorderLayout.SOUTH);
        frame.setContentPane(canvas);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /* */
        browse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final JFileChooser fc = new JFileChooser("Choose where to save:");
                fc.setSelectedFile(controller.getFile());
                final int choice = fc.showSaveDialog(frame);
                switch (choice) {
                    case JFileChooser.APPROVE_OPTION:
                        final File newPath = fc.getSelectedFile();
                        controller.setFile(newPath.getPath());
                        pathField.setText(newPath.getPath());
                        break;
                    case JFileChooser.CANCEL_OPTION:
                        break;
                    default:
                        JOptionPane.showMessageDialog(frame, choice, "Something went wrong", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        /* */
        save.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent ignored) {
                    try {
                        controller.saveString(mainArea.getText());
                    } catch (Exception e) {
                        IO.println("Errore durante il salvataggio");
                    }

                }
            }
        );
    }

    /* */
    private void display() {
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / PROPORTION, sh / PROPORTION); 
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    /* */
    public static void main() {
        final SimpleGUIWithFileChooser gui = new SimpleGUIWithFileChooser(new Controller());
        gui.display();
    }
}
