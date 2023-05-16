package ru.nsu.fit.kolesnik.airportinformationsystem.gender;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nsu.fit.kolesnik.airportinformationsystem.NotFoundException;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GenderServiceImpl implements GenderService {

    private final GenderRepository genderRepository;

    @Override
    public List<Gender> getAllGenders() {
        return genderRepository.findAll();
    }

    @Override
    public Gender getGenderById(Long id) {
        return genderRepository.findById(id).orElseThrow(() -> new NotFoundException("Gender not found: " + id));
    }

}
