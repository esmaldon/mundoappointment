package com.mundo.appointment.patients;

import com.mundo.appointment.AppApplication;
import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;

class ModulithArchitectureTests {

	@Test
	void verifiesModularStructure() {
		ApplicationModules.of(AppApplication.class).verify();
	}
}
