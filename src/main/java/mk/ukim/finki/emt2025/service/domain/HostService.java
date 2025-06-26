package mk.ukim.finki.emt2025.service.domain;

import mk.ukim.finki.emt2025.model.domain.Host;
import mk.ukim.finki.emt2025.model.dto.HostCreateDto;

import java.util.List;
import java.util.Optional;

public interface HostService {
    List<Host> findAll();
    Optional<Host> findById(Long id);
    Optional<Host> create(HostCreateDto hostDto);
    Optional<Host> update(Long id, HostCreateDto hostDto);
    void deleteById(Long id);
}