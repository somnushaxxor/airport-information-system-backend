insert into genders(name)
values ('MALE'),
       ('FEMALE');

insert into specializations(name)
values ('PILOT'),
       ('TECHNICIAN'),
       ('SERVICE');

insert into departments(name)
values ('First Department'),
       ('Second Department'),
       ('Third Department'),
       ('Fourth Department');

insert into brigades(department_id, name, specialization_id)
values (1, 'PILOTS-1', 1),
       (1, 'TECH-1', 2),
       (2, 'PILOTS-2', 1),
       (3, 'TECH-3', 2),
       (3, 'SERVICE-3', 3),
       (3, 'PILOTS-3', 1);

insert into employees(first_name, last_name, gender_id, date_of_birth, joined_at, number_of_children,
                      salary, specialization_id, department_id, brigade_id)
values ('Robin', 'Karp', 1, '2002-09-27', '2020-09-01', 0, 100000, 1, 1, 1),
       ('Bob', 'Martins', 1, '2001-10-25', '2020-09-02', 1, 120000, 2, 1, 2),
       ('Vans', 'Johnson', 1, '2000-11-07', '2020-09-03', 4, 120000, 1, 2, 3),
       ('Martha', 'Wicker', 2, '2001-04-24', '2020-09-01', 0, 120000, 2, 3, 4),
       ('Lora', 'Stone', 2, '2002-09-20', '2020-09-05', 2, 80000, 3, 3, 5),
       ('Alice', 'Krage', 2, '1999-10-10', '2020-09-05', 2, 90000, 3, 3, 5);

insert into attributes(name, specialization_id)
values ('Flight school GPA', 1),
       ('Passed leadership courses', 2);

insert into attribute_values(attribute_id, employee_id, value)
values (1, 1, '4.5'),
       (2, 2, 'True');

insert into departments_chiefs(department_id, chief_id)
values (1, 1),
       (2, 3),
       (3, 4);

insert into pilots_medical_examinations(pilot_id, date)
values (1, '2022-05-05'),
       (1, '2023-03-04'),
       (3, '2023-04-07');

insert into countries(name)
values ('Russia'),
       ('Germany'),
       ('UK'),
       ('USA');

insert into cities(name, country_id)
values ('Omsk', 1),
       ('Moscow', 1),
       ('Berlin', 2),
       ('London', 3);

insert into airports(name, city_id)
values ('OMSK-1', 1),
       ('MOSCOW-1', 2),
       ('BERLIN-1', 3),
       ('LONDON-1', 4);

insert into routes(departure_airport_id, transfer_airport_id, arrival_airport_id)
values (1, null, 2),
       (2, null, 1),
       (2, null, 3),
       (3, null, 4),
       (1, 2, 3);

insert into airplane_models(name, passengers_capacity)
values ('Kukuruznik', 10),
       ('Airbus', 100),
       ('Boeing', 200);

insert into airplanes(model_id, created_at, joined_at, pilots_brigade_id, technicians_brigade_id, service_brigade_id,
                      home_airport_id)
values (1, '2019-05-10', '2020-08-01', 1, 2, 5, 1),
       (2, '2015-05-10', '2019-08-03', 3, 4, 5, 2),
       (3, '2016-09-27', '2018-05-05', 6, 4, 5, 2);

insert into airplane_maintenance_operations(done_at, repair_required, airplane_id)
values ('2019-02-24', false, 1),
       ('2019-03-08', true, 1),
       ('2019-05-10', true, 1),
       ('2019-06-01', false, 1);

insert into flight_categories(name)
values ('DOMESTIC'),
       ('INTERNATIONAL'),
       ('SPECIAL');

insert into flights(airplane_id, route_id, category_id, scheduled_departure_at, scheduled_arrival_at, ticket_price,
                    min_tickets_number)
values (1, 1, 1, '2023-01-01 9:25:00', '2023-01-01 12:34:00', 200, 50),
       (1, 1, 1, '2023-01-02 9:25:00', '2023-01-02 12:34:00', 200, 50);

insert into tickets(flight_id, first_name, last_name, gender_id, date_of_birth, local_passport_number,
                    international_passport_number, seat, baggage)
values (1, 'Artem', 'Kolesnik', 1, '2002-09-27', '5216585573', 'A1B2C3D4', 4, true),
       (1, 'Artem', 'Kolesnik', 1, '2002-09-27', '5216585573', 'A1B2C3D4', 8, true),
       (2, 'Artem', 'Kolesnik', 1, '2002-09-27', '5216585573', 'A1B2C3D4', 4, true),
       (2, 'Artem', 'Kolesnik', 1, '2002-09-27', '5216585573', 'A1B2C3D4', 5, true),
       (2, 'Artem', 'Kolesnik', 1, '2002-09-27', '5216585573', 'A1B2C3D4', 6, true);