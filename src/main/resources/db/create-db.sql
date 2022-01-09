create table regions (
            id SERIAL,
            region_name varchar(255),
            region_number int8,
            primary key (id)
                     );

create table sensors (
                         id SERIAL,
                         latitude float8,
                         longitude float8,
                         name varchar(255),
                         sensor_number int8,
                         region_id int8,
                         primary key (id),
                         foreign key (region_id) references regions
);

create table registrations (
            id SERIAL,
            registration_date timestamp,
            temperature float8,
            sensor_id int8,
            primary key (id),
            foreign key (sensor_id) references sensors
                           );

