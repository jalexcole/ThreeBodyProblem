import java.util.Random;

public class Numeric {

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
