package ru.nsu.fit.kolesnik.airportinformationsystem.gender;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/genders")
public class GenderController {

    private final GenderService genderService;

    @GetMapping
    public List<GenderDto> getAllGenders() {
        return genderService.getAllGenders().stream().map(GenderMapper::toDto).toList();
    }

}
