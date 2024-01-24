package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import com.example.demo.entities.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@TestInstance(Lifecycle.PER_CLASS)
class RoomUnitTest {
    @Test
    void shouldCreateRoomEmptyConstructor() {
        Room room1 = new Room();
        assertThat(room1).isNotNull();
        assertThat(room1.getRoomName()).isNull();
    }

    @Test
    void shouldCreateRoomFullConstructor() {
        Room room1 = new Room("dermatology");
        assertThat(room1).isNotNull();
        assertThat(room1.getRoomName()).isEqualTo("dermatology");
    }
}

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@TestInstance(Lifecycle.PER_CLASS)
class DoctorUnitTest {
    @Test
    void shouldCreateDoctorEmptyConstructor() {
        Doctor doctor1 = new Doctor();
        assertThat(doctor1).isNotNull();
    }

    @Test
    void shouldCreateDoctorFullConstructor() {
        Doctor doctor1 = new Doctor("Jessica", "Sinatra", 34, "sinatraj89@mail.com");

        assertThat(doctor1).isNotNull();
        assertThat(doctor1.getId()).isEqualTo(0);
        assertThat(doctor1.getFirstName()).isEqualTo("Jessica");
        assertThat(doctor1.getLastName()).isEqualTo("Sinatra");
        assertThat(doctor1.getAge()).isEqualTo(34);
        assertThat(doctor1.getEmail()).isEqualTo("sinatraj89@mail.com");
    }

    @Test
    void shouldSetDoctorValues() {
        Doctor doctor1 = new Doctor();
        doctor1.setId(1);
        doctor1.setFirstName("Gloria");
        doctor1.setLastName("Trevi");
        doctor1.setAge(42);
        doctor1.setEmail("gloriatrevi@mail.com");

        assertThat(doctor1).isNotNull();
        assertThat(doctor1.getId()).isEqualTo(1);
        assertThat(doctor1.getFirstName()).isEqualTo("Gloria");
        assertThat(doctor1.getLastName()).isEqualTo("Trevi");
        assertThat(doctor1.getAge()).isEqualTo(42);
        assertThat(doctor1.getEmail()).isEqualTo("gloriatrevi@mail.com");
    }

}

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@TestInstance(Lifecycle.PER_CLASS)
class PatientUnitTest {

    @Test
    void shouldCreatePatientEmptyConstructor() {
        Patient patient1 = new Patient();
        assertThat(patient1).isNotNull();
    }

    @Test
    void shouldCreatePatientFullConstructor() {
        Patient patient1 = new Patient("Jessica", "Sinatra", 34, "sinatraj89@mail.com");

        assertThat(patient1).isNotNull();
        assertThat(patient1.getId()).isEqualTo(0);
        assertThat(patient1.getFirstName()).isEqualTo("Jessica");
        assertThat(patient1.getLastName()).isEqualTo("Sinatra");
        assertThat(patient1.getAge()).isEqualTo(34);
        assertThat(patient1.getEmail()).isEqualTo("sinatraj89@mail.com");
    }

    @Test
    void shouldSetPatientValues() {
        Patient patient1 = new Patient();
        patient1.setId(1);
        patient1.setFirstName("Gloria");
        patient1.setLastName("Trevi");
        patient1.setAge(42);
        patient1.setEmail("gloriatrevi@mail.com");

        assertThat(patient1).isNotNull();
        assertThat(patient1.getId()).isEqualTo(1);
        assertThat(patient1.getFirstName()).isEqualTo("Gloria");
        assertThat(patient1.getLastName()).isEqualTo("Trevi");
        assertThat(patient1.getAge()).isEqualTo(42);
        assertThat(patient1.getEmail()).isEqualTo("gloriatrevi@mail.com");
    }
}

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@TestInstance(Lifecycle.PER_CLASS)
class AppointmentUnitTest {
    Patient patient;
    Patient patient2;
    Doctor doctor;
    Doctor doctor2;
    Room room;

    @BeforeEach
    void setUp() {
        patient = new Patient("Jose Luis", "Olaya", 37, "j.olaya@email.com");
        patient2 = new Patient("Paulino", "Antunez", 37, "p.antunez@email.com");
        doctor = new Doctor("Perla", "Amalia", 24, "p.amalia@hospital.accwe");
        doctor2 = new Doctor("Miren", "Iniesta", 24, "m.iniesta@hospital.accwe");
        room = new Room("Dermatology");
    }

    @Test
    void shouldCreateAppointmentEmptyConstructor() {
        Appointment appointment1 = new Appointment();
        assertThat(appointment1).isNotNull();
    }

    @Test
    void shouldCreateAppointmentFullConstructor() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy");
        LocalDateTime startsAt = LocalDateTime.parse("19:30 24/04/2023", formatter);
        LocalDateTime finishesAt = LocalDateTime.parse("20:30 24/04/2023", formatter);
        Appointment appointment1 = new Appointment(patient, doctor, room, startsAt, finishesAt);

