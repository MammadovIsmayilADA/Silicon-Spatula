package Silicon_Spatula;

import java.util.*;

public class Order {
    private List<MenuItem> items = new ArrayList<>();

    public void addItem(MenuItem item)
    {
        items.add(item);
    }
    public List<MenuItem> getItems()
    {
        return items;
    }
    @Override
    public String toString()
    {
        return items.size() + " items: " + items;
    }
}