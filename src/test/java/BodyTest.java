import org.junit.Test;

import static org.junit.Assert.*;

public class BodyTest{

    @Test
    public void applyForce() {
        Body body = new Body();
        double[] initPosition = body.getPosition();
        double[] initVelocity = body.velocity;
        double[] initAcceleration = body.acceleration;
        body.mass = 1;
        double[] testForce = {1, 1, 1};
        body.applyForce(testForce);
        assertArrayEquals(body.acceleration, testForce);
    }

    private void assertArrayEquals(double[] acceleration, double[] testForce) {
        if(acceleration.length == testForce.length){

        } else {
            fail("Vector Length Equivalency Error");
        }
    }

    @Test
    public void getPosition() {
    }

    @Test
    public void getMass() {
    }
}