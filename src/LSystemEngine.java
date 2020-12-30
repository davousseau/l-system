import java.awt.Color;
import java.util.ArrayList;

/**
 * L-System engine
 * @author  David Brousseau
 * @version 0.1
 */
public class LSystemEngine {

    /** List contents */
    public final ArrayList<RulePanel> contents;

    /** Parent application */
    private final Application parent;

    /**
     * Constructor
     * @param app Parent application
     */
    public LSystemEngine(Application app) {
        contents = new ArrayList<>();
        parent = app;
    }

    /**
     * Add a new rule.
     * @param rule Rule to add
     */
    public void add(RulePanel rule) {
        int status = validate(rule);
        parent.rulesListPanel.messageLabel.setForeground(Color.RED);
        if (status == 1) {
            parent.rulesListPanel.messageLabel.setText("La règle a trop de caractères.");
        } else if (status == 2) {
            parent.rulesListPanel.messageLabel.setText("La règle existe déjà pour ce caractère.");
        } else {
            rule.addButton.setVisible(false);
            rule.characterTextField.setEditable(false);
            rule.deleteButton.setVisible(true);
            rule.replaceWithTextField.setEditable(false);
            contents.add(rule);
            parent.rulesListPanel.messageLabel.setForeground(Color.GREEN.darker());
            parent.rulesListPanel.messageLabel.setText("La règle a été ajoutée avec succès.");
            parent.rulesListPanel.updateRulesList(contents);
        }
    }

    /**
     * Validate a new rule.
     * @param  rule Rule to validate
     * @return      Status code { 0: Rule is valid, 1: Rule has too much
     *              characters, 2: Rule already exists for this character }
     */
    public int validate(RulePanel rule) {
        int status = 0;
        String character = rule.characterTextField.getText();
        if (character.length() != 1) { status = 1; }
        for (int i = 0; i < contents.size(); i++) {
            character = contents.get(i).characterTextField.getText();
            if (character.equals(rule.characterTextField.getText())) { status = 2; }
        }
        return status;
    }

    /** Clear all rules. */
    public void clearAll() {
        contents.clear();
        parent.axiomPanel.axiomTextField.setText("");
        parent.axiomPanel.iterationsTextField.setText("");
        parent.axiomPanel.resultTextArea.setText("");
        parent.rulesListPanel.messageLabel.setText("");
        parent.rulesListPanel.updateRulesList(contents);
        parent.rulesListPanel.rulesList.validate();
        parent.rulesListPanel.rulesList.repaint();
    }

    /**
     * Delete the selected rule.
     * @param rule Rule to delete
     */
    public void delete(RulePanel rule) {

        for (int i = 0; i < contents.size(); i++) {
            String character = contents.get(i).characterTextField.getText();
            if (character.equals(rule.characterTextField.getText())) {
                contents.remove(i);
                parent.rulesListPanel.messageLabel.setForeground(Color.GREEN.darker());
                parent.rulesListPanel.messageLabel.setText("La règle a été supprimée avec succès.");
                parent.rulesListPanel.updateRulesList(contents);
                parent.rulesListPanel.rulesList.validate();
                parent.rulesListPanel.rulesList.repaint();
            }
        }
    }

    /**
     * Interpret the axiom entered.
     * @param axiom Axiom to interpret
     */
    public void interpret(AxiomPanel axiom) {
        // TODO:
        // parent.rulesListPanel.messageLabel.setForeground(Color.RED);
        // parent.rulesListPanel.messageLabel.setText("Le nombre d'itération est
        // invalide.");
        parent.rulesListPanel.messageLabel.setForeground(Color.GREEN.darker());
        parent.rulesListPanel.messageLabel.setText("L'interprétation a terminée avec succès.");
    }
}