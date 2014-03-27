package dao.validation;

public class PassengersValidator implements ValidationRule {
    private int adults;
    private int children;
    private int infants;

    public PassengersValidator(int adults, int children, int infants) {
        this.adults = adults;
        this.children = children;
        this.infants = infants;
    }

    @Override
    public void enforce() throws ValidationFailure {
        if (adults < 1) {
            throw new ValidationFailure("Number of adults must be at least 1");
        }
    }
}
