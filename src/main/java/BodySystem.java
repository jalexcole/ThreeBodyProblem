import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;

public class BodySystem {
    ArrayList<Body> attractingBodies = new ArrayList<>();
    ArrayList<Body> bodies = new ArrayList<>();

    final double gravitationConstant = 6.6743015e-11;

    int countAttractingBodies = 3;
    int nonAttractingCount = 0;

    double stepSize = 1;
    int dimensionality = 3;
    double scaleModifier = 1;

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
        IntStream.range(0, countAttractingBodies).forEach(n -> {
            attractingBodies.add(new Body(dimensionality));
            attractingBodies.get(n).setStepSize(stepSize);
        });

        IntStream.range(0, nonAttractingCount).forEach(n -> {
            bodies.add(new Body(dimensionality));
            bodies.get(n).setStepSize(stepSize);
            bodies.get(n).setNonAttracting();
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
        IntStream.range(0, attractingBodies.size()).forEach(n -> {
            attractingBodies.get(n).applyForce(totalForceVector(attractingBodies.get(n)));
        });

        IntStream.range(0, bodies.size()).forEach(n -> {
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

    }


    public double getAverageSystemSize() {
        /* Calculate the distance between all objects from each object and take the average */
        return 0;
    }
}


