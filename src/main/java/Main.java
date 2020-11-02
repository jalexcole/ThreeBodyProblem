public class Main {

    public static void main(String[] args) {
        BodySystem system = new BodySystem.Builder()
                .setCountAttractingBodies(300)
                .setScaleModifier(5)
                .build();

        int maxStepsSize = 1000;
        int stepCount = 0;

        while(stepCount < maxStepsSize){
            system.step();
            stepCount++;
        }
    }
}
