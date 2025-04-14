package mk.ukim.finki.emt2025.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AccommodationAlreadyReservedException extends RuntimeException {
    public AccommodationAlreadyReservedException(Long id) {
        super(String.format("Accommodation with id: %d is already reserved", id));
    }
}