import java.security.PublicKey;
import java.util.*;

public class Process {

    private List<Double>xAxis;
    private List<Double>yAxis;

    public Process(){
        xAnalyze();
        yAnalyze();
    };
//horizontal
    public void xAnalyze(){
    xAxis = new ArrayList<>();
    Random r = new Random();


    for(int i=-700; i<0; i++){


        xAxis.add((double)i);






        //xAxis.add((double)Math.toRadians(i*10));
    }
    }
//vertical
    public void yAnalyze(){
        yAxis = new ArrayList<>();
        Random r = new Random();

        for(int i=-700; i<0; i++){
            //yAxis.add((double)i*10);
           yAxis.add((double)i);
/*
            if(i<300)
            yAxis.add((double)Math.random()*100);
            else if(i>300 && i<500)
                yAxis.add((double)Math.random()*400);
            else if(i>500 && i<800)
                yAxis.add((double)Math.random()*1200);
            else
                yAxis.add((double)Math.random()*50);*/
            //yAxis.add((double)Math.sin(xAxis.get(i)));
        }
    }

    public List<Double> xList(){
        return xAxis;
    }

    public List<Double> yList(){
        return yAxis;
    }

    public int getSize(){
        return xAxis.size();
    }

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
}
