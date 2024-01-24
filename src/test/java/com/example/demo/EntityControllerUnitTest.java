package com.example.demo;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.example.demo.controllers.*;
import com.example.demo.repositories.*;
import com.example.demo.entities.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.hamcrest.Matchers.*;


@WebMvcTest(DoctorController.class)
class DoctorControllerUnitTest {

    @MockBean
    private DoctorRepository doctorRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    Doctor doctor;
    Doctor doctor2;

    @BeforeEach
    void setUp() {
        doctor = new Doctor("Perla", "Amalia", 24, "p.amalia@hospital.accwe");
        doctor2 = new Doctor("Miren", "Iniesta", 24, "m.iniesta@hospital.accwe");
        doctor.setId(1);
    }

    @Test
    void shouldGetTwoDoctors() throws Exception {
        List<Doctor> doctorList = new ArrayList<>();
        doctorList.add(doctor);
        doctorList.add(doctor2);

        when(doctorRepository.findAll()).thenReturn(doctorList);
        mockMvc.perform(get("/api/doctors")).andExpect(status().isOk()).andExpect(jsonPath("$").isArray()).andExpect(MockMvcResultMatchers.jsonPath("$[*]", hasSize(2)));
    }

    @Test
    void shouldGetNoDoctor() throws Exception {
        List<Doctor> doctorList = new ArrayList<>();
        when(doctorRepository.findAll()).thenReturn(doctorList);
        mockMvc.perform(get("/api/doctors")).andExpect(status().isNoContent());
    }

    @Test
    void shouldGetDoctorById() throws Exception {
        when(doctorRepository.findById(1L)).thenReturn(Optional.of(doctor));
        mockMvc.perform(get("/api/doctors/1")).andExpect(status().isOk());
    }

    @Test
    void shouldNotGetDoctorById() throws Exception {
        when(doctorRepository.findById(5L)).thenReturn(Optional.empty());
        mockMvc.perform(get("/api/doctors/5")).andExpect(status().isNotFound());
    }

    @Test
    void shouldCreateADoctor() throws Exception {
        when(doctorRepository.save(doctor)).thenReturn(doctor);

        mockMvc.perform(post("/api/doctor").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(doctor))).andExpect(status().isCreated());
    }

    @Test
    void shouldDeleteADoctor() throws Exception {
        when(doctorRepository.findById(1L)).thenReturn(Optional.of(doctor));
        mockMvc.perform(delete("/api/doctors/1")).andExpect(status().isOk());
    }

    @Test
    void shouldNotDeleteADoctor() throws Exception {
        when(doctorRepository.findById(1L)).thenReturn(Optional.empty());
        mockMvc.perform(delete("/api/doctors/1")).andExpect(status().isNotFound());
    }

    @Test
    void shouldDeleteAllDoctors() throws Exception {
        mockMvc.perform(delete("/api/doctors")).andExpect(status().isOk());
    }
}

@WebMvcTest(PatientController.class)
class PatientControllerUnitTest {

    @MockBean
    private PatientRepository patientRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    Patient patient;
    Patient patient2;

    @BeforeEach
    void setUp() {
        patient = new Patient("Jose Luis", "Olaya", 37, "j.olaya@email.com");
        patient2 = new Patient("Paulino", "Antunez", 37, "p.antunez@email.com");
        patient.setId(1);
    }

    @Test
    void shouldGetTwoPatients() throws Exception {
        List<Patient> patientList = new ArrayList<>();
        patientList.add(patient);
        patientList.add(patient2);

        when(patientRepository.findAll()).thenReturn(patientList);
        mockMvc.perform(get("/api/patients")).andExpect(status().isOk()).andExpect(jsonPath("$").isArray()).andExpect(MockMvcResultMatchers.jsonPath("$[*]", hasSize(2)));
    }

