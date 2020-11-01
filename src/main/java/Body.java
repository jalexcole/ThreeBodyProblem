import java.util.Random;


class Body {
    /**
     * Body represents a gravitational body that can update itself in a single unit time scale.
     * <p>
     * Inputs should only be for updates and returning position.
     */
    private int dimensionality = 3;
    private double[] position = new double[dimensionality];

    // Set as public due to outside use
    protected double[] velocity = new double[dimensionality];
    protected double[] acceleration = new double[dimensionality];
    protected double mass;

    private double modifier = 1;
    private boolean attracting = true;
    private double stepSize = 1;

    private Random random = new Random();

    static class Builder {
        private Random random = new Random();
        private int dimensionality;
        private double modifier;
        private double stepSize;

        public void setRandom(Random random) {
            this.random = random;
        }

        public void setDimensionality(int dimensionality) {
            this.dimensionality = dimensionality;
        }

        public void setModifier(double modifier) {
            this.modifier = modifier;
        }

        public void setStepSize(double stepSize) {
            this.stepSize = stepSize;
        }

        public Body build(){
            return new Body(this);
        }
    }

    public Body(Builder builder){
        this.random = builder.random;
        this.dimensionality = builder.dimensionality;
        this.modifier = builder.modifier;
        this.stepSize =builder.stepSize;
        init();
    }

    public Body() {
        init();
    }

    public Body(int dimensionality) {
        this.dimensionality = dimensionality;
        init();
    }


    public void init() {
        for (int i = 0; i < dimensionality; i++) {
            position[i] = random.nextDouble() * modifier;
            velocity[i] = 0;
            acceleration[i] = 0;

        }
        mass = random.nextDouble() * modifier;
    }

    public void update() {
        updatePosition();
        updateVelocity();
    }

    private void updatePosition() {
        position = calculatePosition();
    }

    public double[] calculatePosition() {
        /*
         * This function is for the use of Euler's method for solving the differential equation.
         */
        double[] futureVelocity = Numeric.addVectors(velocity, Numeric.mulVector(acceleration, stepSize));
        futureVelocity = Numeric.mulVector(futureVelocity, stepSize);
        double[] futurePosition = Numeric.addVectors(position, futureVelocity);
        futurePosition = Numeric.addVectors(futurePosition, Numeric.mulVector(acceleration, Math.pow(stepSize, 2)));
        return futurePosition;
    }

    public double[] calculatePosition(double partialStep) {
        double[] futureVelocity = Numeric.addVectors(velocity, Numeric.mulVector(acceleration, stepSize * partialStep));
        futureVelocity = Numeric.mulVector(futureVelocity, stepSize * partialStep);
        double[] futurePosition = Numeric.addVectors(position, futureVelocity);
        futurePosition = Numeric.addVectors(futurePosition, Numeric.mulVector(acceleration, Math.pow(stepSize * partialStep, 2)));
        return futurePosition;
    }


    private void updateVelocity() {
        velocity = Numeric.addVectors(velocity, Numeric.mulVector(acceleration, stepSize));
    }

    public void applyForce(double[] force) {
        acceleration = Numeric.divide(force, mass);
    }

    public void setNonAttracting() {
        attracting = false;
    }

    public double[] getPosition() {
        return position;
    }

    public double getMass() {
        return mass;
    }

    public double[] getVelocity() {
        return velocity;
    }

    public double[] getAcceleration() {
        return acceleration;
    }

    public void setDimensionality(int dimensionality) {
        this.dimensionality = dimensionality;
    }

    public double[] getMomentum() {
        return Numeric.mulVector(velocity, mass);
    }

    public void setStepSize(double stepSize) {
        this.stepSize = stepSize;
    }

    public void setModifier(double modifier) {
        this.modifier = modifier;
    }

}