package ru.nsu.fit.kolesnik.airportinformationsystem.ticket;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketService ticketService;

    @GetMapping
    public List<TicketPreviewDto> getAllTicketsBy(
            @RequestParam(name = "flightId", required = false) Long flightId,
            @RequestParam(name = "genderId", required = false) Long genderId,
            @Min(0) @RequestParam(name = "ageInYears", required = false) Integer ageInYears
    ) {
        return ticketService.getAllTicketsBy(flightId, genderId, ageInYears).stream()
                .map(TicketMapper::toPreviewDto).toList();
    }

    @GetMapping("/{id}")
    public TicketDto getTicketById(@PathVariable Long id) {
        return TicketMapper.toDto(ticketService.getTicketById(id));
    }

    @PostMapping
    public void createTicket(@Valid @RequestBody TicketCreationRequest creationRequest) {
        ticketService.createTicket(creationRequest);
    }

    @PutMapping
    public void updateTicket(@Valid @RequestBody TicketUpdateRequest updateRequest) {
        ticketService.updateTicket(updateRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteTicketById(@PathVariable Long id) {
        ticketService.deleteTicketById(id);
    }

}
