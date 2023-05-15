package ru.nsu.fit.kolesnik.airportinformationsystem.gender;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GenderServiceImpl implements GenderService {

    private final GenderRepository genderRepository;

    @Override
    public List<Gender> getAllGenders() {
        return genderRepository.findAll();
    }

}
