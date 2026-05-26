package com.mundo.appointment.patients.infrastructure.web;

import com.mundo.appointment.patients.application.CreatePatientCommand;
import com.mundo.appointment.patients.domain.PatientStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;

public record CreatePatientRequest(
		@NotBlank String firstName,
		@NotBlank String lastName,
		@NotNull @Past LocalDate birthDate,
		@NotBlank String guardianName,
		@NotBlank String phoneNumber,
		@NotNull PatientStatus status,
		@NotBlank String referredBy,
		String dischargeReason,
		@NotNull LocalDate admissionDate,
		@Email String email) {

	CreatePatientCommand toCommand() {
		return new CreatePatientCommand(firstName,
				lastName,
				birthDate,
				guardianName,
				phoneNumber,
				email,
				status,
				referredBy,
				dischargeReason,
				admissionDate
				);
	}
}
