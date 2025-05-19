package mk.ukim.finki.emt2025.events;

import lombok.Getter;
import mk.ukim.finki.emt2025.model.domain.Host;
import org.springframework.context.ApplicationEvent;

import java.time.LocalDateTime;

@Getter
public class HostEvent extends ApplicationEvent {

    private final LocalDateTime when;
    private final String eventType; // CREATE, UPDATE, DELETE

    public HostEvent(Host source, String eventType) {
        super(source);
        this.when = LocalDateTime.now();
        this.eventType = eventType;
    }
}