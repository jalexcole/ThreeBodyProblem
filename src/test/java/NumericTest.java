import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class NumericTest {

    @Test
    public void array() {

        double[] vector = {1, 1, 1};

        Numeric numeric = new Numeric().array(vector);


        assertTrue(Arrays.equals(vector, numeric.getVector()));
    }

    @Test
    public void linspace() {
    }

    @Test
    public void random() {
    }

    @Test
    public void zeros() {
    }

    @Test
    public void multiply() {
    }

    @Test
    public void testMultiply() {
    }

    @Test
    public void dot() {
    }

    @Test
    public void cross() {
    }

    @Test
    public void pow() {
    }

    @Test
    public void testArray() {
    }

    @Test
    public void testMultiply1() {
    }

    @Test
    public void testDot() {
    }

    @Test
    public void testCross() {
    }

    @Test
    public void checkLength() {
    }

    @Test
    public void addVectors() {
    }

    @Test
    public void subtractVectors() {
    }

    @Test
    public void mulVector() {
    }

    @Test
    public void sqrtVector() {
    }

    @Test
    public void powVector() {
    }

    @Test
    public void divide() {
    }

    @Test
    public void sumVector() {
    }

    @Test
    public void randomVector() {
    }
}