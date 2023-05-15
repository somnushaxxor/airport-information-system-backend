package ru.nsu.fit.kolesnik.airportinformationsystem.brigade;

import java.util.List;

public interface BrigadeService {

    List<Brigade> getBrigadesBy(Long specialisationId, Long departmentId);

}
