/********************************************
 *                                          *
 * Process.java                             *
 * Project: Graph Interface                 *
 * Details:                                 *
 * Access information from dataset.txt and  *
 * sends the information to GUI.java.       *
 * This class computes all information      * 
 * except the graphics.                     *
 *                                          *    
 ********************************************/
import java.io.File;
import java.io.FileNotFoundException;  
import java.util.*; 
import java.util.Scanner;

public class Process {

    //Global variables
    private List<Double>xAxis;
    private List<Double>yAxis;
    private boolean valid = true;
    private String topic = "NOT AVAILABLE";
    private String xRep = "NOT AVAILABLE";
    private String yRep = "NOT AVAILABLE";
    private int length = 0;

    //constructor
    public Process(){
        xAxis = new ArrayList<>();
        yAxis = new ArrayList<>();
        fileProcess();
        if(length==0)
            valid=false;
        if(!valid)
            backup();
    }

    //Accessing information for dataset.txt
    public void fileProcess(){
        try{
            File dataset = new File("dataset.txt");
            Scanner scan = new Scanner(dataset);
            int lineNum = 1;
            while(scan.hasNextLine() && lineNum <=6){
                if(lineNum == 1){
                    String firstLine = scan.nextLine(); 
                    topic = firstLine.substring(6,firstLine.length()-1);
                }
                else if(lineNum == 2){
                    String secondLine = scan.nextLine(); 
                    length = Integer.parseInt(secondLine.substring(9,secondLine.length()-1));
                }
                else if(lineNum == 3){
                    String thirdLine = scan.nextLine();
                    xRep = thirdLine.substring(2,thirdLine.length()-1);
                }
                else if(lineNum == 4){
                    String fourthLine = scan.nextLine();
                    String val = fourthLine.substring(2,fourthLine.length()-1);
                    String[]valContainer = val.split(",");
                    if(valContainer.length == length) {
                        for (int i = 0; i < valContainer.length; i++) {
                            xAxis.add(Double.parseDouble(valContainer[i]));
                        }
                    }
                    else valid = false;
                }
                else if(lineNum==5){
                    String fourthLine = scan.nextLine();
                    yRep = fourthLine.substring(2,fourthLine.length()-1);
                }
                else if(lineNum == 6){
                    String sixthLine = scan.nextLine();
                    String val = sixthLine.substring(2,sixthLine.length()-1);
                    String[]valContainer = val.split(",");
                    if(valContainer.length == length) {
                        for (int i = 0; i < valContainer.length; i++) {
                            yAxis.add(Double.parseDouble(valContainer[i]));
                        }
                    }else valid = false;
                }
                lineNum++;
            }
            scan.close();
        }catch (FileNotFoundException exp) {
            System.out.println(exp.getMessage());
        }
    }

    //x-axis accessor
    public List<Double> xList(){ return xAxis; }

    //y-axis accessor
    public List<Double> yList(){ return yAxis; }

    //x-axis size
    public int xSize(){ return xAxis.size(); }

    //y-axis size
    public int ySize(){ return yAxis.size(); }

    //min in x-axis
    public Double xMin(){ return Collections.min(xAxis); }

    //max in x-axis
    public Double xMax(){ return Collections.max(xAxis); }

    //min in y-axis
    public Double yMin(){ return Collections.min(yAxis); }

    //max in y-axis
    public Double yMax(){ return Collections.max(yAxis); }

    //sum of x-axis
    public Double sumX(){
        double sum = 0;
        for(int i=0; i<xSize(); i++){
            sum+=xAxis.get(i);
        }
        return sum;
    }

    //sum of y-axis
    public Double sumY(){
        double sum = 0;
        for(int i=0; i<ySize(); i++){
            sum+=yAxis.get(i);
        }
        return sum;
    }

    //mean of x-axis
    public Double meanX(){
        return sumX()/xSize();
    }

    //mean of y-axis
    public Double meanY(){
        return sumY()/ySize();
    }

    //graph type x-axis
    public String growthX(){
        boolean upwards = false;
        boolean downwards = false;
        String evaluation = "";

        for(int i=0; i<ySize()-1; i++){
            if(xAxis.get(i)<xAxis.get(i+1)) {
                upwards = true;
            }
            if(xAxis.get(i)>xAxis.get(i+1)) {
                downwards = true;
            }
        }

        if(upwards && downwards)
            evaluation = "Fluctuation.";
        else if(!upwards && !downwards)
            evaluation = "Stable.";
        else if(upwards)
            evaluation = "Upwards";
        else evaluation = "Downwards";

        return evaluation;
    }

    //graph type y-axis
    public String growthY(){
        boolean upwards = false;
        boolean downwards = false;
        String evaluation = "";

        for(int i=0; i<ySize()-1; i++){
            if(yAxis.get(i)<yAxis.get(i+1)) {
                upwards = true;
            }
            if(yAxis.get(i)>yAxis.get(i+1)) {
                downwards = true;
            }
        }

        if(upwards && downwards)
            evaluation = "Fluctuation.";
        else if(!upwards && !downwards)
            evaluation = "Stable.";
        else if(upwards)
            evaluation = "Upwards";
        else evaluation = "Downwards";

        return evaluation;
    }

    //median of x-axis
    public double medX(){
        boolean even = true;
        int index = 0;
        if(xSize()%2!=0)
            even = false;
        if(even)
            index = (xSize()/2)-1;
        else
            index = ((xSize()+1)/2)-1;
        return xAxis.get(index);
    }

    //median of y-axis
    public double medY(){
        boolean even = true;
        int index = 0;
        if(ySize()%2!=0)
            even = false;
        if(even)
            index = (ySize()/2)-1;
        else
            index = ((ySize()+1)/2)-1;
        return yAxis.get(index);
    }

    //topic
    public String topic(){
        if(topic.equals(""))
            return "NOT AVAILABLE";
        else
            return topic;
    }

    //x-representation
    public String getxRep(){
        if(xRep.equals(""))
            return "NOT AVAILABLE";
        else
            return xRep;
    }

    //y-representation
    public String getyRep(){
        if(yRep.equals(""))
            return "NOT AVAILABLE";
        else
            return yRep;
    }

    //validation confirmation
    public String valid(){
        if(valid)
            return "Validated"; 
        else
            return "FILE CONTAINS ERROR.";
    }

    //Value range
    public int size(){
        return length;
    }

    //empty sets and a equal set
    public void backup(){
        for(int i=0; i<xSize(); i++){
            xAxis.remove(i);
        }

        for(int i=0; i<ySize(); i++){
            yAxis.remove(i);
        }

        for(int i=0; i<100; i++){
            xAxis.add(1.1);
            yAxis.add(1.1);
        }
    }
}
