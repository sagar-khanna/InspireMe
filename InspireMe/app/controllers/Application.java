package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import controllers.elasticsearch.HolidaysAccessor;
import dao.entity.HolidayPayload;
import dao.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.api.templates.Html;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.main;

import java.util.List;

public class Application extends Controller {
    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    public static Result index() {
        return ok(main.render("Inspire Me Search", new Html(null)));
    }

    public static Result search(
            String fromAirport, String dateFrom, int duration, int numAdults, int numChildren, int numInfants) throws JsonProcessingException {
        HolidayPayload holidayPayload =
                new HolidayPayload(fromAirport, dateFrom, duration, numAdults, numChildren, numInfants);
        Validator validator = new Validator(holidayPayload);
        List<String> errors = validator.validate();
        if (!errors.isEmpty()) {
            return badRequest(errors.toString());
        }
        // TODO Do a search

        HolidaysAccessor accessor = new HolidaysAccessor();
        String holidays = accessor.getHolidays(holidayPayload);
        return ok(holidays);
    }
}
