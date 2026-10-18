package Silicon_Spatula;
import java.util.*;

abstract class MenuItem 
{
    protected String name;
    protected int price;

    public MenuItem(String name, int price) 
    {
        this.name = name;
        this.price = price;
    }

    public String getName()
    {
        return name;
    }
    public int getPrice()
    {
        return price;
    }
    public abstract String getType();
    public abstract Map<String, Integer> getIngredients();

    @Override
    public String toString()
    {
        return name+" ($"+price+")";
    }
}
abstract class HotFood extends MenuItem
{
    public HotFood(String name, int price)
    {
        super(name, price);
    }

    @Override
    public String getType() 
    {
        return "Hot Food";
    }
}

abstract class Beverage extends MenuItem
{
    public Beverage(String name, int price)
    {
        super(name, price);
    }

    @Override
    public String getType()
    {
        return "Beverage";
    }
}
class Burger extends HotFood
{
    public Burger()
    {
        super("Burger",20);
    }

    @Override
    public Map<String, Integer> getIngredients()
    {
        Map<String, Integer> map=new HashMap<>();
        map.put("BUNS",1);
        map.put("PATTIES",1);
        map.put("TOMATO",1);
        return map;
    }
}

class Fries extends HotFood
{
    public Fries()
    {
        super("Fries", 15);
    }

    @Override
    public Map<String, Integer> getIngredients()
    {
        Map<String, Integer> map=new HashMap<>();
        map.put("POTATO",2);
        return map;
    }
}

class Cola extends Beverage
{
    public Cola()
    {
        super("Cola",10);
    }

    @Override
    public Map<String, Integer> getIngredients()
    {
        Map<String, Integer> map=new HashMap<>();
        map.put("COLA", 1);
        return map;
    }
}

class Tea extends Beverage
{
    public Tea()
    {
        super("Tea", 10);
    }

    @Override
    public Map<String, Integer> getIngredients()
    {
        Map<String, Integer> map=new HashMap<>();
        map.put("TEA",1);
        return map;
    }
}




