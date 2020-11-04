import java.util.Random;

public class Numeric {
    double[] numericVector;

    public double[] getVector(){
        return numericVector;
    }


    public void array(double[] vector){
        numericVector = (double[])vector;
    }

    public void linspace(double start, double stop, int steps){
        double stepSize = (stop - start) / ((double)steps - 1.0);
        double[] vector = new double[steps];
        vector[0] = start;

        for(int i = 1; i < steps; i++){
            vector[i] = vector[i - 1] + stepSize;
        }

        numericVector = vector;
    }

    public void random(int length){
        Random random = new Random();
        numericVector = new double[length];
        for(double i: numericVector) i = random.nextDouble();
    }

    public void zeros(int length){
        numericVector = new double[length];
        for(double i: numericVector) i = 0;
    }

    public void multiply(double value){
        for(int i = 0; i < numericVector.length; i++){
            numericVector[i] *= value;
        }
    }

    public void multiply(double[] vector){
        if(checkLength(numericVector, vector)){
            for(int i = 0; i < numericVector.length; i++){
                numericVector[i] *= vector[i];
            }
        }
    }

    public double dot(double[] vector){
        double[] dotVector;
        if(checkLength(numericVector, vector)){
            dotVector = new double[numericVector.length];
            for(int i = 0; i < numericVector.length; i++){
                dotVector[i] = numericVector[i] * vector[i];
            }
            return sumVector(dotVector);
        } else {
            return 0;
        }

    }

    public void cross(double[] vector){

    }

    public void pow(double value){
        for(double i: numericVector){
            Math.pow(i, value);
        }
    }

    public boolean checkLength(double[] vector1, double[] vector2){
        if(vector1.length != vector2.length){
            System.err.println("Vector array size mismatch");
            return false;
        } else {
            return true;
        }
    }

    public static double[] addVectors(double[] vector1, double[] vector2){
        double[] totalVector = new double[(vector1.length + vector2.length) / 2];

        for(int i = 0; i < totalVector.length; i++){
            totalVector[i] = vector1[i] + vector2[i];
        }
        return totalVector;
    }

    public static double[] subtractVectors(double[] vector1, double[] vector2){
        double[] totalVector = new double[(vector1.length + vector2.length) / 2];

        for(int i = 0; i < totalVector.length; i++){
            totalVector[i] = vector1[i] - vector2[i];
        }
        return totalVector;
    }

    public static  double[] mulVector(double[] vector1, double value){
        for(int i = 0; i < vector1.length; i++){
            vector1[i] *= value;
        }
        return vector1;
    }

    public static double[] sqrtVector(double[] vector){
        for(int i = 0; i < vector.length; i++){
            vector[i] = Math.sqrt(vector[i]);
        }
        return vector;
    }

    public static double[] powVector(double vector[], double exponent){
        double[] newVector = new double[vector.length];
        for(int i = 0; i < vector.length; i++){
            newVector[i] = Math.pow(vector[i], exponent);
        }
        return newVector;
    }
    public static double[] divide(double[] vector, double value){
        double[] newVector = new double[vector.length];
        for(int i = 0; i < vector.length; i++){
            newVector[i] = vector[i] / value;
        }
        return newVector;
    }

    public static double sumVector(double[] vector){
        double sum = 0;
        for(double v: vector){
            sum += v;
        }
        return sum;
    }

    public static double[] randomVector(int length){
        double[] vector = new double[length];
        for(int i = 0; i < length; i++){
            vector[i] = new Random().nextDouble();
        }
        return vector;
    }
}
