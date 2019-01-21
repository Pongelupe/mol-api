package br.com.mol.molapi.dtos.prescription;

import lombok.Data;

@Data
public class PrescriptionItemDTO {

	private String id;
	private Double quantity;
	private MedicineDTO medicine;
	private String medicineId;
	private String description;
}
