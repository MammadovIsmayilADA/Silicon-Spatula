package Silicon_Spatula;

public class DrinkDispenser implements IAppliance
{
    @Override
    public boolean canProcess(MenuItem item)
    {
        return item instanceof Beverage;
    }
    @Override
    public void processTask(MenuItem item)
    {
        Logger.log("DrinkDispenser: preparing "+item.getName());
    }
}