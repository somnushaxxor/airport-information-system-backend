package ru.nsu.fit.kolesnik.airportinformationsystem.pilotmedicalexamination;

import java.util.List;

public interface PilotMedicalExaminationService {

    List<PilotMedicalExamination> getAllPilotsMedicalExaminationsBy();

    PilotMedicalExamination getPilotMedicalExaminationById(Long id);

    void createPilotMedicalExamination(PilotMedicalExaminationCreationRequest creationRequest);

    void updatePilotMedicalExamination(PilotMedicalExaminationUpdateRequest updateRequest);

    void deletePilotMedicalExaminationById(Long id);

}
