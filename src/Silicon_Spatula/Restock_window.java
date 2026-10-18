package Silicon_Spatula;

import java.awt.*;
import javax.swing.*;

public class Restock_window extends JPanel
{

    private Log_window logWindow;

    private States state;
    private Inventory_window inventory;
    private JComboBox<String> dropdown;

    public Restock_window(States state, Inventory_window inventory, Log_window logWindow) {
        this.state = state;
        this.inventory = inventory;
        this.logWindow = logWindow;
        rebuild();
    }

    public void rebuild()
    {
        removeAll();
        setLayout(new GridBagLayout ());
        setBorder(BorderFactory.createTitledBorder("Restock"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Build dropdown from inventory
        String[] names = state.inventory.getAll().keySet()
            .stream()
            .map(Ingredient::getName)
            .toArray(String[]::new);

        dropdown = new JComboBox<>(names);
        dropdown.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
        dropdown.setPreferredSize(new Dimension(300, 60));

        JButton buyButton = new JButton("Buy (+5)");
        String selected = (String) dropdown.getSelectedItem();
        buyButton.setText("Buy " + selected + " (+5)");
        dropdown.addActionListener(e -> {
    String sel = (String) dropdown.getSelectedItem();
    buyButton.setText("Buy " + sel + " (+5)");
});
        buyButton.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
        buyButton.setPreferredSize(new Dimension(300, 60));
        buyButton.addActionListener(e -> {
            String chosen = (String) dropdown.getSelectedItem();
            int cost = 5;
            if (state.money >= cost) {
                state.money -= cost;
                state.inventory.restock(new Ingredient(chosen), 5);
                inventory.refresh();
            } else {
                JOptionPane.showMessageDialog(this, "Not enough money!");
            }
            if (state.money < cost) {
    buyButton.setEnabled(state.money >= cost);
}
        });

        gbc.gridx =0;
        add(dropdown, gbc);
        gbc.gridx = 1;
        add(buyButton, gbc);
        revalidate();
        repaint();
    }
}