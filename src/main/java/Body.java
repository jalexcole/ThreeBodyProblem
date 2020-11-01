import java.util.Random;


class Body {
    /**
     * Body represents a gravitational body that can update itself in a single unit time scale.
     *
     * Inputs should only be for updates and returning position.
     */
    double[] position = new double[3];
    double[] velocity = new double[3];
    double[] acceleration = new double[3];
    double mass;
    private double modifier = 1;
    private boolean attracting = true;

    Random random = new Random();
    
    private int stepSize = 1;

    public Body() {
        init();
    }
    public void init(){
        for(int i = 0; i < position.length; i++){
            position[i] = random.nextDouble() * modifier;
            velocity[i] = 0;
            acceleration[i] = 0;

        }
        mass = random.nextDouble() * modifier;
    }
    public void update(){
        updatePosition();
        updateVelocity();
    }
    private void updatePosition() {
        position = calculatePosition();
    }

    public double[] calculatePosition(){
        double[] futureVelocity = Numeric.addVectors(velocity, Numeric.mulVector(acceleration, stepSize));
        futureVelocity = Numeric.mulVector(futureVelocity, stepSize);
        double[] futurePosition = Numeric.addVectors(position, futureVelocity);
        futurePosition = Numeric.addVectors(futurePosition, Numeric.mulVector(acceleration, Math.pow(stepSize, 2)));
        return futurePosition;
    }
    public double[] calculatePosition(double partialStep){
        double[] futureVelocity = Numeric.addVectors(velocity, Numeric.mulVector(acceleration, stepSize * partialStep));
        futureVelocity = Numeric.mulVector(futureVelocity, stepSize * partialStep);
        double[] futurePosition = Numeric.addVectors(position, futureVelocity);
        futurePosition = Numeric.addVectors(futurePosition, Numeric.mulVector(acceleration, Math.pow(stepSize * partialStep, 2)));
        return futurePosition;
    }


    private void updateVelocity() {
        velocity = Numeric.addVectors(velocity, acceleration);
    }

    public void applyForce(double[] force) {
        acceleration = Numeric.divide(force, mass);
    }
    public void setNonAttracting(){
        attracting = false;
    }
    public double[] getPosition(){
        return position;
    }
    public double getMass(){
        return mass;
    }

    public double[] getMomentum(){

        return null;
    }

}