    @Test
    void shouldGetNoPatient() throws Exception {
        List<Patient> patientList = new ArrayList<>();
        when(patientRepository.findAll()).thenReturn(patientList);
        mockMvc.perform(get("/api/patients")).andExpect(status().isNoContent());
    }

    @Test
    void shouldGetPatientById() throws Exception {
        when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));
        mockMvc.perform(get("/api/patients/1")).andExpect(status().isOk());
    }

    @Test
    void shouldNotGetPatientById() throws Exception {
        when(patientRepository.findById(5L)).thenReturn(Optional.empty());
        mockMvc.perform(get("/api/patients/5")).andExpect(status().isNotFound());
    }

    @Test
    void shouldCreateAPatient() throws Exception {
        when(patientRepository.save(patient)).thenReturn(patient);

        mockMvc.perform(post("/api/patient").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(patient))).andExpect(status().isCreated());
    }

    @Test
    void shouldDeleteAPatient() throws Exception {
        when(patientRepository.findById(1L))
                .thenReturn(Optional.of(patient));
        mockMvc.perform(delete("/api/patients/1"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldNotDeleteARoom() throws Exception {
        when(patientRepository.findById(1L)).thenReturn(Optional.empty());
        mockMvc.perform(delete("/api/patients/1")).andExpect(status().isNotFound());
    }

    @Test
    void shouldDeleteAllRooms() throws Exception {
        mockMvc.perform(delete("/api/patients")).andExpect(status().isOk());
    }
}

@WebMvcTest(RoomController.class)
class RoomControllerUnitTest {

    @MockBean
    private RoomRepository roomRepository;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    Room room;
    Room room2;

    @BeforeEach
    void setUp() {
        room = new Room("Dermatology");
        room2 = new Room("Urology");
    }

    @Test
    void shouldGetTwoRooms() throws Exception {
        List<Room> roomList = new ArrayList<>();
        roomList.add(room);
        roomList.add(room2);

        when(roomRepository.findAll()).thenReturn(roomList);
        mockMvc.perform(get("/api/rooms")).andExpect(status().isOk()).andExpect(jsonPath("$").isArray()).andExpect(MockMvcResultMatchers.jsonPath("$[*]", hasSize(2)));
    }

    @Test
    void shouldGetNoRooms() throws Exception {
        List<Room> roomList = new ArrayList<>();
        when(roomRepository.findAll()).thenReturn(roomList);
        mockMvc.perform(get("/api/rooms")).andExpect(status().isNoContent());
    }

    @Test
    void shouldGetRoomByRoomName() throws Exception {
        when(roomRepository.findByRoomName("Dermatology")).thenReturn(Optional.of(room));
        mockMvc.perform(get("/api/rooms/Dermatology")).andExpect(status().isOk());
    }

    @Test
    void shouldNotGetRoomByRoomName() throws Exception {
        when(roomRepository.findByRoomName("Dermatology")).thenReturn(Optional.empty());
        mockMvc.perform(get("/api/rooms/Dermatology")).andExpect(status().isNotFound());
    }

    @Test
    void shouldCreateARoom() throws Exception {
        when(roomRepository.save(room)).thenReturn(room);

        mockMvc.perform(post("/api/room").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(room))).andExpect(status().isCreated());
    }

    @Test
    void shouldDeleteARoom() throws Exception {
        when(roomRepository.findByRoomName("Dermatology")).thenReturn(Optional.of(room));
        mockMvc.perform(delete("/api/rooms/Dermatology")).andExpect(status().isOk());
    }

    @Test
    void shouldNotDeleteARoom() throws Exception {
        when(roomRepository.findByRoomName("Dermatology")).thenReturn(Optional.empty());
        mockMvc.perform(delete("/api/rooms/Dermatology")).andExpect(status().isNotFound());
    }

    @Test
    void shouldDeleteAllRooms() throws Exception {
        mockMvc.perform(delete("/api/rooms")).andExpect(status().isOk());
    }
}
