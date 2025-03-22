package mk.ukim.finki.emt2025.web;

import mk.ukim.finki.emt2025.model.dto.HostCreateDto;
import mk.ukim.finki.emt2025.model.dto.HostDto;
import mk.ukim.finki.emt2025.service.HostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/hosts")
public class HostRestController {

    private final HostService hostService;

    public HostRestController(HostService hostService) {
        this.hostService = hostService;
    }

    @GetMapping
    public List<HostDto> getAllHosts() {
        return this.hostService.findAll()
                .stream()
                .map(this.hostService::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HostDto> getHost(@PathVariable Long id) {
        return this.hostService.findById(id)
                .map(host -> ResponseEntity.ok(this.hostService.toDto(host)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<HostDto> createHost(@RequestBody HostCreateDto hostCreateDto) {
        return this.hostService.create(hostCreateDto)
                .map(host -> ResponseEntity.ok(this.hostService.toDto(host)))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<HostDto> updateHost(@PathVariable Long id,
                                              @RequestBody HostCreateDto hostCreateDto) {
        return this.hostService.update(id, hostCreateDto)
                .map(host -> ResponseEntity.ok(this.hostService.toDto(host)))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHost(@PathVariable Long id) {
        this.hostService.deleteById(id);
        if(this.hostService.findById(id).isEmpty()) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}