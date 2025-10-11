package be.kdg.keepdishesgoing.security.api.dtos;

public class UnsecuredMessageDto {

    private static final String message = "I'm unsecure";

    public String getMessage() {
        return message;
    }
}
