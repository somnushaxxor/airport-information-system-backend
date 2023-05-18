create table genders
(
    id   bigint primary key generated always as identity,
    name varchar(255) unique not null check ( name <> '' )
);

create table specializations
(
    id   bigint primary key generated always as identity,
    name varchar(255) unique not null check ( name <> '' )
);

create table departments
(
    id   bigint primary key generated always as identity,
    name varchar(255) unique not null check ( name <> '' )
);

create table brigades
(
    id                bigint primary key generated always as identity,
    name              varchar(255) unique not null check ( name <> '' ),
    department_id     bigint              not null references departments on delete cascade,
    specialization_id bigint              not null references specializations on delete cascade
);

create table employees
(
    id                 bigint primary key generated always as identity,
    first_name         varchar(255) not null check ( first_name <> '' ),
    last_name          varchar(255) not null check ( last_name <> '' ),
    gender_id          bigint       not null references genders on delete cascade,
    date_of_birth      date         not null,
    joined_at          date         not null check ( date_of_birth <= joined_at ),
    number_of_children int          not null check ( number_of_children >= 0 ),
    salary             int          not null check ( salary >= 0 ),
    specialization_id  bigint       not null references specializations on delete cascade,
    department_id      bigint       not null references departments on delete cascade,
    brigade_id         bigint references brigades on delete cascade
);

create table departments_chiefs
(
    department_id bigint primary key references departments on delete cascade,
    chief_id      bigint not null unique references employees on delete cascade
);

create table countries
(
    id   bigint primary key generated always as identity,
    name varchar(255) unique not null check ( name <> '' )
);

create table cities
(
    id         bigint primary key generated always as identity,
    name       varchar(255) unique not null check ( name <> '' ),
    country_id bigint              not null references countries on delete cascade
);

create table airports
(
    id      bigint primary key generated always as identity,
    name    varchar(255) unique not null check ( name <> '' ),
    city_id bigint              not null references cities on delete cascade
);

create table airplane_models
(
    id                  bigint primary key generated always as identity,
    name                varchar(255) unique not null check ( name <> '' ),
    passengers_capacity int                 not null check ( passengers_capacity >= 0 )
);

create table airplanes
(
    id                 bigint primary key generated always as identity,
    model_id           bigint not null references airplane_models on delete cascade,
    created_at         date   not null,
    joined_at          date   not null check ( created_at <= joined_at ),
    pilots_brigade_id  bigint references brigades on delete set null,
    tech_brigade_id    bigint references brigades on delete set null,
    service_brigade_id bigint references brigades on delete set null,
    home_airport_id    bigint not null references airports on delete cascade
);

create table routes
(
    id                   bigint primary key generated always as identity,
    departure_airport_id bigint not null references airports on delete cascade,
    transfer_airport_id  bigint references airports on delete cascade,
    arrival_airport_id   bigint not null references airports on delete cascade,
    unique (departure_airport_id, transfer_airport_id, arrival_airport_id)
);

create table flight_categories
(
    id   bigint primary key generated always as identity,
    name varchar(255) unique not null check ( name <> '' )
);

create table flights
(
    id                     bigint primary key generated always as identity,
    airplane_id            bigint    not null references airplanes on delete cascade,
    route_id               bigint    not null references routes on delete cascade,
    category_id            bigint    not null references flight_categories on delete cascade,
    scheduled_departure_at timestamp not null,
    scheduled_arrival_at   timestamp not null,
    actual_departure_at    timestamp,
    actual_arrival_at      timestamp,
    ticket_price           integer   not null check ( ticket_price >= 0 ),
    min_tickets_number     integer   not null check ( min_tickets_number > 0)
);

create table tickets
(
    id                            bigint primary key generated always as identity,
    flight_id                     bigint       not null references flights on delete cascade,
    first_name                    varchar(255) not null check ( first_name <> '' ),
    last_name                     varchar(255) not null check ( last_name <> '' ),
    gender_id                     bigint       not null references genders on delete cascade,
    date_of_birth                 date         not null,
    local_passport_number         varchar(255) not null check ( local_passport_number <> '' ),
    international_passport_number varchar(255) check (international_passport_number <> ''),
    seat                          int          not null,
    baggage                       boolean      not null
);
