import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

/**
 * Axiom panel
 * @author  David Brousseau
 * @version 0.1
 */
public class AxiomPanel {

    // Constants
    private final static int COLUMN_WIDTH = 200;

    /** Axiom label */
    private final JLabel axiomLabel;

    /** Axiom text field */
    public final JTextField axiomTextField;

    /** Interpret button */
    private final JButton interpretButton;

    /** Iteration label */
    private final JLabel iterationLabel;

    /** Iteration text field */
    public final JTextField iterationTextField;

    /** Panel */
    public final JPanel panel;

    /** Parent application */
    private final Application parent;

    /** Result label */
    private final JLabel resultLabel;

    /** Result scroll pane */
    private final JScrollPane resultScrollPane;

    /** Result text area */
    public final JTextArea resultTextArea;

    /**
     * Constructor
     * @param app Parent application
     */
    public AxiomPanel(Application app) {
        axiomLabel = new JLabel("Axiome");
        axiomTextField = new JTextField();
        interpretButton = new JButton("Interpréter");
        iterationLabel = new JLabel("Itération");
        iterationTextField = new JTextField();
        panel = new JPanel(new GridBagLayout());
        parent = app;
        resultLabel = new JLabel("Résultat");
        resultTextArea = new JTextArea();
        resultScrollPane = new JScrollPane(resultTextArea);
        initialize();
    }

    /** Create the layout. */
    private void createLayout() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(6, 6, 3, 6);
        gbc.weightx = 1;
        panel.add(axiomLabel, gbc);
        gbc.gridy = 1;
        gbc.insets = new Insets(3, 6, 3, 6);
        panel.add(axiomTextField, gbc);
        gbc.gridy = 2;
        panel.add(iterationLabel, gbc);
        gbc.gridy = 3;
        panel.add(iterationTextField, gbc);
        gbc.gridy = 4;
        panel.add(resultLabel, gbc);
        gbc.gridy = 5;
        gbc.weighty = 1;
        panel.add(resultScrollPane, gbc);
        gbc.gridy = 6;
        gbc.insets = new Insets(3, 6, 6, 6);
        gbc.weighty = 0;
        panel.add(interpretButton, gbc);
    }

    /** Initialize the axiom panel. */
    private void initialize() {
        interpretButton.addActionListener(e -> parent.lSystemEngine.interpret(this));
        resultTextArea.setEditable(false);
        resultTextArea.setLineWrap(true);
        resultScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        createLayout();
        updateComponentSizes();
    }

    /** Update all component sizes. */
    private void updateComponentSizes() {
        axiomTextField.setPreferredSize(new Dimension(COLUMN_WIDTH, Application.ROW_HEIGHT));
        interpretButton.setPreferredSize(new Dimension(COLUMN_WIDTH, Application.ROW_HEIGHT));
        iterationTextField.setPreferredSize(new Dimension(COLUMN_WIDTH, Application.ROW_HEIGHT));
        resultScrollPane.setPreferredSize(new Dimension(COLUMN_WIDTH, Application.ROW_HEIGHT * 3));
    }
}