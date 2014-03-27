package dao.entity;

/**
 * Created with IntelliJ IDEA.
 * User: sateeshampolu
 * Date: 27/03/14
 * Time: 15:42
 * To change this template use File | Settings | File Templates.
 */
public class HolidayResult {

    public HolidayResult() {
    }

    public HolidayResult(String lat, String lang, String price, String info) {
        this.lat = lat;
        this.lang = lang;
        this.price = price;
        this.info = info;
    }

    private String lat;
    private String lang;
    private String price;
    private String info;

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
