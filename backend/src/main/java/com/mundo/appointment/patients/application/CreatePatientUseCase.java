package com.mundo.appointment.patients.application;

import com.mundo.appointment.patients.domain.Patient;
import com.mundo.appointment.patients.domain.PatientRepository;
import org.springframework.stereotype.Service;

import java.time.Clock;

@Service
public class CreatePatientUseCase {

	private final PatientRepository patients;
	private final Clock clock;

	public CreatePatientUseCase(PatientRepository patients, Clock clock) {
		this.patients = patients;
		this.clock = clock;
	}

	public PatientResponse create(CreatePatientCommand command) {
		Patient patient = Patient.register(
				command.firstName(),
				command.lastName(),
				command.birthDate(),
				command.guardianName(),
				command.phoneNumber(),
				command.email(),
				clock);

		return PatientResponse.from(patients.save(patient));
	}
}
