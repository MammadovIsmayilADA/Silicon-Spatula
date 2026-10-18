package Silicon_Spatula;

import java.awt.*;
import java.io.*;
import javax.swing.*;

public class MainApp
{    public static void main(String[] args)
    {
        States state=new States();
        File saveFile=new File("save.txt");
        if (saveFile.exists())
        {
            SaveLoad.load(state,"save.txt");
        }

        Dimension screen =Toolkit.getDefaultToolkit().getScreenSize();

        // Inventory window 
        Inventory_window inventoryWindow=new Inventory_window(state);
        Log_window logWindow=new Log_window();
        Restock_window restockWindow=new Restock_window(state, inventoryWindow, logWindow);
        OrderManager[] managerRef=new OrderManager[1];


        Queue_window queueWindow=new Queue_window(state, managerRef);
        OrderManager orderManager=new OrderManager(state, queueWindow,inventoryWindow);

        managerRef[0]=orderManager;
        orderManager.start();


        JButton saveButton=new JButton("Save");
        saveButton.addActionListener(e->SaveLoad.save(state, "save.txt"));

        JButton loadButton=new JButton("Load");
        loadButton.addActionListener(e -> {
            SaveLoad.load(state, "save.txt");
            inventoryWindow.rebuild();
            restockWindow.rebuild();
            queueWindow.refresh(); 
        });
        JPanel bottomButtons=new JPanel();
        bottomButtons.add(saveButton);
        bottomButtons.add(loadButton);

        JFrame inventoryFrame = new JFrame("The Silicon Spatula: Inventory");
        inventoryFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        inventoryFrame.setSize(screen.width / 2, screen.height *2/ 3);
        inventoryFrame.setLocation(0, 0);
        inventoryFrame.setLayout(new BorderLayout());
        inventoryFrame.add(inventoryWindow, BorderLayout.CENTER);
        inventoryFrame.add(bottomButtons, BorderLayout.SOUTH);

        // auto-save on close
        inventoryFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                SaveLoad.save(state, "save.txt");
                System.exit(0);
            }
        });

        // Restock window 
        JFrame restockFrame = new JFrame("Restock");
        restockFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        restockFrame.setSize(screen.width / 2, screen.height / 4);
        restockFrame.setLocation(0, screen.height*2/3);
        restockFrame.add(restockWindow);

        inventoryFrame.setVisible(true);
        restockFrame.setVisible(true);


        JFrame logFrame=new JFrame("System Log");
        Logger.setLogWindow(logWindow);
        
        logFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        logFrame.setSize(screen.width/2, screen.height/4);
        logFrame.setLocation(screen.width/2, screen.height*2/3);

        logFrame.add(logWindow);
        logFrame.setVisible(true);
        JFrame queueFrame = new JFrame("Orders Queue");
        queueFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        queueFrame.setSize(screen.width/2, 2*screen.height/3);
        queueFrame.setLocation(screen.width/2, 0);
        queueFrame.add(queueWindow);
        queueFrame.setVisible(true);
    }
}