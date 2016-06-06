package su.dreamteam.lonja.trainingdiaryfinal.event;

public class NameValidationEvent extends ValidationEvent {

    public NameValidationEvent(boolean isValidationPassed) {
        super(isValidationPassed);
    }
}
