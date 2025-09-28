package be.kdg.keepdishesgoing.restaurant.domain.exceptions;

public class UnauthorizedOwnerException extends RuntimeException {
    public UnauthorizedOwnerException(String message) {
        super(message);
    }
}
