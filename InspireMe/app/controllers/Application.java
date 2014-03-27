package controllers;

import dao.entity.HolidayPayload;
import dao.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.mvc.*;

import java.util.List;

public class Application extends Controller {
    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    public static Result index() {
        return ok("Hello World");
    }

    public static Result search(
            String fromAirport, String dateFrom, int duration, int numAdults, int numChildren, int numInfants) {
        HolidayPayload holidayPayload =
                new HolidayPayload(fromAirport, dateFrom, duration, numAdults, numChildren, numInfants);
        Validator validator = new Validator(holidayPayload);
        List<String> errors = validator.validate();
        if (!errors.isEmpty()) {
            return badRequest(errors.toString());
        }
        // TODO Do a search
        return ok(holidayPayload.toString());
    }
}
