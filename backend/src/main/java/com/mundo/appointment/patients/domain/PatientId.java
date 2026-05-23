package com.mundo.appointment.patients.domain;

import java.util.UUID;

public record PatientId(UUID value) {

	public PatientId {
		if (value == null) {
			throw new IllegalArgumentException("Patient id is required");
		}
	}

	public static PatientId newId() {
		return new PatientId(UUID.randomUUID());
	}
}
