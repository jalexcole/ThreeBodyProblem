import java.util.ArrayList;
import java.util.stream.IntStream;

public class Main {
    ArrayList<Body> bodies = new ArrayList<>();
    final double gravitationConstant = 6.6743015e-11;

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
