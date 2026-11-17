package it.unibo.mvc;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUI {

    
    private static final String TITLE = "Simple GUI";
    private static final int PROPORTION = 5;
    private final JFrame frame = new JFrame(TITLE);
 
    /* */
    public SimpleGUI(final Controller controller) {
        final JPanel canvas = new JPanel();
        final JTextArea mainArea= new JTextArea();
        final JButton save = new JButton("Save on file");
        canvas.setLayout(new BorderLayout());
        canvas.add(mainArea, BorderLayout.CENTER);
        canvas.add(save, BorderLayout.SOUTH);
        frame.setContentPane(canvas);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /* */
        save.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent ignored) {
                    try {
                        controller.saveString(mainArea.getText());
                    } catch (final Exception e) {
                        IO.println("Errore");
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
        final SimpleGUI gui = new SimpleGUI(new Controller());
        gui.display();
    }
}
