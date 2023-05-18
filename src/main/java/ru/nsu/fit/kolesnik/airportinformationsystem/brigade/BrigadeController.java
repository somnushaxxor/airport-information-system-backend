package ru.nsu.fit.kolesnik.airportinformationsystem.brigade;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/brigades")
public class BrigadeController {

    private final BrigadeService brigadeService;

    @GetMapping
    public List<BrigadePreviewDto> getAllBrigadesBy(
            @RequestParam(value = "specializationId", required = false) Long specializationId,
            @RequestParam(value = "departmentId", required = false) Long departmentId
    ) {
        return brigadeService.getAllBrigadesBy(specializationId, departmentId).stream()
                .map(BrigadeMapper::toPreviewDto).toList();
    }

    @GetMapping("/{id}")
    public BrigadeDto getBrigadeById(@PathVariable Long id) {
        return BrigadeMapper.toDto(brigadeService.getBrigadeById(id));
    }

    @PostMapping
    public void createBrigade(@Valid @RequestBody BrigadeCreationRequest creationRequest) {
        brigadeService.createBrigade(creationRequest);
    }

    @PutMapping
    public void updateBrigade(@Valid @RequestBody BrigadeUpdateRequest updateRequest) {
        brigadeService.updateBrigade(updateRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteBrigadeById(@PathVariable Long id) {
        brigadeService.deleteBrigadeById(id);
    }

}
