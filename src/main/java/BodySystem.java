import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;

public class BodySystem {
    ArrayList<Body> attractingBodies = new ArrayList<>();
    ArrayList<Body> bodies = new ArrayList<>();

    final double gravitationConstant = 6.6743015e-11;

    public BodySystem() {
        for(int i = 0; i < 3; i++) attractingBodies.add(new Body());
    }

    public BodySystem(int countAttractingBodies){
        IntStream.range(0, countAttractingBodies).forEach(n ->{
            attractingBodies.add(new Body());
        });
    }

    public BodySystem(int countAttractingBodies, int countNonAttractingBodies){
        IntStream.range(0, countAttractingBodies).forEach(n ->{
            attractingBodies.add(new Body());
        });

        IntStream.range(0, countNonAttractingBodies).forEach(n ->{
            bodies.add(new Body());
        });

    }

    public void step(){
        /* Steps the system through each frame for all bodies in the system */
        IntStream.range(0, attractingBodies.size()).forEach(n ->{
            attractingBodies.get(n).applyForce(totalForceVector(attractingBodies.get(n)));
        });

        IntStream.range(0, bodies.size()).forEach(n ->{
            bodies.get(n).applyForce(totalForceVector(bodies.get(n)));
        });

        IntStream.range(0, attractingBodies.size()).forEach(n ->{
            attractingBodies.get(n).update();
        });

        IntStream.range(0, bodies.size()).forEach(n ->{
            bodies.get(n).update();
        });

    }

    public double calculateDistance (Body body1, Body body2) {
        double dx = body1.getPosition()[0] - body2.getPosition()[0];
        double dy = body1.getPosition()[1] - body2.getPosition()[1];
        double dz = body1.getPosition()[2] - body2.getPosition()[2];
        double distance = Math.sqrt(Math.pow(dx,2) + Math.pow(dy, 2) + Math.pow(dz, 2));

        return distance;
    }

    public double[] totalForceVector(Body myBody){
        double[] totalGravitationalForce = new double[myBody.getPosition().length];
        Arrays.fill(totalGravitationalForce, 0);
        for(Body someBody: attractingBodies){
            try {
                totalGravitationalForce =
                        Numeric.addVectors(totalGravitationalForce, gravitationalForce(myBody, someBody));
            } catch (ArithmeticException e) {

            }
        }
        return totalGravitationalForce;
    }

    public double[] gravitationalForce(Body body1, Body body2){
        double[] force = new double[body1.getPosition().length];
        for(int i = 0; i < force.length; i++){
            force[i] = gravitationConstant * body1.getMass() * body2.getMass()
                    / Math.pow(calculateDistance(body1, body2), 2);
        }
        return force;
    }

    public double getAverageSystemSize(){
        /* Calculate the distance between all objects from each object and take the average */
        return 0;
    }
}
