package dao.entity;

import com.google.common.base.Objects;

public class HolidayPayload {
    private String fromAirport;
    private String dateFrom;
    private int duration;
    private int numAdults;
    private int numChildren;
    private int numInfants;

    public HolidayPayload (
            String fromAirport, String dateFrom, int duration, int numAdults, int numChildren, int numInfants) {
        this.fromAirport = fromAirport;
        this.dateFrom = dateFrom;
        this.duration = duration;
        this.numAdults = numAdults;
        this.numChildren = numChildren;
        this.numInfants = numInfants;
    }

    public String getFromAirport() {
        return fromAirport;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public int getDuration() {
        return duration;
    }

    public int getNumAdults() {
        return numAdults;
    }

    public int getNumChildren() {
        return numChildren;
    }

    public int getNumInfants() {
        return numInfants;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("From Airport", this.fromAirport)
                .add("Date From", this.dateFrom)
                .add("Duration", this.duration)
                .add("Num Adults", this. numAdults)
                .add("Num Children", this.numChildren)
                .add("Num Infants", this.numInfants)
                .toString();
    }
}
