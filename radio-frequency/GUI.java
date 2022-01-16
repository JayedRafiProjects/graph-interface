import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.List;
import javax.swing.*;
import java.text.DecimalFormat;
import java.util.Collections;

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
    private final int H_FRAME = 350; // Height of graph simulation frame
    private static final int B_WIDTH = 150; //Button width
    private static final int B_HEIGHT = 40; //Button height
    private static final int B_XPADDING = (WIDTH-50-1100-B_WIDTH)/2;//Button x-padding; (WIDTH-L_PADDING-W_FRAME-B-WIDTH)/2
    private static final int B_TPADDING = 50;//Button Y-padding
    private static final int B_INTERVAL = (350-(4*B_HEIGHT))/6;//Button in-between intervals
    private final int Y_GRID = 35;//Num of horizontal grid
    private final int X_GRID = 110;//num of vertical grid
    private final int POINT_INDICATOR_LENGTH = 5;

    //Simulation value range
    DecimalFormat dFormat = new DecimalFormat("#.##");//Format up to 2 decimal
    private Double xMin = 0.0;
    private Double yMin = 0.0;
    private Double xMax = 1000.0;
    private Double yMax = 1000.0;
    private int quantity;

    //Containers
    private List<Double>xAxis;
    private List<Double>yAxis;

    public GUI(List<Double>xAxis, List<Double>yAxis){
        this.xAxis = xAxis;
        this.yAxis = yAxis;
    }//end GUI

    protected void paintComponent(Graphics gph){
        super.paintComponent(gph);
        Graphics2D g2D = (Graphics2D) gph;
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //Simulation frame
        g2D.setColor(Color.WHITE);
        g2D.fillRect(WIDTH-L_PADDING-W_FRAME, T_PADDING, W_FRAME, H_FRAME);
        g2D.setColor(Color.BLACK);

        //Initialize max min val
        quantity = xAxis.size();
        xMax = Collections.max(xAxis);
        xMin = Collections.min(xAxis);
        yMax = Collections.max(yAxis);
        yMin = Collections.min(yAxis);

        //Y-grid
        int xIn, yIn, xFin, yFin; //x & y coordinates
        int gridInterval;
        final int Y_POINT_INTERVAL = 5;
        String point;
        Double perSqr = (Math.abs(yMin)+Math.abs(yMax))/Y_GRID;//Value per square
        xIn = WIDTH-L_PADDING-W_FRAME;
        gridInterval = H_FRAME/Y_GRID;
        xFin = xIn+W_FRAME;

        for(int i=0; i<Y_GRID+1; i++){
            yIn = yFin = T_PADDING+i*gridInterval;
            g2D.setColor(GRID_COLOR);
            g2D.drawLine(xIn, yIn, xFin, yFin);//grid
            point = dFormat.format(yMax-i*perSqr)+"";
            FontMetrics metrics = g2D.getFontMetrics();
            int labelWidth = metrics.stringWidth(point);
            if(i%Y_POINT_INTERVAL==0){
                g2D.setColor(Color.BLACK);
                g2D.drawString(point, xIn-labelWidth-4, yIn-4+metrics.getHeight()/2);//Point
                g2D.setColor(Color.blue);
                g2D.drawLine(xIn, yIn, xIn+POINT_INDICATOR_LENGTH, yFin);//Point indicator
            }
        }

        //X-grid
        //Re-use Y-grid variables
        final int X_POINT_INTERVAL = 10;
        yIn = T_PADDING;
        yFin=yIn+H_FRAME;
        gridInterval = W_FRAME/X_GRID;
        for(int i=0; i<=X_GRID; i++){
            xIn = WIDTH-L_PADDING-W_FRAME+i*gridInterval;
            xFin = xIn;
            g2D.setColor(GRID_COLOR);
            g2D.drawLine(xIn, yIn, xFin, yFin);
            perSqr = (Math.abs(xMin)+Math.abs(xMax))/X_GRID;
            point = dFormat.format(xMin+i*perSqr)+"";
            FontMetrics metrics = g2D.getFontMetrics();
            int labelWidth = metrics.stringWidth(point);
            if(i%X_POINT_INTERVAL==0){
                g2D.setColor(Color.BLACK);
                g2D.drawString(point, xIn-labelWidth/2-2, T_PADDING+H_FRAME+metrics.getHeight()+2);//Point
            }
            if(i%Y_POINT_INTERVAL==0){
                g2D.setColor(Color.blue);
                g2D.drawLine(xIn, yFin-POINT_INDICATOR_LENGTH, xFin, yFin);//Point indicator
            }

        }

        //Blue point indication line
        g2D.setColor(Color.blue);
        g2D.drawLine(WIDTH-L_PADDING-W_FRAME, T_PADDING+H_FRAME, WIDTH-L_PADDING-W_FRAME+W_FRAME,T_PADDING+H_FRAME);//y
        g2D.drawLine(WIDTH-L_PADDING-W_FRAME,yIn,WIDTH-L_PADDING-W_FRAME,yFin);//x

        //Graph Simulation
        int x1, y1, x2, y2;
        double perPixelX = (Math.abs(xMin)+Math.abs(xMax))/W_FRAME;
        double perPixelY = (Math.abs(yMin)+Math.abs(yMax))/H_FRAME;
        for(int i=0; i<quantity-1; i++){
            if(Collections.min(xAxis)<0){
                x1 = (int)(WIDTH-L_PADDING-W_FRAME+(xAxis.get(i)+Math.abs(Collections.min(xAxis)))/perPixelX);
                x2 = (int)(WIDTH-L_PADDING-W_FRAME+(xAxis.get(i+1)+Math.abs((Collections.min(xAxis))))/perPixelX);
            }else{
                x1 = (int)(WIDTH-L_PADDING-W_FRAME+xAxis.get(i)/perPixelX);
                x2 = (int)(WIDTH-L_PADDING-W_FRAME+xAxis.get(i+1)/perPixelX);
            }
            if(Collections.min(yAxis)<0){
                y1 = (int)(T_PADDING+H_FRAME-(yAxis.get(i)+Math.abs(Collections.min(yAxis)))/perPixelY);
                y2 = (int)(T_PADDING+H_FRAME-(yAxis.get(i+1)+Math.abs(Collections.min(yAxis)))/perPixelY);
            }else{
                y1 = (int)(T_PADDING+H_FRAME-yAxis.get(i)/perPixelY);
                y2 = (int)(T_PADDING+H_FRAME-yAxis.get(i+1)/perPixelY);
            }

            g2D.setColor(Color.blue);
            g2D.drawLine(x1, y1, x2, y2);
        }

    }//end simulateGraph

    private static void processGUI() {
        Process system = new Process();
        GUI gFrame = new GUI(system.xList(),system.yList());
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
