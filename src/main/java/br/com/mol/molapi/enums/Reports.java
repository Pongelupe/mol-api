package br.com.mol.molapi.enums;

import java.util.Arrays;
import java.util.List;

public enum Reports {
	DEFAULT("/reports/default_prescription.jasper", Arrays.asList("patient", "patient.name", "doctor.email",
			"doctor.address", "doctor.phone", "doctor.crm", "medicines"));

	private final String path;
	private final List<String> requiredFields;

	private Reports(String path, List<String> requiredFields) {
		this.path = path;
		this.requiredFields = requiredFields;
	}

	public String getPath() {
		return path;
	}

	public List<String> getRequiredFields() {
		return requiredFields;
	}

}
