package su.dreamteam.lonja.trainingdiaryfinal.event;

public class WeightValidationEvent extends ValidationEvent {

    public WeightValidationEvent(boolean isValidationPassed) {
        super(isValidationPassed);
    }
}
