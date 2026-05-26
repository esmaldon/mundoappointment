package com.mundo.appointment.patients.infrastructure.persistence;

import com.mundo.appointment.TestcontainersConfiguration;
import com.mundo.appointment.patients.domain.Patient;
import com.mundo.appointment.patients.domain.PatientId;
import com.mundo.appointment.patients.domain.PatientRepository;
import com.mundo.appointment.patients.domain.PatientStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
@Testcontainers(disabledWithoutDocker = true)
class JdbcPatientRepositoryIntegrationTests {

	@Autowired
	private PatientRepository patients;

	@Test
	void savesAndFindsPatient() {
		Patient patient = new Patient(
				new PatientId(UUID.randomUUID()),
				"Sofia",
				"Lopez",
				LocalDate.of(2018, 4, 12),
				"Maria Lopez",
				"5551234567",
				"maria@example.com",
				PatientStatus.ACTIVO,
				"Redes",
				null,
				LocalDate.of(2026, 5, 24),
				Instant.parse("2026-05-24T18:00:00Z"));

		patients.save(patient);

		assertThat(patients.findById(patient.id()))
				.hasValueSatisfying(found -> {
					assertThat(found.id()).isEqualTo(patient.id());
					assertThat(found.firstName()).isEqualTo("Sofia");
					assertThat(found.lastName()).isEqualTo("Lopez");
					assertThat(found.birthDate()).isEqualTo(LocalDate.of(2018, 4, 12));
					assertThat(found.guardianName()).isEqualTo("Maria Lopez");
					assertThat(found.phoneNumber()).isEqualTo("5551234567");
					assertThat(found.email()).isEqualTo("maria@example.com");
					assertThat(found.status()).isEqualTo(PatientStatus.ACTIVO);
					assertThat(found.referredBy()).isEqualTo("Redes");
					assertThat(found.dischargeReason()).isNull();
					assertThat(found.admissionDate()).isEqualTo(LocalDate.of(2026, 5, 24));
					assertThat(found.createdAt()).isEqualTo(Instant.parse("2026-05-24T18:00:00Z"));
				});
	}
}
