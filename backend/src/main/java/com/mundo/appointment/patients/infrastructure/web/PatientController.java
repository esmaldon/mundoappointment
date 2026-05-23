package com.mundo.appointment.patients.infrastructure.web;

import com.mundo.appointment.patients.application.CreatePatientUseCase;
import com.mundo.appointment.patients.application.GetPatientUseCase;
import com.mundo.appointment.patients.application.PatientResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/api/patients")
class PatientController {

	private final CreatePatientUseCase createPatient;
	private final GetPatientUseCase getPatient;

	PatientController(CreatePatientUseCase createPatient, GetPatientUseCase getPatient) {
		this.createPatient = createPatient;
		this.getPatient = getPatient;
	}

	@PostMapping
	ResponseEntity<PatientResponse> create(@Valid @RequestBody CreatePatientRequest request) {
		PatientResponse patient = createPatient.create(request.toCommand());
		return ResponseEntity.created(URI.create("/api/patients/" + patient.id())).body(patient);
	}

	@GetMapping("/{id}")
	ResponseEntity<PatientResponse> findById(@PathVariable UUID id) {
		return getPatient.findById(id)
				.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}
}
