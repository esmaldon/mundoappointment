package com.mundo.appointment.patients.application;

import com.mundo.appointment.patients.domain.PatientId;
import com.mundo.appointment.patients.domain.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class GetPatientUseCase {

	private final PatientRepository patients;

	public GetPatientUseCase(PatientRepository patients) {
		this.patients = patients;
	}

	public Optional<PatientResponse> findById(UUID id) {
		return patients.findById(new PatientId(id)).map(PatientResponse::from);
	}
}
