import java.util.ArrayList;
import java.util.stream.IntStream;

public class Main {
    ArrayList<Body> bodies = new ArrayList<>();
    final double gravitationConstant = 6.6743015e-11;

    public static void main(String[] args) {
        Main problem = new Main();
        problem.initBodies(3);
        System.out.printf("");


    }
    public void initBodies(int count){
        IntStream.range(0, count).forEach(n ->{
            bodies.add(new Body());
        });
    }

    public void gravitationalForce(Body selectedBody) {
        
        for (Body body : bodies) {

        }
    }
    public double calculateDistance (Body body1, Body body2) {
        double dx = body1.getPosition()[0] - body2.getPosition()[0];
        double dy = body1.getPosition()[1] - body2.getPosition()[1];
        double dz = body1.getPosition()[2] - body2.getPosition()[2];
        double distance = Math.sqrt(Math.pow(dx,2) + Math.pow(dy, 2) + Math.pow(dz, 2));

        return distance;
    }

}
