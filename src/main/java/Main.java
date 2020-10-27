import java.util.ArrayList;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {
        BodySystem system = new BodySystem(20000);
        int maxStepsSize = 10000;
        int stepCount = 0;

        while(stepCount < maxStepsSize){
            system.step();
            stepCount++;
        }
    }
}
