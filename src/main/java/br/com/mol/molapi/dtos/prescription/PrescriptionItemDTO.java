package br.com.mol.molapi.dtos.prescription;

import br.com.mol.molapi.entity.Medicine;
import lombok.Data;

@Data
public class PrescriptionItemDTO {

	private String id;

	private Double quantity;

	private Medicine medicine;

	private String description;
}
