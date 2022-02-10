/********************************************
 *                                          *
 * GUI.java                                 *
 * Project: Graph Interface                 *
 * Details:                                 *
 * Receives value from Process.java and     *
 * generates the graph inside graph frame.  *
 * All graphical calculations are done in   *
 * GUI.java and Information Panel are done  *
 * in Process.java                          *
 *                                          *
 ********************************************/
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

    //Dimension controller
    private static final int WIDTH = 1000; // Length of Interface
    private static final int HEIGHT = 800; // Height of Interface
    private final int L_PADDING = 25; //Left padding for graph frame
    private final int T_PADDING = 25; //Top padding for graph frame
    private final int W_FRAME = 900; // Length of graph frame
    private final int H_FRAME = 400; // Height of graph frame
    private final int Y_GRID = 40;//Num of horizontal grid
    private final int X_GRID = 90;//num of vertical grid
    private final int POINT_INDICATOR_LENGTH = 5;//Each five cube there will be a blue indicator on graph

    //Graph value range
    DecimalFormat dFormat = new DecimalFormat("#.##");//Up to 2 decimal
    private Double xMin = 0.0;
    private Double yMin = 0.0;
    private Double xMax = 1000.0;
    private Double yMax = 1000.0;
    private int quantity;

    //Containers & object
    private Process data;//Process object
    private List<Double>xAxis;//X axis points
    private List<Double>yAxis;//Y axis points

    //Other
    private final Color GRID_COLOR = new Color(200, 200, 200, 200); // Gray

    //Software details
    private static final String TITLE = "Graph Interface";
    private final String VERSION  = "[Version 1.0]";
    private final String YEAR = "[Year2022]";

    //Constructor
    //Initializing xy axis and objects
    public GUI(Process data, List<Double>xAxis, List<Double>yAxis){
        this.data = data;
        this.xAxis = xAxis;
        this.yAxis = yAxis;
    }//End GUI

    protected void paintComponent(Graphics gph){
        super.paintComponent(gph);
        Graphics2D g2D = (Graphics2D) gph;
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //Simulation frame
        g2D.setColor(Color.white);//Graph frame background: white
        g2D.fillRect(WIDTH-L_PADDING-W_FRAME, T_PADDING, W_FRAME, H_FRAME);
        g2D.setColor(Color.BLACK);

        //Passing max min value for the graph
        quantity = data.size();
        xMax = data.xMax();
        xMin = data.xMin();
        yMax = data.yMax();
        yMin = data.yMin();

        //Y grid
        int xIn, yIn, xFin, yFin; //x & y coordinates
        int gridInterval;
        final int Y_POINT_INTERVAL = 10;
        String point;
        Double perSqr = (Math.abs(yMin)+Math.abs(yMax))/Y_GRID;//Value per square
        xIn = WIDTH-L_PADDING-W_FRAME;
        gridInterval = H_FRAME/Y_GRID;
        xFin = xIn+W_FRAME;

        for(int i=0; i<Y_GRID+1; i++){
            yIn = yFin = T_PADDING+i*gridInterval;
            g2D.setColor(GRID_COLOR);
            g2D.drawLine(xIn, yIn, xFin, yFin);//Horizontal grid lines
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

        //x grid
        //Re-use Y grid variables
        final int X_POINT_INTERVAL = 10;
        yIn = T_PADDING;
        yFin=yIn+H_FRAME;
        gridInterval = W_FRAME/X_GRID;
        for(int i=0; i<=X_GRID; i++){
            xIn = WIDTH-L_PADDING-W_FRAME+i*gridInterval;
            xFin = xIn;
            g2D.setColor(GRID_COLOR);
            g2D.drawLine(xIn, yIn, xFin, yFin);//Vertical grid lines
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
        g2D.drawLine(WIDTH-L_PADDING-W_FRAME, T_PADDING+H_FRAME, WIDTH-L_PADDING-W_FRAME+W_FRAME,T_PADDING+H_FRAME);//Horizontal
        g2D.drawLine(WIDTH-L_PADDING-W_FRAME,yIn,WIDTH-L_PADDING-W_FRAME,yFin);//Vertical

        //Generating graph based on given values
        //Compress or resize the values into the frame
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
            g2D.setColor(Color.red);
            g2D.drawLine(x1, y1, x2, y2);//Graph line
        }

        //Information Panel
        final String PANEL = "Information Panel";
        String p1 = "Topic: "+data.topic();
        String p2 = "x represents: "+data.getxRep();
        String p3 = "y represents: "+data.getyRep();
        String p4 = "Max(x): "+data.xMax();
        String p5 = "Min(x): "+data.xMin();
        String p6 = "Max(y): "+data.yMax();
        String p7 = "Min(y): "+data.yMin();
        String p8 = "Med(x): "+data.medX();
        String p9 = "Med(y): "+data.medY();
        String p10 = "FILE TEST: "+data.valid();
        String p11 = "Mean(x)/Avg: "+data.meanX();
        String p12 = "Mean (y)/Avg: "+data.meanY();
        String p13 = "Growth (x): "+data.growthX();
        String p14 = "Growth (y): "+data.growthY();
        String p15 = "Total x value: "+data.xSize();
        String p16 = "Total y value: "+data.ySize();
        String p17 = "Sum(x): "+data.sumX();
        String p18 = "Sum(y): "+data.sumY();
        String p19 = "Optional: "; //Optional if user wants to include something
        String p20 = "Software Information: "+VERSION+" "+YEAR;

        g2D.setColor(Color.BLACK);
        FontMetrics metrics = g2D.getFontMetrics();

        //Line height is previous+25
        g2D.drawString(PANEL, WIDTH-W_FRAME, T_PADDING+H_FRAME+metrics.getHeight()+50);
        g2D.drawString(p1, WIDTH-W_FRAME, T_PADDING+H_FRAME+metrics.getHeight()+75);
        g2D.drawString(p2, WIDTH-W_FRAME, T_PADDING+H_FRAME+metrics.getHeight()+100);
        g2D.drawString(p3, WIDTH-W_FRAME, T_PADDING+H_FRAME+metrics.getHeight()+125);
        g2D.drawString(p4, WIDTH-W_FRAME, T_PADDING+H_FRAME+metrics.getHeight()+150);
        g2D.drawString(p5, WIDTH-W_FRAME, T_PADDING+H_FRAME+metrics.getHeight()+175);
        g2D.drawString(p6, WIDTH-W_FRAME, T_PADDING+H_FRAME+metrics.getHeight()+200);
        g2D.drawString(p7, WIDTH-W_FRAME, T_PADDING+H_FRAME+metrics.getHeight()+225);
        g2D.drawString(p8, WIDTH-W_FRAME, T_PADDING+H_FRAME+metrics.getHeight()+250);
        g2D.drawString(p9, WIDTH-W_FRAME, T_PADDING+H_FRAME+metrics.getHeight()+275);
        g2D.drawString(p10, WIDTH-W_FRAME, T_PADDING+H_FRAME+metrics.getHeight()+300);
        g2D.drawString(p11, WIDTH-(W_FRAME/9)*5, T_PADDING+H_FRAME+metrics.getHeight()+75);
        g2D.drawString(p12, WIDTH-(W_FRAME/9)*5, T_PADDING+H_FRAME+metrics.getHeight()+100);
        g2D.drawString(p13, WIDTH-(W_FRAME/9)*5, T_PADDING+H_FRAME+metrics.getHeight()+125);
        g2D.drawString(p14, WIDTH-(W_FRAME/9)*5, T_PADDING+H_FRAME+metrics.getHeight()+150);
        g2D.drawString(p15, WIDTH-(W_FRAME/9)*5, T_PADDING+H_FRAME+metrics.getHeight()+175);
        g2D.drawString(p16, WIDTH-(W_FRAME/9)*5, T_PADDING+H_FRAME+metrics.getHeight()+200);
        g2D.drawString(p17, WIDTH-(W_FRAME/9)*5, T_PADDING+H_FRAME+metrics.getHeight()+225);
        g2D.drawString(p18, WIDTH-(W_FRAME/9)*5, T_PADDING+H_FRAME+metrics.getHeight()+250);
        g2D.drawString(p19, WIDTH-(W_FRAME/9)*5, T_PADDING+H_FRAME+metrics.getHeight()+275);
        g2D.drawString(p20, WIDTH-(W_FRAME/9)*5, T_PADDING+H_FRAME+metrics.getHeight()+300);
    }//end paintComponent

    private static void processGUI() {
        //Creating objects & dimension/title
        Process system = new Process();//Process object
        GUI gFrame = new GUI(system,system.xList(),system.yList());//GUI object
        gFrame.setPreferredSize(new Dimension(WIDTH, HEIGHT));//Interface dimension
        JFrame frame = new JFrame(TITLE);//Title
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(gFrame);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }//End processGUI

    public static void main(String[]args){
        //Run software
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                processGUI();
            }
        });
    }//End main
