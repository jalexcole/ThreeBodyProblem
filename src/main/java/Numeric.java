public class Numeric {
    public static double[] addVectors(double[] vector1, double[] vector2){
        double[] totalVector = new double[(vector1.length + vector2.length) / 2];

        for(int i = 0; i < totalVector.length; i++){
            totalVector[i] = vector1[i] + vector2[i];
        }
        return totalVector;
    }
}
