package Silicon_Spatula;

import java.io.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Logger
{
    private static final String LOG_FILE="log.txt";
    private static final DateTimeFormatter fmt=DateTimeFormatter.ofPattern("HH:mm:ss");
    private static Log_window logWindow;

    //Call this once from MainApp to connect the window
    public static void setLogWindow(Log_window window)
    {
        logWindow=window;
    }

    public static void log(String message)
    {
        String line="["+LocalTime.now().format(fmt)+"]"+message;
        System.out.println(line);

        //Update window in real time
        if (logWindow!=null)
        {
            logWindow.addMessage(line);
        }

        // Write to file
        try
        {
            BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE, true));
            writer.write(line);
            writer.newLine();
            writer.close();
        } catch (IOException e)
        {//Teacher said that BufferedWriter cant work without throwing exceptoin
            System.out.println(e.getMessage());
        }
    }

    public static String loadLog()
    {
        StringBuilder sb=new StringBuilder();
        try
        {
            File file=new File(LOG_FILE);
            if (!file.exists()) return "Log is empty.";
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line=reader.readLine())!= null)
            {
                sb.append(line).append("\n");
            }
            reader.close();
        }
        catch (IOException e)
        {
            return "Could not load log."+ e.getMessage();
        }
        return sb.toString();
    }
}