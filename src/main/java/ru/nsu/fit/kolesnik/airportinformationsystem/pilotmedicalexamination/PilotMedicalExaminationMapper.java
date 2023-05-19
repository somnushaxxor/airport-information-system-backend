package ru.nsu.fit.kolesnik.airportinformationsystem.pilotmedicalexamination;

public final class PilotMedicalExaminationMapper {

    private PilotMedicalExaminationMapper() {
    }

    public static PilotMedicalExaminationPreviewDto toPreviewDto(PilotMedicalExamination pilotMedicalExamination) {
        return new PilotMedicalExaminationPreviewDto(pilotMedicalExamination.getId(),
                pilotMedicalExamination.getPilot().getFirstName(), pilotMedicalExamination.getPilot().getLastName(),
                pilotMedicalExamination.getDate());
    }

    public static PilotMedicalExaminationDto toDto(PilotMedicalExamination pilotMedicalExamination) {
        return new PilotMedicalExaminationDto(pilotMedicalExamination.getPilot().getId(),
                pilotMedicalExamination.getDate());
    }

}
