package br.com.mol.molapi.dtos.prescription;

import java.util.Date;
import java.util.Set;

import br.com.mol.molapi.dtos.user.DoctorDTO;
import br.com.mol.molapi.entity.enums.PrescriptionType;
import lombok.Data;

@Data
public class PrescriptionDetailedDTO {
	private String id;
	private String observation;
	private Date shelfLife;
	private Date createdAt;
	private DoctorDTO doctor;
	private String prescriptionBase64;
	private Set<PrescriptionItemDTO> prescriptionItems;
	private PrescriptionType prescriptionType;

}
