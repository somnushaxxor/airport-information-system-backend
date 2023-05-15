insert into genders(name)
values ('MALE'),
       ('FEMALE');

insert into specializations(name)
values ('PILOT'),
       ('TECHNICIAN'),
       ('SERVICE');

insert into departments(name, chief_id)
values ('First Department', 1),
       ('Second Department', 3),
       ('Third Department', 4);

insert into brigades(department_id, name, specialization_id)
values (1, 'PILOTS-1', 1),
       (1, 'TECH-1', 2),
       (2, 'PILOTS-2', 1),
       (3, 'TECH-3', 2),
       (3, 'SERVICE-3', 3);

insert into employees(first_name, last_name, gender_id, date_of_birth, joined_at, number_of_children,
                      salary, specialization_id, department_id, brigade_id)
values ('Robin', 'Karp', 1, '2002-09-27', '2020-09-01', 0, 100000, 1, 1, 1),
       ('Bob', 'Martins', 1, '2001-10-25', '2020-09-02', 1, 120000, 2, 1, 2),
       ('Vans', 'Johnson', 1, '2000-11-07', '2020-09-03', 4, 120000, 1, 2, 3),
       ('Martha', 'Wicker', 2, '2001-04-24', '2020-09-01', 0, 120000, 2, 3, 4),
       ('Lora', 'Stone', 2, '2002-09-20', '2020-09-05', 2, 80000, 3, 3, 5),
       ('Alice', 'Krage', 2, '1999-10-10', '2020-09-05', 2, 90000, 3, 3, 5);

alter table departments
    add foreign key (chief_id) references employees;

insert into countries(name)
values ('Russia'),
       ('Germany'),
       ('UK');

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