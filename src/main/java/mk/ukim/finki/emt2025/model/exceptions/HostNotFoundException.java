package mk.ukim.finki.emt2025.model.exceptions;

public class HostNotFoundException extends RuntimeException {
    public HostNotFoundException(Long id) {
        super(String.format("Host with id: %d was not found", id));
    }
}
