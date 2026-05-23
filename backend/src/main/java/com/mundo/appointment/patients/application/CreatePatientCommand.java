package com.mundo.appointment.patients.application;

import java.time.LocalDate;

public record CreatePatientCommand(
		String firstName,
		String lastName,
		LocalDate birthDate,
		String guardianName,
		String phoneNumber,
		String email) {
}
