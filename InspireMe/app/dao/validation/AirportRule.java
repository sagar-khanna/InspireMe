package dao.validation;

import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: steven_johnston
 * Date: 27/03/14
 * Time: 14:37
 * To change this template use File | Settings | File Templates.
 */
public class AirportRule<Payload> implements ValidationRule {
    private static final Pattern IATA_FORMAT = Pattern.compile("^[A-Z]{3}$");
    private String iataCode;

    public AirportRule(String iataCode) {
        this.iataCode = iataCode;
    }

    @Override
    public void enforce() throws ValidationFailure {
        if (!IATA_FORMAT.matcher(iataCode).find()) {
            throw new ValidationFailure("Invalid Iata Code: " + iataCode);
        }
    }
}
