package Silicon_Spatula;

import java.awt.*;
import javax.swing.*;

public class Queue_window extends JPanel
{
    private States state;
    private DefaultListModel<String> listModel;
    private JList<String> list;
    private JButton cookButton;

    public Queue_window(States state, OrderManager[] managerRef)
    {
        this.state=state;
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Order Queue"));
        listModel=new DefaultListModel<>();
        list=new JList<>(listModel);
        cookButton=new JButton("Cook Next Order");
        list.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
        cookButton.setFont(new Font("Comic Sans MS", Font.BOLD, 20));

        cookButton.addActionListener(e->
        {
            if (managerRef[0] != null)
            {
                managerRef[0].cookNext();
            }
        });

        add(new JScrollPane(list), BorderLayout.CENTER);
        JTextArea hint = new JTextArea();
        hint.setEditable(false);
        hint.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
        hint.setBackground(null);
        hint.setText("Menu Guide:\n\n" +"Burger:\n  BUNS x1\n  PATTIES x1\n  TOMATO x1\n\n"
         + "Fries:\n  POTATO x2\n\n" +   "Cola:\n  COLA x1\n\n" +"Tea:\n  TEA x1");
        hint.setBorder(BorderFactory.createTitledBorder("Ingredients"));
        add(hint, BorderLayout.EAST);
        add(cookButton, BorderLayout.SOUTH);
        refresh();
    }

    public void refresh()
    {
        listModel.clear();
        int i=1;
        for (Order o : state.orderQueue)
        {
            listModel.addElement(i + ". " + o.toString());
            i++;
        }
    }
}