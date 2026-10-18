package Silicon_Spatula;

public class Ingredient
{
    private String name;
    public Ingredient(String name)
    {
        this.name=name;
    }

    public String getName()
    {
        return name;
    }

    @Override
    public String toString()
    {
        return name;
    }

    @Override//Check if two Ingredient objects are equal based on their name
    public boolean equals(Object obj)
    {
        if(this== obj)
            return true;
        if(!(obj instanceof Ingredient))// not an Ingredient at all
            return false;
        Ingredient other=(Ingredient) obj;
        return name.equals(other.name);
    }
    // Required so that HashMap can correctly find Ingredient keys
    // Two ingredients with the same name must have the same hashCode
    @Override
    public int hashCode()
    {
        return name.hashCode();
    }
}