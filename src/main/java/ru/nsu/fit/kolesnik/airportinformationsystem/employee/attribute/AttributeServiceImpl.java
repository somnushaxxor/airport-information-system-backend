package ru.nsu.fit.kolesnik.airportinformationsystem.employee.attribute;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import ru.nsu.fit.kolesnik.airportinformationsystem.NotFoundException;
import ru.nsu.fit.kolesnik.airportinformationsystem.employee.attribute.value.AttributeValueRepository;
import ru.nsu.fit.kolesnik.airportinformationsystem.specialization.Specialization;
import ru.nsu.fit.kolesnik.airportinformationsystem.specialization.SpecializationService;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AttributeServiceImpl implements AttributeService {

    private final AttributeRepository attributeRepository;
    private final SpecializationService specializationService;
    private final AttributeValueRepository attributeValueRepository;

    @Override
    public List<Attribute> getAllAttributes() {
        return attributeRepository.findAll();
    }

    @Override
    public Attribute getAttributeById(Long id) {
        return attributeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Attribute not found: " + id));
    }

    @Override
    public void createAttribute(AttributeCreationRequest creationRequest) {
        Specialization specialization = specializationService.getSpecializationById(creationRequest.specializationId());
        if (attributeRepository.existsByNameAndSpecialization(creationRequest.name(), specialization)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Attribute name must be unique for given specialization");
        }
        Attribute attribute = new Attribute();
        attribute.setName(creationRequest.name());
        attribute.setSpecialization(specialization);
        attributeRepository.save(attribute);
    }

    @Override
    public void updateAttribute(AttributeUpdateRequest updateRequest) {
        Attribute attribute = getAttributeById(updateRequest.id());
        Specialization specialization = specializationService.getSpecializationById(updateRequest.specializationId());
        if (!attribute.getSpecialization().equals(specialization)
                && isAttributeAlreadyInUse(attribute)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Unable to change the attribute's specialization while someone has some value of this attribute");
        }
        if (attributeRepository.existsByNameAndSpecialization(updateRequest.name(), specialization)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Attribute name must be unique for given specialization");
        }
        attribute.setName(updateRequest.name());
        attribute.setSpecialization(specialization);
        attributeRepository.save(attribute);
    }

    private boolean isAttributeAlreadyInUse(Attribute attribute) {
        return attributeValueRepository.existsByAttribute(attribute);
    }

    @Override
    @Transactional
    public void deleteAttributeById(Long id) {
        Attribute attribute = getAttributeById(id);
        attributeValueRepository.deleteAllByAttribute(attribute);
        attributeRepository.delete(attribute);
    }

}
