package Silicon_Spatula;

import java.util.*;
import javax.swing.Timer;

public class OrderManager {

    private States state;
    private Queue_window queueWindow;
    private Inventory_window inventoryWindow;
    private Timer timer;

    private static final MenuItem[] MENU_ITEMS={new Burger(),new Fries(),new Cola(),new Tea()};

    public OrderManager(States state, Queue_window queueWindow, Inventory_window inventoryWindow)
    {
        this.state = state;
        this.queueWindow = queueWindow;
        this.inventoryWindow = inventoryWindow;
    }

    public void start()
    {
        timer=new Timer(5000,e->generateOrder());
        timer.start();
    }

    public void stop()
    {
        if (timer!=null)
        timer.stop();
    }

    private void generateOrder()
    {
    Random random=new Random();
    Order order=new Order();
    int itemCount=random.nextInt(4)+1; 
    for (int i=0;i<itemCount; i++)
    {
        MenuItem item=MENU_ITEMS[random.nextInt(MENU_ITEMS.length)];
        order.addItem(item);
    }
    state.orderQueue.add(order);
    Logger.log("New order received: "+order.toString());
    queueWindow.refresh();
}

    public void cookNext()
    {
        if (state.orderQueue.isEmpty())
        {
            Logger.log("No orders in queue.");
            return;
        }
        Order order=state.orderQueue.poll();
        boolean success=true;

        for (MenuItem item : order.getItems())
        {
            if (!state.inventory.checkStock(item))
            {
                Logger.log("ERROR: Not enough ingredients for " + item.getName() + ". Order cancelled.");
                success=false;
                break;
            }
        }

        if (success)
        {
            for (MenuItem item : order.getItems()) 
        {
                state.inventory.deductIngredients(item);
                for (IAppliance appliance : state.inventory.getAppliances()) 
                {
                    if (appliance.canProcess(item))
                    {
                        appliance.processTask(item);
                        break;
                    }
                }
            }
            state.money += order.getItems().size() * 10;
            Logger.log("Order completed. +$" + order.getItems().size() * 10);
            inventoryWindow.refresh();
        }
        queueWindow.refresh();
    }
}