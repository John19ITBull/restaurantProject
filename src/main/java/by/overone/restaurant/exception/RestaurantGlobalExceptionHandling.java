package by.overone.restaurant.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestaurantGlobalExceptionHandling {

    @ExceptionHandler
    ResponseEntity<RestaurantIncorrectData> handleException(NoSuchRestaurantException exception) {
        RestaurantIncorrectData restaurantIncorrectData = new RestaurantIncorrectData();
        restaurantIncorrectData.setInfo(exception.getMessage());
        return new ResponseEntity<>(restaurantIncorrectData, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<RestaurantIncorrectData> handleException(Exception exception) {
        RestaurantIncorrectData restaurantIncorrectData = new RestaurantIncorrectData();
        restaurantIncorrectData.setInfo(exception.getMessage());

        return new ResponseEntity<>(restaurantIncorrectData, HttpStatus.BAD_REQUEST);
    }
}
