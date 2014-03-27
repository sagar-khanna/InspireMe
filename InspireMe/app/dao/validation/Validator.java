package dao.validation;

import dao.entity.HolidayPayload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class Validator {
    private List<ValidationRule> rules;
    private List<String> errors;
    private static final Logger LOGGER = LoggerFactory.getLogger(Validator.class);

    public Validator(HolidayPayload payload) {
        this.rules = new ArrayList<>();
        this.errors = new ArrayList<>();
        this.rules.add(new AirportRule(payload.getFromAirport()));
        this.rules.add(new FromDateRule(payload.getDateFrom()));
        this.rules.add(new DurationValidator(payload.getDuration()));
        this.rules.add(new PassengersValidator(
                payload.getNumAdults(), payload.getNumChildren(), payload.getNumInfants()));
    }

    /**
     * Execute validation on entity
     * @return list of any validation errors
     */
    public List<String> validate() {
        for(ValidationRule rule : rules)
            try {
                rule.enforce();
            } catch (ValidationFailure f) {
                errors.add(f.getMessage());
            }
        return errors;
    }
}
