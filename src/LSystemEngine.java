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
            parent.rulesListPanel.messageLabel.setText("Trop de caractères");
        } else if (status == 2) {
            parent.rulesListPanel.messageLabel.setText("Existe déjà pour ce caractère");
        } else {
            rule.addButton.setVisible(false);
            rule.characterTextField.setEditable(false);
            rule.deleteButton.setVisible(true);
            rule.replaceWithTextField.setEditable(false);
            contents.add(rule);
            parent.rulesListPanel.messageLabel.setForeground(Color.GREEN.darker());
            parent.rulesListPanel.messageLabel.setText("Ajoutée avec succès");
            parent.rulesListPanel.updateRulesList(contents);
        }
    }

    /** Clear all rules. */
    public void clearAll() {
        contents.clear();
        parent.axiomPanel.axiomTextField.setText("");
        parent.axiomPanel.iterationTextField.setText("");
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
                parent.rulesListPanel.messageLabel.setText("Supprimée avec succès");
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
        parent.rulesListPanel.messageLabel.setForeground(Color.RED);
        if (parent.axiomPanel.axiomTextField.getText().isEmpty()) {
            parent.rulesListPanel.messageLabel.setText("L'axiome est requise");
        } else if (!isInteger(parent.axiomPanel.iterationTextField.getText())) {
            parent.rulesListPanel.messageLabel.setText("L'itération requiert un nombre valide");
        } else {
            String result = parent.axiomPanel.axiomTextField.getText();
            int iteration = Integer.parseInt(parent.axiomPanel.iterationTextField.getText());
            for (int i = 0; i < iteration; i++) { result = rewrite(result); }
            parent.axiomPanel.resultTextArea.setText(result);
            parent.rulesListPanel.messageLabel.setForeground(Color.GREEN.darker());
            parent.rulesListPanel.messageLabel.setText("Terminée avec succès");
        }
    }

    /**
     * Check if the iteration represents an integer.
     * @param  iteration Iteration to check
     * @return           True or False
     */
    private boolean isInteger(String iteration) {
        if (iteration == null) { return false; }
        try {
            Integer.parseInt(iteration);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    /**
     * Rewrite the axiom following the rules.
     * @param  axiom Axiom to rewrite
     * @return       Result
     */
    private String rewrite(String axiom) {
        String result = "";
        for (int i = 0; i < axiom.length(); i++) {
            String axiomCharacter = axiom.substring(i, i + 1);
            String character = axiomCharacter;
            for (int j = 0; j < contents.size(); j++) {
                String ruleCharacter = contents.get(j).characterTextField.getText();
                if (axiomCharacter.equals(ruleCharacter)) {
                    character = contents.get(j).replaceWithTextField.getText();
                }
            }
            result += character;
        }
        return result;
    }

    /**
     * Validate a new rule.
     * @param  rule Rule to validate
     * @return      Status code { 0: Rule is valid, 1: Rule has too much
     *              characters, 2: Rule already exists for this character }
     */
    private int validate(RulePanel rule) {
        int status = 0;
        String character = rule.characterTextField.getText();
        if (character.length() != 1) { status = 1; }
        for (int i = 0; i < contents.size(); i++) {
            character = contents.get(i).characterTextField.getText();
            if (character.equals(rule.characterTextField.getText())) { status = 2; }
        }
        return status;
    }
}