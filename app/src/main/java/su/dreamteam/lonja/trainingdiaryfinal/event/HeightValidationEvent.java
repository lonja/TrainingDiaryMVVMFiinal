package su.dreamteam.lonja.trainingdiaryfinal.event;

public class HeightValidationEvent extends ValidationEvent {

    public HeightValidationEvent(boolean isValidationPassed) {
        super(isValidationPassed);
    }
}
