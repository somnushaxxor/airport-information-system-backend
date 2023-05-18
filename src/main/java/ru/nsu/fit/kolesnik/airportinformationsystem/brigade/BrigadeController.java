package ru.nsu.fit.kolesnik.airportinformationsystem.brigade;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/brigades")
public class BrigadeController {

    private final BrigadeService brigadeService;

    @GetMapping
    public List<BrigadeDto> getAllBrigadesBy(
            @RequestParam(value = "specializationId", required = false) Long specializationId,
            @RequestParam(value = "departmentId", required = false) Long departmentId
    ) {
        return brigadeService.getAllBrigadesBy(specializationId, departmentId).stream()
                .map(BrigadeMapper::toDto).toList();
    }

}
