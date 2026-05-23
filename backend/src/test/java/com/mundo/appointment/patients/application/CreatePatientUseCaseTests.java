package com.mundo.appointment.patients.application;

import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;

import static org.assertj.core.api.Assertions.assertThat;

class CreatePatientUseCaseTests {

	@Test
	void createsPatient() {
		Clock fixedClock = Clock.fixed(Instant.parse("2026-05-23T10:00:00Z"), ZoneOffset.UTC);
		InMemoryPatientRepository patients = new InMemoryPatientRepository();
		CreatePatientUseCase useCase = new CreatePatientUseCase(patients, fixedClock);

		PatientResponse patient = useCase.create(new CreatePatientCommand(
				"Sofia",
				"Lopez",
				LocalDate.of(2018, 4, 12),
				"Maria Lopez",
				"5551234567",
				"maria@example.com"));

		assertThat(patient.id()).isNotNull();
		assertThat(patient.firstName()).isEqualTo("Sofia");
		assertThat(patient.createdAt()).isEqualTo(Instant.parse("2026-05-23T10:00:00Z"));
	}
}
