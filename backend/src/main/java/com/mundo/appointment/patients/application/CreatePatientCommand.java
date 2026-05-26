package com.mundo.appointment.patients.application;

import com.mundo.appointment.patients.domain.PatientStatus;

import java.time.LocalDate;

public record CreatePatientCommand(
		String firstName,
		String lastName,
		LocalDate birthDate,
		String guardianName,
		String phoneNumber,
		String email,
		PatientStatus status,
		String referredBy,
		String dischargeReason,
		LocalDate admissionDate) {
}
