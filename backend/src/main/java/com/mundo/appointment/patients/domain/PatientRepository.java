package com.mundo.appointment.patients.domain;

import java.util.Optional;

public interface PatientRepository {

	Patient save(Patient patient);

	Optional<Patient> findById(PatientId id);
}
