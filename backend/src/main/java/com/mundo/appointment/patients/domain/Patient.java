package com.mundo.appointment.patients.domain;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;

public record Patient(
		PatientId id,
		String firstName,
		String lastName,
		LocalDate birthDate,
		String guardianName,
		String phoneNumber,
		String email,
		Instant createdAt) {

	public Patient {
		requireText(firstName, "First name is required");
		requireText(lastName, "Last name is required");
		requireText(guardianName, "Guardian name is required");
		requireText(phoneNumber, "Phone number is required");
		if (id == null) {
			throw new IllegalArgumentException("Patient id is required");
		}
		if (birthDate == null) {
			throw new IllegalArgumentException("Birth date is required");
		}
		if (createdAt == null) {
			throw new IllegalArgumentException("Created date is required");
		}
	}

	public static Patient register(
			String firstName,
			String lastName,
			LocalDate birthDate,
			String guardianName,
			String phoneNumber,
			String email,
			Clock clock) {

		return new Patient(
				PatientId.newId(),
				firstName,
				lastName,
				birthDate,
				guardianName,
				phoneNumber,
				email,
				Instant.now(clock));
	}

	private static void requireText(String value, String message) {
		if (value == null || value.isBlank()) {
			throw new IllegalArgumentException(message);
		}
	}
}
