import java.util.ArrayList;
import java.util.Arrays;


public class Worker implements Runnable{
    ArrayList<Body> bodies = new ArrayList<>();

    ArrayList<Body> workerBodies = new ArrayList<>();
    int startIndex;
    int stopIndex;
    int frame;

    private final double gravitationConstant = 6.6743015e-11;

    @Override
    public void run() {

    }

    public double calculateDistance(Body body1, Body body2) {
        double distance[] = Numeric.subtractVectors(body1.getPosition(), body2.getPosition());
        distance = Numeric.powVector(distance, 2);

        return Math.sqrt(Numeric.sumVector(distance));

    }

    public double[] totalForceVector(Body myBody) {
        double[] totalGravitationalForce = new double[myBody.getPosition().length];
        Arrays.fill(totalGravitationalForce, 0);
        for (Body someBody : bodies) {
            try {
                totalGravitationalForce =
                        Numeric.addVectors(totalGravitationalForce, gravitationalForce(myBody, someBody));
            } catch (ArithmeticException e) {

            }
        }
        return totalGravitationalForce;
    }

    public double[] gravitationalForce(Body body1, Body body2) {
        double[] force = new double[body1.getPosition().length];
        for (int i = 0; i < force.length; i++) {
            force[i] = gravitationConstant * body1.getMass() * body2.getMass()
                    / Math.pow(calculateDistance(body1, body2), 2);
        }
        return force;
    }
}
