package br.com.mol.molapi.dtos.prescription;

import java.util.Date;
import java.util.Set;

import br.com.mol.molapi.dtos.user.UserDTO;
import lombok.Data;

@Data
public class PrescriptionDTO {

	private String id;

	private String observation;

	private Date createdAt;

	private Date shelfLife;

	private UserDTO doctor;
	
	private Set<PrescriptionItemDTO> prescriptonItems;
}
