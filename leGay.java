import java.util.*;

public class leGay {
    public static void main(String[] args) {

        Random rnd = new Random();

        StdDraw.setScale();
        StdDraw.text(0.5, 0.5, "Gay");
        StdDraw.setPenRadius(0.01);
        double whatever = 0.05;

        while(true){
            whatever = 0.05;
            for (int i = 0; whatever < 1; i++) {
                whatever += 0.01;
    
                StdDraw.setPenColor(rnd.nextInt(100, 256), rnd.nextInt(100, 256),
                        rnd.nextInt(100, 256));
                StdDraw.square(0.5, 0.5, whatever);
            }
        }
    }
}
