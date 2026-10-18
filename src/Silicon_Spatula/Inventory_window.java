package Silicon_Spatula;
import java.awt.*;
import java.util.HashMap;
import javax.swing.*;

public class Inventory_window extends JPanel 
{
    private JLabel moneyLabel;
    private States state;
    private HashMap<String, JLabel> ingredientLabels;
    public Inventory_window(States state) 
    {
       this.state=state;
       this.ingredientLabels=new HashMap<>();
       rebuild();
    }
    public void rebuild()
    {
        Font font =new Font("Comic Sans MS", Font.BOLD, 20);
        removeAll();
        HashMap <Ingredient, Integer> all = state.inventory.getAll();

        setLayout(new GridLayout(all.size()+1,2));
        setBorder(BorderFactory.createTitledBorder("Inventory"));  

        // Money row
        moneyLabel=new JLabel("$" + state.money);
        moneyLabel.setFont(font);
        JLabel moneytext=new JLabel("Money: ");
        moneytext.setFont(font);
        add(moneytext);
        add(moneyLabel);
        // One row per ingredient — built dynamically from HashMap
       all.forEach((Ingredient ingredient, Integer amount)-> {
        JLabel label=new JLabel(String.valueOf(amount));
        label.setFont(font);
        ingredientLabels.put(ingredient.getName(), label);
        JLabel ingredientLabel=new JLabel(ingredient.getName() + ":");
        ingredientLabel.setFont(font);
        add(ingredientLabel);
        add(label);
        });

        revalidate();
        repaint();      
    }
    public void refresh()
    {
        moneyLabel.setText("$"+state.money);
        ingredientLabels.forEach((name, label)->
            label.setText(String.valueOf(state.inventory.getAmount(new Ingredient(name))))
        );    
    }

}

/*        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> SaveLoad.save(state, "save.txt"));

        JButton loadButton = new JButton("Load");
        loadButton.addActionListener(e -> {
            States loaded = SaveLoad.load("save.txt");
            panel.updateMoney(loaded.money);
            panel.updateBuns(loaded.inventory.get("BUNS"));
            panel.updateCola(loaded.inventory.get("COLA"));
            panel.updatePatties(loaded.inventory.get("PATTIES"));
            panel.updateTomato(loaded.inventory.get("TOMATO"));
            panel.updatePotato(loaded.inventory.get("POTATO"));
            panel.updateChicken(loaded.inventory.get("CHICKEN"));
        });

        JPanel buttons = new JPanel();
        buttons.add(saveButton);
        buttons.add(loadButton);
*/