        assertThat(appointment1).isNotNull();
        assertThat(appointment1.getId()).isEqualTo(0);
        assertThat(appointment1.getPatient()).isEqualTo(patient);
        assertThat(appointment1.getDoctor()).isEqualTo(doctor);
        assertThat(appointment1.getRoom()).isEqualTo(room);
        assertThat(appointment1.getStartsAt()).isEqualTo(startsAt);
        assertThat(appointment1.getFinishesAt()).isEqualTo(finishesAt);
    }

    @Test
    void shouldSetAppointmentValues() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy");
        LocalDateTime startsAt = LocalDateTime.parse("19:30 24/04/2023", formatter);
        LocalDateTime finishesAt = LocalDateTime.parse("20:30 24/04/2023", formatter);
        Appointment appointment1 = new Appointment();

        appointment1.setId(1);
        appointment1.setPatient(patient);
        appointment1.setDoctor(doctor);
        appointment1.setRoom(room);
        appointment1.setStartsAt(startsAt);
        appointment1.setFinishesAt(finishesAt);

        assertThat(appointment1).isNotNull();
        assertThat(appointment1.getId()).isEqualTo(1);
        assertThat(appointment1.getPatient()).isEqualTo(patient);
        assertThat(appointment1.getDoctor()).isEqualTo(doctor);
        assertThat(appointment1.getRoom()).isEqualTo(room);
        assertThat(appointment1.getStartsAt()).isEqualTo(startsAt);
        assertThat(appointment1.getFinishesAt()).isEqualTo(finishesAt);
    }

    @Test
    void shouldOverlapAppointmentDueSameStartHour() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy");
        LocalDateTime startsAt = LocalDateTime.parse("19:30 24/04/2023", formatter);
        LocalDateTime finishesAt = LocalDateTime.parse("20:30 24/04/2023", formatter);
        LocalDateTime finishesAt2 = LocalDateTime.parse("20:15 24/04/2023", formatter);

        Appointment appointment1 = new Appointment(patient, doctor, room, startsAt, finishesAt);
        Appointment appointment2 = new Appointment(patient2, doctor2, room, startsAt, finishesAt2);
        boolean overlap = appointment1.overlaps(appointment2);

        assertThat(overlap).isTrue();
    }

    @Test
    void shouldOverlapAppointmentDueSameFinishHour() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy");
        LocalDateTime startsAt = LocalDateTime.parse("19:30 24/04/2023", formatter);
        LocalDateTime startsAt2 = LocalDateTime.parse("19:45 24/04/2023", formatter);
        LocalDateTime finishesAt = LocalDateTime.parse("20:30 24/04/2023", formatter);

        Appointment appointment1 = new Appointment(patient, doctor, room, startsAt, finishesAt);
        Appointment appointment2 = new Appointment(patient2, doctor2, room, startsAt2, finishesAt);
        boolean overlap = appointment1.overlaps(appointment2);

        assertThat(overlap).isTrue();
    }

    @Test
    void shouldOverlapBEndsBeforeAEnds() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy");
        LocalDateTime startsAt = LocalDateTime.parse("19:30 24/04/2023", formatter);
        LocalDateTime startsAt2 = LocalDateTime.parse("19:15 24/04/2023", formatter);
        LocalDateTime finishesAt = LocalDateTime.parse("20:30 24/04/2023", formatter);
        LocalDateTime finishesAt2 = LocalDateTime.parse("20:15 24/04/2023", formatter);

        Appointment appointment1 = new Appointment(patient, doctor, room, startsAt, finishesAt);
        Appointment appointment2 = new Appointment(patient2, doctor2, room, startsAt2, finishesAt2);
        boolean overlap = appointment1.overlaps(appointment2);

        assertThat(overlap).isTrue();
    }

    @Test
    void shouldOverlapBStartsBeforeAEnds() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy");
        LocalDateTime startsAt = LocalDateTime.parse("19:15 24/04/2023", formatter);
        LocalDateTime finishesAt = LocalDateTime.parse("20:15 24/04/2023", formatter);
        LocalDateTime startsAt2 = LocalDateTime.parse("20:00 24/04/2023", formatter);
        LocalDateTime finishesAt2 = LocalDateTime.parse("21:00 24/04/2023", formatter);

        Appointment appointment1 = new Appointment(patient, doctor, room, startsAt, finishesAt);
        Appointment appointment2 = new Appointment(patient2, doctor2, room, startsAt2, finishesAt2);
        boolean overlap = appointment1.overlaps(appointment2);

        assertThat(overlap).isTrue();
    }

    @Test
    void shouldNotOverlapAppointments() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy");
        LocalDateTime startsAt = LocalDateTime.parse("19:15 24/04/2023", formatter);
        LocalDateTime finishesAt = LocalDateTime.parse("20:15 24/04/2023", formatter);
        LocalDateTime startsAt2 = LocalDateTime.parse("20:15 24/04/2023", formatter);
        LocalDateTime finishesAt2 = LocalDateTime.parse("21:15 24/04/2023", formatter);

        Appointment appointment1 = new Appointment(patient, doctor, room, startsAt, finishesAt);
        Appointment appointment2 = new Appointment(patient2, doctor2, room, startsAt2, finishesAt2);
        boolean overlap = appointment1.overlaps(appointment2);

        assertThat(overlap).isFalse();
    }
}
