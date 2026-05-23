package com.mundo.appointment.patients.application;

import com.mundo.appointment.patients.domain.Patient;
import com.mundo.appointment.patients.domain.PatientId;
import com.mundo.appointment.patients.domain.PatientRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

class InMemoryPatientRepository implements PatientRepository {

	private final Map<PatientId, Patient> patients = new HashMap<>();

	@Override
	public Patient save(Patient patient) {
		patients.put(patient.id(), patient);
		return patient;
	}

	@Override
	public Optional<Patient> findById(PatientId id) {
		return Optional.ofNullable(patients.get(id));
	}
}
