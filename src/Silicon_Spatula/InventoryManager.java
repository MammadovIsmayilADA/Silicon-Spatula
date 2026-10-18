package Silicon_Spatula;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class InventoryManager
{

    private HashMap<Ingredient, Integer> stock;
    private List<IAppliance>appliances= new ArrayList<>(Arrays.asList(new Grill(),new DrinkDispenser()));

    public InventoryManager() 
    {
        stock=new HashMap<>();
        stock.put(new Ingredient("BUNS"),10);
        stock.put(new Ingredient("PATTIES"),10);
        stock.put(new Ingredient("TOMATO"),10);
        stock.put(new Ingredient("POTATO"),10);
        stock.put(new Ingredient("COLA"),10);
        stock.put(new Ingredient("TEA"),10);
        stock.put(new Ingredient("CHICKEN"),10);
    }
    public HashMap<Ingredient, Integer> getAll()
    {
        return stock;
    }

    // Returns quantity of ingredient
    public int getAmount(Ingredient ingredient)
    {
        return stock.getOrDefault(ingredient, 0);
    }

    //Directly set amount
    public void setAmount(Ingredient ingredient, int amount)
    {
        stock.put(ingredient, amount);
    }

    // Adds quantity when restocking
    public void restock(Ingredient ingredient,int amount)
    {
        Logger.log("Restocked "+amount +"x " +ingredient.getName());
        stock.put(ingredient, getAmount(ingredient) + amount);
    }
    public boolean checkStock(MenuItem item)
    {
        for (Map.Entry<String, Integer> entry : item.getIngredients().entrySet())
        {
            Ingredient ingredient=new Ingredient(entry.getKey());
            if (stock.getOrDefault(ingredient, 0)<entry.getValue())
            {
                return false;
            }
        }
        return true;
    }

    public void deductIngredients(MenuItem item)
    {
        for (Map.Entry<String, Integer> entry : item.getIngredients().entrySet())
        {
            Ingredient key =new Ingredient(entry.getKey());
            stock.put(key, getAmount(key)-entry.getValue());
        }
    }

//     public void cookNextOrder(Queue<Order> queue, States states) {
//     if (queue.isEmpty()) {
//         Logger.log("No orders in queue.");
//         return;
//     }
//     Order order = queue.poll();
//     Logger.log("Processing order: " + order.toString());

//     for (MenuItem item : order.getItems()) {
//         if (!checkStock(item)) {
//             Logger.log("ERROR: Not enough ingredients for " + item.getName() + ". Order cancelled.");
//             return;
//         }
//     }
//     for (MenuItem item : order.getItems()){
//         for(IAppliance appliance : appliances){
//             if(appliance.canProcess(item)){
//                 appliance.processTask(item);
//                 break;

//             }
//         }
//     }
//     for (MenuItem item : order.getItems()) {
//         deductIngredients(item);
//     }
//     states.money += order.getItems().size() * 10;
//     Logger.log("Order completed. +" + order.getItems().size() * 10 + "$");
// }



//cook next no need anymore, as there is new function in OrderManager



    public List<IAppliance> getAppliances()

    {
        return appliances;
    }
}