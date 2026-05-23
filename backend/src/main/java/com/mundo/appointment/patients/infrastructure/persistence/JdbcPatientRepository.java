package com.mundo.appointment.patients.infrastructure.persistence;

import com.mundo.appointment.patients.domain.Patient;
import com.mundo.appointment.patients.domain.PatientId;
import com.mundo.appointment.patients.domain.PatientRepository;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Repository
class JdbcPatientRepository implements PatientRepository {

	private final JdbcClient jdbcClient;

	JdbcPatientRepository(JdbcClient jdbcClient) {
		this.jdbcClient = jdbcClient;
	}

	@Override
	public Patient save(Patient patient) {
		jdbcClient.sql("""
				insert into patients (
					id,
					first_name,
					last_name,
					birth_date,
					guardian_name,
					phone_number,
					email,
					created_at
				)
				values (
					:id,
					:firstName,
					:lastName,
					:birthDate,
					:guardianName,
					:phoneNumber,
					:email,
					:createdAt
				)
				""")
				.param("id", patient.id().value())
				.param("firstName", patient.firstName())
				.param("lastName", patient.lastName())
				.param("birthDate", patient.birthDate())
				.param("guardianName", patient.guardianName())
				.param("phoneNumber", patient.phoneNumber())
				.param("email", patient.email())
				.param("createdAt", patient.createdAt())
				.update();

		return patient;
	}

	@Override
	public Optional<Patient> findById(PatientId id) {
		return jdbcClient.sql("""
				select id, first_name, last_name, birth_date, guardian_name, phone_number, email, created_at
				from patients
				where id = :id
				""")
				.param("id", id.value())
				.query(this::mapPatient)
				.optional();
	}

	private Patient mapPatient(ResultSet rs, int rowNum) throws SQLException {
		return new Patient(
				new PatientId(rs.getObject("id", java.util.UUID.class)),
				rs.getString("first_name"),
				rs.getString("last_name"),
				rs.getObject("birth_date", java.time.LocalDate.class),
				rs.getString("guardian_name"),
				rs.getString("phone_number"),
				rs.getString("email"),
				rs.getTimestamp("created_at").toInstant());
	}
}
