import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;

public class BodySystem {
    private ArrayList<Body> attractingBodies = new ArrayList<>();
    private ArrayList<Body> bodies = new ArrayList<>();

    private final double gravitationConstant = 6.6743015e-11;

    private int countAttractingBodies = 3;
    private int nonAttractingCount = 0;

    private double stepSize = 1; // in Seconds
    private int dimensionality = 3;
    private double scaleModifier = 1;

    private int threadPool = Runtime.getRuntime().availableProcessors();

    static class Builder {
        private double stepSize = 1 ;
        private double scaleModifier = 1;
        private int countAttractingBodies = 3;
        private int nonAttractingCount = 0;
        private int dimensionality = 3;

        public Builder(){

        }
        public Builder setStepSize(double stepSize) {
            this.stepSize = stepSize;
            return this;
        }

        public Builder setScaleModifier(double scaleModifier) {
            this.scaleModifier = scaleModifier;
            return this;
        }

        public Builder setCountAttractingBodies(int countAttractingBodies) {
            this.countAttractingBodies = countAttractingBodies;
            return this;
        }

        public Builder setNonAttractingCount(int nonAttractingCount) {
            this.nonAttractingCount = nonAttractingCount;
            return this;
        }

        public Builder setDimensionality(int dimensionality) {
            this.dimensionality = dimensionality;
            return this;
        }

        public BodySystem build(){
            return new BodySystem(this);
        }
    }

    public BodySystem(Builder builder) {
        this.countAttractingBodies = builder.countAttractingBodies;
        this.dimensionality = builder.dimensionality;
        this.nonAttractingCount = builder.nonAttractingCount;
        this.stepSize = builder.stepSize;
        this.scaleModifier = builder.scaleModifier;

        init();
    }

    public void init(){
        IntStream.range(0, countAttractingBodies).parallel().forEach(n -> {
            Body body = new Body.Builder()
                    .setDimensionality(dimensionality)
                    .setStepSize(stepSize)
                    .build();
            attractingBodies.add(body);
        });

        IntStream.range(0, nonAttractingCount).forEach(n -> {
            Body body = new Body.Builder()
                    .setDimensionality(dimensionality)
                    .setAttracting(false)
                    .setStepSize(stepSize)
                    .build();
            bodies.add(body);

        });
    }

    public BodySystem(int countAttractingBodies) {
        IntStream.range(0, countAttractingBodies).forEach(n -> {
            attractingBodies.add(new Body());
        });
    }

    public BodySystem(int countAttractingBodies, int countNonAttractingBodies) {
        IntStream.range(0, countAttractingBodies).forEach(n -> {
            attractingBodies.add(new Body());
        });

        IntStream.range(0, countNonAttractingBodies).forEach(n -> {
            bodies.add(new Body());
        });

    }

    public void step() {
        /* Steps the system through each frame for all bodies in the system */

        IntStream.range(0, attractingBodies.size()).parallel().forEach(n -> {
            attractingBodies.get(n).applyForce(totalForceVector(attractingBodies.get(n)));
        });

        IntStream.range(0, bodies.size()).parallel().forEach(n -> {
            bodies.get(n).applyForce(totalForceVector(bodies.get(n)));
        });

        IntStream.range(0, attractingBodies.size()).forEach(n -> {
            attractingBodies.get(n).update();
        });

        IntStream.range(0, bodies.size()).forEach(n -> {
            bodies.get(n).update();
        });

    }

    public double calculateDistance(Body body1, Body body2) {
        double distance[] = Numeric.subtractVectors(body1.getPosition(), body2.getPosition());
        distance = Numeric.powVector(distance, 2);

        return Math.sqrt(Numeric.sumVector(distance));

    }

    public double[] totalForceVector(Body myBody) {
        double[] totalGravitationalForce = new double[myBody.getPosition().length];
        Arrays.fill(totalGravitationalForce, 0);
        for (Body someBody : attractingBodies) {
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

    public void collision() {
        /* Checks if two objects collide.
           runs if objects are near each other
           Governs rules of collision
           Larger objects is to acquire the mass
           Objects will be made of the same material
           Objects will be given unique names
           Collision will add to an objects collision log existing of names and where objects
           collided.
           collision will add the mass & momentum and recalculate current velocity.

         */
    }


    public double getAverageSystemSize() {
        /* Calculate the distance between all objects from each object and take the average */
        return 0;
    }
}


