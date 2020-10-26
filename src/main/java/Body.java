import java.util.Random;


class Body {
    double[] position = new double[3];
    double[] velocity = new double[3];
    double[] acceleration = new double[3];
    double mass;
    private double modifier = 1;

    Random random = new Random();

    public Body() {
        for (double i : position) {
            i = random.nextDouble() * modifier;
        }
        mass = random.nextDouble() * modifier;
    }

    private void updatePosition() {

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

    public double[] getPosition(){
        return position;
    }
    public double getMass(){
        return mass;
    }

}