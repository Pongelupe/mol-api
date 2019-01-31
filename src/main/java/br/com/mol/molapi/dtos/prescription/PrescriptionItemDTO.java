package br.com.mol.molapi.dtos.prescription;

import br.com.mol.molapi.entity.enums.MedicineFrequencyPerDay;
import lombok.Data;

@Data
public class PrescriptionItemDTO {

	private String id;
	private Double quantity;
	private MedicineDTO medicine;
	private String medicineId;
	private String description;
	private MedicineFrequencyPerDay medicineFrequencyPerDay;
	private int daysToTake;
}
