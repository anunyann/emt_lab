package mk.ukim.finki.emt2025.service;

import mk.ukim.finki.emt2025.model.Host;
import mk.ukim.finki.emt2025.model.dto.HostCreateDto;
import mk.ukim.finki.emt2025.model.dto.HostDto;

import java.util.List;
import java.util.Optional;

public interface HostService {
    List<Host> findAll();
    Optional<Host> findById(Long id);
    Optional<Host> create(HostCreateDto hostDto);
    Optional<Host> update(Long id, HostCreateDto hostDto);
    void deleteById(Long id);
    HostDto toDto(Host host);
}
