create schema if not exists dental;

CREATE TABLE IF NOT EXISTS dental.patient (
    ID SERIAL PRIMARY KEY,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    date_of_birth DATE,
    phone_number TEXT
);

CREATE TABLE IF NOT EXISTS dental.speciality (
    ID SERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS dental.reason (
    ID SERIAL PRIMARY KEY,
    title varchar(255)
);

CREATE TABLE IF NOT EXISTS dental.doctor (
    ID SERIAL PRIMARY KEY,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    speciality_id INT,
    FOREIGN KEY (speciality_id) REFERENCES speciality (ID)
);

CREATE TABLE IF NOT EXISTS dental.doctor_appointment (
    ID SERIAL PRIMARY KEY,
    id_patient INT,
    id_doctor INT,
    date_of_appointment DATE,
    time_of_visit DATE,
    reason_id INT,
    FOREIGN KEY (reason_id) REFERENCES  reason (ID) ON DELETE CASCADE,
    FOREIGN KEY (id_patient) REFERENCES patient (ID) ON DELETE CASCADE,
    FOREIGN KEY (id_doctor) REFERENCES doctor (ID) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS dental.history_of_treatment (
    ID SERIAL PRIMARY KEY,
    appointment_id INT,
    description varchar(255),
    price DECIMAL(10, 2),
    discount DECIMAL(5, 2),
    date_of_treatment DATE,
    FOREIGN KEY (appointment_id) REFERENCES doctor_appointment(ID) ON DELETE CASCADE
);