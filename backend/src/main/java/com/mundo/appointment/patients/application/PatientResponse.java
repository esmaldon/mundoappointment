package com.mundo.appointment.patients.application;

import com.mundo.appointment.patients.domain.Patient;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

public record PatientResponse(
		UUID id,
		String firstName,
		String lastName,
		LocalDate birthDate,
		String guardianName,
		String phoneNumber,
		String email,
		Instant createdAt) {

	public static PatientResponse from(Patient patient) {
		return new PatientResponse(
				patient.id().value(),
				patient.firstName(),
				patient.lastName(),
				patient.birthDate(),
				patient.guardianName(),
				patient.phoneNumber(),
				patient.email(),
				patient.createdAt());
	}
}
