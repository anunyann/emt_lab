package mk.ukim.finki.emt2025.service.domain.impl;

import mk.ukim.finki.emt2025.events.HostEvent;
import mk.ukim.finki.emt2025.model.domain.Country;
import mk.ukim.finki.emt2025.model.domain.Host;
import mk.ukim.finki.emt2025.model.dto.HostCreateDto;
import mk.ukim.finki.emt2025.model.exceptions.CountryNotFoundException;
import mk.ukim.finki.emt2025.model.exceptions.HostNotFoundException;
import mk.ukim.finki.emt2025.repository.CountryRepository;
import mk.ukim.finki.emt2025.repository.HostRepository;
import mk.ukim.finki.emt2025.service.domain.HostService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HostServiceImpl implements HostService {

    private final HostRepository hostRepository;
    private final CountryRepository countryRepository;
    private final ApplicationEventPublisher eventPublisher;

    public HostServiceImpl(HostRepository hostRepository,
                           CountryRepository countryRepository,
                           ApplicationEventPublisher eventPublisher) {
        this.hostRepository = hostRepository;
        this.countryRepository = countryRepository;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public List<Host> findAll() {
        return this.hostRepository.findAll();
    }

    @Override
    public Optional<Host> findById(Long id) {
        return this.hostRepository.findById(id);
    }

    @Override
    public Optional<Host> create(HostCreateDto hostDto) {
        Country country = this.countryRepository.findById(hostDto.getCountryId())
                .orElseThrow(() -> new CountryNotFoundException(hostDto.getCountryId()));

        Host host = new Host(hostDto.getName(), hostDto.getSurname(), country);
        Host savedHost = this.hostRepository.save(host);

        // Publish event for host creation
        eventPublisher.publishEvent(new HostEvent(savedHost, "CREATE"));

        return Optional.of(savedHost);
    }

    @Override
    public Optional<Host> update(Long id, HostCreateDto hostDto) {
        Host host = this.hostRepository.findById(id)
                .orElseThrow(() -> new HostNotFoundException(id));

        Country country = this.countryRepository.findById(hostDto.getCountryId())
                .orElseThrow(() -> new CountryNotFoundException(hostDto.getCountryId()));

        host.setName(hostDto.getName());
        host.setSurname(hostDto.getSurname());
        host.setCountry(country);

        Host updatedHost = this.hostRepository.save(host);

        // Publish event for host update
        eventPublisher.publishEvent(new HostEvent(updatedHost, "UPDATE"));

        return Optional.of(updatedHost);
    }

    @Override
    public void deleteById(Long id) {
        Host host = this.hostRepository.findById(id)
                .orElseThrow(() -> new HostNotFoundException(id));

        this.hostRepository.deleteById(id);

        // Publish event for host deletion
        eventPublisher.publishEvent(new HostEvent(host, "DELETE"));
    }
}