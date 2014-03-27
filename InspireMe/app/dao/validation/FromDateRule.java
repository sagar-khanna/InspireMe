package dao.validation;

import org.joda.time.DateTime;

public class FromDateRule implements ValidationRule{
    private String fromDate;

    public FromDateRule(String fromDate) {
        this.fromDate = fromDate;
    }

    @Override
    public void enforce() throws ValidationFailure {
        try {
            DateTime dateTime = DateTime.parse(fromDate);
        } catch (IllegalArgumentException ex) {
            throw new ValidationFailure("Invalid from date format :" + fromDate);
        }
    }
}
