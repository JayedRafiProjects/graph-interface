import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.Scanner;

public class Process {

    private List<Double>xAxis;
    private List<Double>yAxis;
    private boolean valid = true;
    private String topic = "NOT AVAILABLE";
    private String xRep = "NOT AVAILABLE";
    private String yRep = "NOT AVAILABLE";
    private int length = 0;
    public Process(){
        xAxis = new ArrayList<>();
        yAxis = new ArrayList<>();
        fileProcess();
        System.out.println(valid());
    };

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



    /*
    public void yAnalyze(){

        Random r = new Random();
        for(int i=0; i<100; i++){

         yAxis.add((double)Math.sin(xAxis.get(i)));

        }

    }
*/
    public List<Double> xList(){
        return xAxis;
    }

    public List<Double> yList(){
        return yAxis;
    }

    public int xSize(){ return xAxis.size(); }

    public int ySize(){ return yAxis.size(); }

    public Double xMin(){
        return Collections.min(xAxis);
    }

    public Double xMax(){
        return Collections.max(xAxis);
    }

    public Double yMin(){
        return Collections.min(yAxis);
    }

    public Double yMax(){
        return Collections.max(yAxis);
    }

    public Double sumX(){
        double sum = 0;
        for(int i=0; i<xSize(); i++){
            sum+=xAxis.get(i);
        }
        return sum;
    }

    public Double sumY(){
        double sum = 0;
        for(int i=0; i<ySize(); i++){
            sum+=yAxis.get(i);
        }
        return sum;
    }

    //sum/size
    public Double meanX(){
        return sumX()/xSize();
    }

    public Double meanY(){
        return sumY()/ySize();
    }

    public String growthX(){
        boolean upwards = true;
        boolean downwards = true;
        boolean stable = true;
        String evaluation = "";

        //check upwards
        for(int i=0; i<xSize()-1; i++){
            if(!(xAxis.get(i)<xAxis.get(i+1)))
                upwards = false;
        }

        //check downwards
        for(int i=0; i<xSize()-1; i++){
            if(!(xAxis.get(i)>xAxis.get(i+1)))
                downwards = false;
        }

        //check stable
        for(int i=0; i<xSize()-1; i++){
            if(!(xAxis.get(i)==xAxis.get(i+1))){
                stable = false;
            }
        }

        if((!stable && !upwards && !downwards)||(stable && upwards && downwards))
            evaluation = "Fluctuation.";
        else if(upwards)
            evaluation = "Upward.";
        else if(downwards)
            evaluation = "Downward.";
        else if(stable)
            evaluation = "Stable.";

        return evaluation;
    }

    public String growthY(){
        boolean upwards = true;
        boolean downwards = true;
        boolean stable = true;
        String evaluation = "";

        //check upwards
        for(int i=0; i<ySize()-1; i++){
            if(!(yAxis.get(i)<yAxis.get(i+1)))
                upwards = false;
        }

        //check downwards
        for(int i=0; i<ySize()-1; i++){
            if(!(yAxis.get(i)>yAxis.get(i+1)))
                downwards = false;
        }

        //check stable
        for(int i=0; i<xSize()-1; i++){
            if(!(yAxis.get(i)==yAxis.get(i+1))){
                stable = false;
            }
        }

        if((!stable && !upwards && !downwards)||(stable && upwards && downwards))
            evaluation = "Fluctuation.";
        else if(upwards)
            evaluation = "Upward.";
        else if(downwards)
            evaluation = "Downward.";
        else if(stable)
            evaluation = "Stable.";

        return evaluation;
    }

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

    public String topic(){

            return topic;
    }

    public String getxRep(){
        return xRep;
    }

    public String getyRep(){
        return yRep;
    }

    public String valid(){
        if(valid)
            return "Validated";
        else return "FILE CONTAINS ERROR. MISSING OR EXTRA VALUE ON X OR Y.";
    }
}
