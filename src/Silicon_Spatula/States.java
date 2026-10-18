package Silicon_Spatula;

import java.util.*;
public class States
{
    public long money;
    public InventoryManager inventory;
    public Queue<Order> orderQueue;

    public States() {
        money = 100;
        inventory = new InventoryManager();
        orderQueue = new LinkedList<>();
    }
}