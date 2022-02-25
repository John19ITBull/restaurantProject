package by.overone.restaurant.exception;

public class RestaurantIncorrectData {
    private String info;

    public RestaurantIncorrectData() {}

    public RestaurantIncorrectData(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
