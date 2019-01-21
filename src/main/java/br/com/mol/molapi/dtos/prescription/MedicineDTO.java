package br.com.mol.molapi.dtos.prescription;

import java.util.Date;
import java.util.Set;

import br.com.mol.molapi.entity.ActivePrincible;
import br.com.mol.molapi.entity.AdministrationForm;
import br.com.mol.molapi.entity.PharmaceuticalForm;
import br.com.mol.molapi.entity.RegulatoryCategory;
import br.com.mol.molapi.entity.TherapeuticalClass;
import br.com.mol.molapi.entity.enums.IntendedFor;
import br.com.mol.molapi.entity.enums.MeasureType;
import br.com.mol.molapi.entity.enums.PrescriptionRestriction;
import br.com.mol.molapi.entity.enums.RecordStatus;
import br.com.mol.molapi.entity.enums.Tarja;
import lombok.Data;

@Data
public class MedicineDTO {

	private String id;
	private String companyName;
	private Set<ActivePrincible> activePrincible;
	private Long recordNumber;
	private TherapeuticalClass therapeuticalClass; 
	private Date recordPublication;
	private PrescriptionRestriction prescriptionRestriction;
	private Boolean monodrug;
	private RecordStatus recordStatus;
	private Boolean fractionable;
	private String comercialName;
	private Set<PharmaceuticalForm> pharmaceuticalForm;
	private RegulatoryCategory regulatoryCategory;
	private Tarja tarja;
	private IntendedFor intendedFor;
	private MeasureType measureType;	
	private String preservation;
	private Integer expiration;
	private Set<AdministrationForm> administrationForm;
}
