package Silicon_Spatula;

public interface IAppliance
{
    boolean canProcess(MenuItem item);
    void processTask(MenuItem item);
}