package diceroller;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class DiceBarGraph extends JPanel {
    private HashMap<Integer, Integer> rollResults;
    private int maxValue;

    // Constructor
    public DiceBarGraph(HashMap<Integer, Integer> rollResults) {
        this.rollResults = rollResults;
        this.maxValue = rollResults.values().stream().max(Integer::compareTo).orElse(1);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int width = getWidth();
        int height = getHeight();

        int numBars = rollResults.size();
        int barWidth = width / numBars;

        int i = 0;
        for (Integer face : rollResults.keySet()) {
            int value = rollResults.get(face);

            // Scale height to fit window
            int barHeight = (int) (((double) value / maxValue) * (height - 50));

            // X position of the bar
            int x = i * barWidth + 10;
            int y = height - barHeight - 30;

            // Draw bar
            g.setColor(Color.BLUE);
            g.fillRect(x, y, barWidth - 20, barHeight);

            // Draw value label above bar
            g.setColor(Color.BLACK);
            g.drawString(String.valueOf(value), x + (barWidth / 3), y - 5);

            // Draw face label below bar
            g.drawString("Side " + face, x + (barWidth / 4), height - 10);

            i++;
        }
    }

    public static void showGraph(HashMap<Integer, Integer> rollResults) {
        JFrame frame = new JFrame("Dice Roll Bar Graph");
        DiceBarGraph panel = new DiceBarGraph(rollResults);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.add(panel);
        frame.setVisible(true);
    }

    // Example driver
   
}
