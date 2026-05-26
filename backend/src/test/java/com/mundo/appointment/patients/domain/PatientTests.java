package com.mundo.appointment.patients.domain;

import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PatientTests {

	private final Clock fixedClock = Clock.fixed(Instant.parse("2026-05-23T10:00:00Z"), ZoneOffset.UTC);

	@Test
	void requiresDischargeReasonWhenPatientStatusIsBaja() {
		assertThatThrownBy(() -> Patient.register(
				"Sofia",
				"Lopez",
				LocalDate.of(2018, 4, 12),
				"Maria Lopez",
				"5551234567",
				"maria@example.com",
				PatientStatus.BAJA,
				"Redes",
				"",
				LocalDate.of(2026, 5, 24),
				fixedClock))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage("Discharge reason is required when patient status is BAJA");
	}
}
