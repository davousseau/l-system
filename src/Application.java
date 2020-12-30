import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

/**
 * Application main class
 * @author  David Brousseau
 * @version 0.1
 */
public class Application {

    // Constants
    public final static int     ROW_HEIGHT = 25;
    private final static String TITLE      = "Interpréteur L-Système";
    private final static String VERSION    = "0.1";

    /** Axiom panel */
    public final AxiomPanel axiomPanel;

    /** Frame */
    private final JFrame frame;

    /** L-System engine */
    public final LSystemEngine lSystemEngine;

    /** Rules list panel */
    public final RulesListPanel rulesListPanel;

    /** Constructor */
    public Application() {
        axiomPanel = new AxiomPanel(this);
        frame = new JFrame();
        lSystemEngine = new LSystemEngine(this);
        rulesListPanel = new RulesListPanel(this);
        initialize();
    }

    /** Create the layout. */
    private void createLayout() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        frame.getContentPane().add(rulesListPanel.panel, gbc);
        gbc.gridx = 1;
        gbc.weightx = 0;
        frame.getContentPane().add(new JSeparator(SwingConstants.VERTICAL), gbc);
        gbc.gridx = 2;
        gbc.weightx = 1;
        frame.getContentPane().add(axiomPanel.panel, gbc);
    }

    /** Initialize the application. */
    private void initialize() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        frame.setResizable(false);
        frame.setTitle(TITLE + " v" + VERSION);
        createLayout();
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * Run the application.
     * @param args Arguments
     */
    public static void main(String[] args) { SwingUtilities.invokeLater(Application::new); }
}