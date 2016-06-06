package su.dreamteam.lonja.trainingdiaryfinal.event;


public class ValidationEvent {

    public boolean isValidationPassed;

    public ValidationEvent(boolean isValidationPassed) {
        this.isValidationPassed = isValidationPassed;
    }
}
