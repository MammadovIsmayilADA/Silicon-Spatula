package Silicon_Spatula;

import java.awt.*;
import javax.swing.*;

public class Log_window extends JPanel
{    private JTextArea logArea;
    public Log_window()
    {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("System Log"));
        logArea=new JTextArea();
        logArea.setEditable(false);
        logArea.setFont(new Font("Comic Sans MS", Font.PLAIN,14));
        logArea.setLineWrap(true);
        JScrollPane scroll=new JScrollPane(logArea);
        add(scroll,BorderLayout.CENTER);
        logArea.setText(Logger.loadLog());
        scrollToBottom();
    }
    public void addMessage(String message)
    {
        logArea.append(message+"\n");
        scrollToBottom();
    }

    private void scrollToBottom()
    {
        logArea.setCaretPosition(logArea.getDocument().getLength());
    }
}