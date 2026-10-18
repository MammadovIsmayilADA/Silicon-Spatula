package Silicon_Spatula;

public class Grill implements IAppliance
{
    @Override
    public boolean canProcess(MenuItem item)
    {
        return item instanceof HotFood;
    }

    @Override
    public void processTask(MenuItem item)
    {
        Logger.log("Grill: grilling "+item.getName());
    }
}