import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * Rule panel
 * @author  David Brousseau
 * @version 0.1
 */
public class RulePanel {

    // Constants
    private final static int CHARACTER_FIELD_WIDTH = 100;
    private final static int REPLACE_WITH_FIELD_WIDTH = 125;
    private final static int BUTTON_WIDTH = 125;

    /** Add button */
    public final JButton addButton;

    /** Character text field */
    public final JTextField characterTextField;

    /** Delete button */
    public final JButton deleteButton;

    /** Panel */
    public final JPanel panel;

    /** Parent application */
    private final Application parent;

    /** Replace with text field */
    public final JTextField replaceWithTextField;

    /**
     * Constructor
     * @param app Parent application
     */
    public RulePanel(Application app) {
        addButton = new JButton("Ajouter");
        characterTextField = new JTextField("Caractère");
        deleteButton = new JButton("Supprimer");
        panel = new JPanel(new GridBagLayout());
        parent = app;
        replaceWithTextField = new JTextField("Remplacer par");
        initialize();
    }

    /** Create the layout. */
    private void createLayout() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(6, 6, 6, 3);
        gbc.weightx = 1;
        panel.add(characterTextField, gbc);
        gbc.gridx = 1;
        gbc.insets = new Insets(6, 3, 6, 3);
        panel.add(replaceWithTextField, gbc);
        gbc.gridx = 2;
        gbc.insets = new Insets(6, 3, 6, 6);
        panel.add(addButton, gbc);
        panel.add(deleteButton, gbc);
        gbc.gridwidth = 3;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 0, 0);
        panel.add(new JSeparator(SwingConstants.HORIZONTAL), gbc);
    }

    /** Initialize the rule panel. */
    private void initialize() {
        addButton.addActionListener(e -> parent.lSystemEngine.add(this));
        deleteButton.addActionListener(e -> parent.lSystemEngine.delete(this));
        deleteButton.setVisible(false);
        createLayout();
        updateComponentSizes();
    }

    /** Update all component sizes. */
    private void updateComponentSizes() {
        addButton.setPreferredSize(new Dimension(BUTTON_WIDTH, Application.ROW_HEIGHT));
        characterTextField.setPreferredSize(new Dimension(CHARACTER_FIELD_WIDTH, Application.ROW_HEIGHT));
        deleteButton.setPreferredSize(new Dimension(BUTTON_WIDTH, Application.ROW_HEIGHT));
        replaceWithTextField.setPreferredSize(new Dimension(REPLACE_WITH_FIELD_WIDTH, Application.ROW_HEIGHT));
    }
}
