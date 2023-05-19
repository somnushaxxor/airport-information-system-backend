package ru.nsu.fit.kolesnik.airportinformationsystem.employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.nsu.fit.kolesnik.airportinformationsystem.brigade.Brigade;
import ru.nsu.fit.kolesnik.airportinformationsystem.department.Department;
import ru.nsu.fit.kolesnik.airportinformationsystem.specialization.Specialization;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query(value = """
            select *
            from employees
            where (:genderId is null or gender_id = :genderId)
            and (:departmentId is null or department_id = :departmentId)
            and (:brigadeId is null or brigade_id = :brigadeId)
            and (:workExperienceInYears is null or date_part('year', age(current_date, joined_at)) = :workExperienceInYears)
            and (:ageInYears is null or date_part('year', age(current_date, date_of_birth)) = :ageInYears)
            and (:numberOfChildren is null or number_of_children = :numberOfChildren)
            and (:salary is null or salary = :salary)
            """, nativeQuery = true)
    List<Employee> findAllIgnoringNullBy(
            @Param("genderId") Long genderId,
            @Param("departmentId") Long departmentId,
            @Param("brigadeId") Long brigadeId,
            @Param("workExperienceInYears") Integer workExperienceInYears,
            @Param("ageInYears") Integer ageInYears,
            @Param("numberOfChildren") Integer numberOfChildren,
            @Param("salary") Integer salary
    );

    void deleteAllByDepartment(Department department);

    void deleteAllBySpecialization(Specialization specialization);

    void deleteAllByBrigade(Brigade brigade);

    List<Employee> findAllBySpecialization(Specialization specialization);

    @Query(value = """
            select distinct e.*
            from pilots_medical_examinations
            join employees e on e.id = pilots_medical_examinations.pilot_id
            and (:medicalExaminationYear is null or date_part('year', date) = :medicalExaminationYear)
            and (:genderId is null or gender_id = :genderId)
            and (:ageInYears is null or date_part('year', age(current_date, date_of_birth)) = :ageInYears)
            and (:salary is null or salary = :salary)
            """, nativeQuery = true)
    List<Employee> findAllPilots(
            @Param("medicalExaminationYear") Integer medicalExaminationYear,
            @Param("genderId") Long genderId,
            @Param("ageInYears") Integer ageInYears,
            @Param("salary") Integer salary
    );

}
