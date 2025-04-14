package mk.ukim.finki.emt2025.service.application;

import mk.ukim.finki.emt2025.model.dto.HostCreateDto;
import mk.ukim.finki.emt2025.model.dto.HostDto;

import java.util.List;
import java.util.Optional;

public interface HostApplicationService {
    List<HostDto> findAll();
    Optional<HostDto> findById(Long id);
    Optional<HostDto> create(HostCreateDto hostDto);
    Optional<HostDto> update(Long id, HostCreateDto hostDto);
    void deleteById(Long id);
}