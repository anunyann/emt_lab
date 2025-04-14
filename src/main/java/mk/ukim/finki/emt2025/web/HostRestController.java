package mk.ukim.finki.emt2025.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.ukim.finki.emt2025.model.dto.HostCreateDto;
import mk.ukim.finki.emt2025.model.dto.HostDto;
import mk.ukim.finki.emt2025.service.application.HostApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hosts")
@Tag(name = "Host API", description = "Endpoints for managing hosts")
public class HostRestController {

    private final HostApplicationService hostService;

    public HostRestController(HostApplicationService hostService) {
        this.hostService = hostService;
    }

    @Operation(summary = "Get all hosts", description = "Retrieves a list of all hosts")
    @ApiResponse(responseCode = "200", description = "List of hosts retrieved successfully")
    @GetMapping
    public List<HostDto> getAllHosts() {
        return this.hostService.findAll();
    }

    @Operation(summary = "Get host by ID", description = "Retrieves a host by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Host found"),
            @ApiResponse(responseCode = "404", description = "Host not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<HostDto> getHost(@PathVariable Long id) {
        return this.hostService.findById(id)
                .map(host -> ResponseEntity.ok(host))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create a new host", description = "Creates a new host with the provided details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Host created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid host data")
    })
    @PostMapping
    @PreAuthorize("hasRole('HOST')")
    public ResponseEntity<HostDto> createHost(@RequestBody HostCreateDto hostCreateDto) {
        return this.hostService.create(hostCreateDto)
                .map(host -> ResponseEntity.ok(host))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @Operation(summary = "Update a host", description = "Updates an existing host with new details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Host updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid host data"),
            @ApiResponse(responseCode = "404", description = "Host not found")
    })
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('HOST')")
    public ResponseEntity<HostDto> updateHost(@PathVariable Long id,
                                              @RequestBody HostCreateDto hostCreateDto) {
        return this.hostService.update(id, hostCreateDto)
                .map(host -> ResponseEntity.ok(host))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @Operation(summary = "Delete a host", description = "Deletes a host by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Host deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Host not found")
    })
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('HOST')")
    public ResponseEntity<Void> deleteHost(@PathVariable Long id) {
        this.hostService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}