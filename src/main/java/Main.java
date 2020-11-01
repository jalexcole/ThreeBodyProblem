public class Main {

    public static void main(String[] args) {
        BodySystem system = new BodySystem.Builder()
                .setCountAttractingBodies(3)
                .setScaleModifier(5)
                .build();

        int maxStepsSize = 10;
        int stepCount = 0;

        while(stepCount < maxStepsSize){
            system.step();
            stepCount++;
        }
    }
}
