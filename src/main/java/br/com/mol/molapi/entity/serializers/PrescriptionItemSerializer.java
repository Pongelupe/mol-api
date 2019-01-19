package br.com.mol.molapi.entity.serializers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import br.com.mol.molapi.entity.PrescriptionItem;

public class PrescriptionItemSerializer extends JsonSerializer<PrescriptionItem>{

	@Override
	public void serialize(PrescriptionItem prescriptionItem, JsonGenerator gen, SerializerProvider serializers)
			throws IOException {
		gen.writeStartObject();
		gen.writeStringField("id", prescriptionItem.getId());
		gen.writeNumberField("quantity", prescriptionItem.getQuantity());
		gen.writeStringField("medicineId", prescriptionItem.getMedicine().getId());
		gen.writeStringField("description", prescriptionItem.getDescription());
		gen.writeEndObject();
	}
}
