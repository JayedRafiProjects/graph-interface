import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.*;

public class GUI extends JPanel {

    //Colors
    private final Color GRAPH_COLOR = new Color(249, 85, 80, 1); // Red
    private final Color GRID_COLOR = new Color(200, 200, 200, 200); // Gray

    //Dimensions
    private static final int WIDTH = 1400; // Length of Interface
    private static final int HEIGHT = 700; // Height of Interface
    private final int L_PADDING = 50; //Left padding for graph simulation frame
    private final int T_PADDING = 50; //Top padding for graph simulation frame
    private final int W_FRAME = 1100; // Length of graph simulation frame
    private final int H_FRAME = 350; // Length of graph simulation frame
    private static final int B_WIDTH = 200; //Button width
    private static final int B_HEIGHT = 40; //Button height
    private static final int B_XPADDING = (WIDTH-50-1100-B_WIDTH)/2;//Button x-padding; (WIDTH-L_PADDING-W_FRAME-B-WIDTH)/2
    private static final int B_TPADDING = 50;//Button Y-padding
    private static final int B_INTERVAL = (350-(4*B_HEIGHT))/6;//Button in-between intervals
    private final int Y_GRID = 20;

    //Containers
    private List<Double>inValue; //Received value

    public GUI(List<Double>inValue){
        this.inValue = inValue;
    }//end GUI

    protected void paintComponent(Graphics gph){
        super.paintComponent(gph);
        Graphics2D g2D = (Graphics2D) gph;
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //Simulation frame
        g2D.setColor(Color.WHITE);
        g2D.fillRect(WIDTH-L_PADDING-W_FRAME, T_PADDING, W_FRAME, H_FRAME);
        g2D.setColor(Color.BLACK);

        //Y-grid
        for(int i=0; i<Y_GRID;i++){
            int xIn =
        }

    }//end simulateGraph

    private static void processGUI() {
        //-------------------------------
        List<Double> scores = new ArrayList<>();
        Random random = new Random();
        int maxDataPoints = 40;
        int maxScore = 10;
        for (int i = 0; i < maxDataPoints; i++) {
            scores.add((double) random.nextDouble() * maxScore);
//            scores.add((double) i);
        }
        //----------------------------------------------
        GUI gFrame = new GUI(scores);
        gFrame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        JFrame frame = new JFrame("Radio Frequency - System Analysis Software");

        //Button
        JButton button_one = new JButton("Select FILE (.TXT)");
        JButton button_two = new JButton("Analysis ITU / EEE / EU");
        JButton button_three = new JButton("Print Analysis");
        JButton button_four = new JButton("Software Information");

        //Button dimensions
        button_one.setBounds(B_XPADDING, B_TPADDING, B_WIDTH,B_HEIGHT);
        button_two.setBounds(B_XPADDING,B_TPADDING+B_HEIGHT+B_INTERVAL,B_WIDTH,B_HEIGHT);
        button_three.setBounds(B_XPADDING,B_TPADDING+B_HEIGHT+B_INTERVAL+B_HEIGHT+B_INTERVAL,B_WIDTH,B_HEIGHT);
        button_four.setBounds(B_XPADDING,B_TPADDING+B_HEIGHT+B_INTERVAL+B_HEIGHT+B_INTERVAL+B_HEIGHT+B_INTERVAL,B_WIDTH,B_HEIGHT);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(button_one);
        frame.add(button_two);
        frame.add(button_three);
        frame.add(button_four);
        frame.getContentPane().add(gFrame);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }//End processGUI

    public static void main(String[]args){
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                processGUI();
            }
        });
    }
}
