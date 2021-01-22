import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

/**
 * Rules list panel
 * @author  David Brousseau
 * @version 0.1
 */
public class RulesListPanel {

    /** Clear all button */
    private final JButton clearAllButton;

    /** Message label */
    public final JLabel messageLabel;

    /** Panel */
    public final JPanel panel;

    /** Parent application */
    private final Application parent;

    /** Rules label */
    private final JLabel rulesLabel;

    /** Rules list */
    public final JPanel rulesList;

    /** Rules scroll pane */
    private final JScrollPane rulesScrollPane;

    /**
     * Constructor
     * @param app Parent application
     */
    public RulesListPanel(Application app) {
        clearAllButton = new JButton("Tout effacer");
        messageLabel = new JLabel();
        panel = new JPanel(new GridBagLayout());
        parent = app;
        rulesLabel = new JLabel("Règles");
        rulesList = new JPanel(new GridBagLayout());
        rulesScrollPane = new JScrollPane(rulesList);
        initialize();
    }

    /** Create the layout. */
    private void createLayout() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(6, 6, 3, 3);
        gbc.weightx = 1;
        panel.add(rulesLabel, gbc);
        gbc.gridx = 1;
        gbc.insets = new Insets(6, 3, 3, 6);
        panel.add(messageLabel, gbc);
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(3, 6, 3, 6);
        gbc.weighty = 1;
        panel.add(rulesScrollPane, gbc);
        gbc.gridy = 2;
        gbc.insets = new Insets(3, 6, 6, 6);
        gbc.weighty = 0;
        panel.add(clearAllButton, gbc);
    }

    /** Initialize the rules list panel. */
    private void initialize() {
        clearAllButton.addActionListener(e -> parent.lSystemEngine.clearAll());
        clearAllButton.setPreferredSize(new Dimension(0, Application.ROW_HEIGHT));
        rulesScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        createLayout();
        updateRulesList(parent.lSystemEngine.contents);
    }

    /**
     * Update the rules list.
     * @param contents Engine contents
     */
    public void updateRulesList(ArrayList<RulePanel> contents) {
        rulesList.removeAll();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.PAGE_START;
        gbc.gridx = 0;
        for (int i = 0; i < contents.size(); i++) {
            gbc.gridy = i;
            rulesList.add(contents.get(i).panel, gbc);
        }
        gbc.gridy = contents.size();
        rulesList.add(new RulePanel(parent).panel, gbc);
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.gridy = contents.size() > 0 ? contents.size() + 1 : 1;
        gbc.weighty = 1;
        rulesList.add(new JLabel(), gbc);
    }
}
