package by.overone.restaurant.exception;

public class NoSuchRestaurantException extends RuntimeException {
    public NoSuchRestaurantException() {
    }

    public NoSuchRestaurantException(String message) {
        super(message);
    }

    public NoSuchRestaurantException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchRestaurantException(Throwable cause) {
        super(cause);
    }

    public NoSuchRestaurantException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
