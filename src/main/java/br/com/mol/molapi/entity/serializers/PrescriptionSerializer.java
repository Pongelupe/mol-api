package br.com.mol.molapi.entity.serializers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import br.com.mol.molapi.entity.Prescription;
import br.com.mol.molapi.entity.PrescriptionItem;

public class PrescriptionSerializer extends JsonSerializer<Prescription> {

	@Override
	public void serialize(Prescription prescription, JsonGenerator gen, SerializerProvider serializers)
			throws IOException {
		gen.writeStartObject();

		gen.writeStringField("id", prescription.getId());

		gen.writeStringField("observation", prescription.getObservation());
		gen.writeStringField("createdAt", String.valueOf(prescription.getCreatedAt().getTime()));

		if (prescription.getShelfLife() != null)
			gen.writeStringField("shelfLife", prescription.getShelfLife().toString());

		gen.writeStringField("doctorId", prescription.getDoctor().getId());
		gen.writeStringField("patientId", prescription.getPatientId());

		gen.writeArrayFieldStart("prescriptionItems");

		if (prescription.getPrescriptionItems() != null)
			for (PrescriptionItem item : prescription.getPrescriptionItems())
				gen.writeObject(item);

		gen.writeEndArray();

		gen.writeStringField("prescriptionType", prescription.getPrescriptionType().name());
	}

}
