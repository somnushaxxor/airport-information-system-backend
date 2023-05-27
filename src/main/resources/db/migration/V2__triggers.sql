CREATE FUNCTION check_is_joined_before_born()
    RETURNS TRIGGER AS
$$
BEGIN
    IF NEW.joined_at < NEW.date_of_birth
    THEN
        RAISE EXCEPTION 'Employee must be born before joining the airport';
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER verify_joined_date
    BEFORE INSERT OR UPDATE
    ON employees
    FOR EACH ROW
EXECUTE FUNCTION check_is_joined_before_born();

CREATE FUNCTION check_is_department_unique()
    RETURNS TRIGGER AS
$$
BEGIN
    IF EXISTS (SELECT 1 FROM departments WHERE departments.name = NEW.name)
    THEN
        RAISE EXCEPTION 'Department name must be unique';
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER verify_department_name
    BEFORE INSERT OR UPDATE
    ON departments
    FOR EACH ROW
EXECUTE FUNCTION check_is_department_unique();

CREATE FUNCTION check_is_chief_from_same_department()
    RETURNS TRIGGER AS
$$
BEGIN
    IF NOT NEW.department_id = (SELECT department_id
                                FROM employees
                                WHERE id = NEW.chief_id)
    THEN
        RAISE EXCEPTION 'Employee must belong to the given department';
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER verify_department_chief
    BEFORE INSERT OR UPDATE
    ON departments_chiefs
    FOR EACH ROW
EXECUTE FUNCTION check_is_chief_from_same_department();

CREATE FUNCTION check_is_brigade_unique()
    RETURNS TRIGGER AS
$$
BEGIN
    IF EXISTS (SELECT 1 FROM brigades WHERE brigades.name = NEW.name)
    THEN
        RAISE EXCEPTION 'Brigade name must be unique';
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER verify_brigade_name
    BEFORE INSERT OR UPDATE
    ON brigades
    FOR EACH ROW
EXECUTE FUNCTION check_is_brigade_unique();

CREATE FUNCTION check_is_brigade_has_airplanes_assigned()
    RETURNS TRIGGER AS
$$
BEGIN
    IF EXISTS (SELECT 1
               from airplanes
                        inner join brigades b on OLD.id = airplanes.pilots_brigade_id)
    THEN
        RAISE EXCEPTION 'There should be no airplanes serviced by the brigade to delete it';
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER verify_brigade_has_no_airplanes_assigned
    BEFORE DELETE
    ON brigades
    FOR EACH ROW
EXECUTE FUNCTION check_is_brigade_has_airplanes_assigned();

CREATE FUNCTION check_is_exists_by_done_at()
    RETURNS TRIGGER AS
$$
BEGIN
    IF EXISTS (SELECT 1
               from airplane_maintenance_operations
               WHERE airplane_maintenance_operations.done_at = NEW.done_at)
    THEN
        RAISE EXCEPTION 'Airplane maintenance operation for this date is already registered';
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER verify_examination_by_done_at_date
    BEFORE INSERT OR UPDATE
    ON airplane_maintenance_operations
    FOR EACH ROW
EXECUTE FUNCTION check_is_exists_by_done_at();
