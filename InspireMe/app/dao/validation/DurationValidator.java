package dao.validation;

public class DurationValidator implements ValidationRule {
    private int duration;

    public DurationValidator(int duration) {
        this.duration = duration;
    }

    @Override
    public void enforce() throws ValidationFailure {
        if (duration < 2) {
            throw new ValidationFailure("Holiday must last for at least 1 day: " + duration);
        }
    }
}
