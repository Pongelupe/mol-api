package br.com.mol.molapi.dtos.prescription;

import java.util.Date;
import java.util.Set;

import br.com.mol.molapi.entity.enums.PrescriptionType;
import lombok.Data;

@Data
public class PrescriptionDTO {

	private String id;
	private String observation;
	private Date shelfLife;
	private String doctorId;
	private Set<PrescriptionItemDTO> prescriptonItems;
	private PrescriptionType prescriptionType;
}
