package ru.nsu.fit.kolesnik.airportinformationsystem.brigade;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.nsu.fit.kolesnik.airportinformationsystem.department.Department;
import ru.nsu.fit.kolesnik.airportinformationsystem.specialization.Specialization;

import java.util.List;

@Repository
public interface BrigadeRepository extends JpaRepository<Brigade, Long> {

    @Query("""
            select b
            from Brigade b
            where (:department is null or b.department = :department)
            and (:specialization is null or b.specialization = :specialization)
            """)
    List<Brigade> findAllByDepartmentAndSpecializationIgnoringNull(
            @Param("department") Department department,
            @Param("specialization") Specialization specialization
    );

}
