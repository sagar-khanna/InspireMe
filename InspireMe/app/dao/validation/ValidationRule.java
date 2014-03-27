package dao.validation;

public interface ValidationRule {
    void enforce() throws ValidationFailure;
}
