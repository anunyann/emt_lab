package mk.ukim.finki.emt2025.listeners;

import mk.ukim.finki.emt2025.events.HostEvent;
import mk.ukim.finki.emt2025.repository.HostsPerCountryViewRepository;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class HostEventListener {

    private final HostsPerCountryViewRepository hostsPerCountryViewRepository;

    public HostEventListener(HostsPerCountryViewRepository hostsPerCountryViewRepository) {
        this.hostsPerCountryViewRepository = hostsPerCountryViewRepository;
    }

    @EventListener
    public void onHostEvent(HostEvent event) {
        // Refresh the materialized view whenever a host is created, updated, or deleted
        hostsPerCountryViewRepository.refreshMaterializedView();
    }
}