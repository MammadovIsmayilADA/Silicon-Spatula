package Silicon_Spatula;

import java.io.*;

public class SaveLoad {

    public static void save(States state, String filename)
    {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            writer.write("MONEY:" + state.money);
            writer.newLine();
            state.inventory.getAll().forEach((Ingredient ingredient, Integer amount) ->
            {
                try {
                    writer.write(ingredient.getName() + ":" + amount);
                    writer.newLine();
                } catch (IOException e) {// in case of error,we want to continue writing the rest of the file
                    System.out.println("Write error: " + e.getMessage());
                }
            });

            writer.write("QUEUE:");
            for (Order order : state.orderQueue)
            {
                for (MenuItem item : order.getItems()) {
                    writer.write(item.getName() + ";");
                }
                writer.write("|");
            }
            writer.newLine();
            writer.close();
            System.out.println("Saved to " + filename);

        }
        catch (IOException e)//bufferwriter catcher
        {
            System.out.println("Save error: " + e.getMessage());
        }
    }

    public static void load(States state, String filename)
    {
        try {
            BufferedReader reader=new BufferedReader(new FileReader(filename));
            String line;

            state.orderQueue.clear();

            while ((line = reader.readLine())!=null)
            {
                String[] parts = line.split(":", 2);

                if (parts[0].equals("MONEY")){
                    state.money = Long.parseLong(parts[1]);

                } else if (parts[0].equals("QUEUE")) {
                    if (parts.length > 1 && !parts[1].isEmpty()) {
                        String[] orders = parts[1].split("\\|");

                        for (String orderText : orders) {
                            if (!orderText.isEmpty()) {
                                Order order=new Order();

                                String[] items=orderText.split(";");
                                for (String itemName : items) {
                                    MenuItem item = createMenuItem(itemName);
                                    if (item != null) {
                                        order.addItem(item);
                                    }
                                }

                                if (!order.getItems().isEmpty())
                                {
                                    state.orderQueue.add(order);
                                }
                            }
                        }
                    }

                }
                else
                {
                    state.inventory.setAmount(new Ingredient(parts[0]),Integer.parseInt(parts[1]));
                }
            }

            reader.close();
            System.out.println("Loaded from "+filename);

        }
        catch (IOException e)
        {
            System.out.println("Load error: " + e.getMessage());
        }
    }

    private static MenuItem createMenuItem(String name)
    {
        switch (name) {
            case "Burger":
                return new Burger();
            case "Fries":
                return new Fries();
            case "Cola":
                return new Cola();
            case "Tea":
                return new Tea();
            default:
                return null;
        }
    }
}