package controllers;

import com.google.common.base.Objects;
import dao.HolidayPayload;
import play.*;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {

    public static Result index() {
        return ok("Hello World");
    }

    public static Result search(
            String fromAirport, String dateFrom, int duration, int numAdults, int numChildren, int numInfants) {
        HolidayPayload holidayPayload =
                new HolidayPayload(fromAirport, dateFrom, duration, numAdults, numChildren, numInfants);

        // TODO Do a search
        return ok(holidayPayload.toString());
    }
}
