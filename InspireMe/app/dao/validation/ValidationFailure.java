package dao.validation;

public class ValidationFailure extends Exception {
    public ValidationFailure(String message) {
        super(message);
    }
}
