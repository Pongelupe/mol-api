package br.com.mol.molapi.dtos.prescription;

import java.util.Date;

import br.com.mol.molapi.entity.enums.PrescriptionType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PrescriptionPartialDTO {

	private String id;
	private String observation;
	private Date shelfLife;
	private Date createdAt;
	private String doctorId;
	private String patientId;
	private PrescriptionType prescriptionType;

}
