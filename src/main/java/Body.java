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
        for(int i = 0; i < position.length; i++){
            position[i] += velocity[i];
        }
    }

    private void updateVelocity() {
        velocity[0] += acceleration[0];
        velocity[1] += acceleration[1];
        velocity[2] += acceleration[2];
    }

    public void applyForce(double[] force) {
        acceleration[0] = force[0] / mass;
        acceleration[1] = force[1] / mass;
        acceleration[2] = force[2] / mass;

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

}