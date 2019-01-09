package br.com.mol.molapi.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.mol.molapi.entity.enums.IntendedFor;
import br.com.mol.molapi.entity.enums.PrescriptionRestriction;
import br.com.mol.molapi.entity.enums.RecordStatus;
import br.com.mol.molapi.entity.enums.Tarja;
import lombok.Data;

@Entity
@Data
public class Medicine {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 80)
	private String companyName;
	
	//ActivePrincible oneToMany
	
	private Long recordNumber;
	
	//TherapeuticalClass oneToOne
	
	@Temporal(value = TemporalType.DATE)
	private Date recordPublication;
	
	@Enumerated(EnumType.STRING)
	private PrescriptionRestriction prescriptionRestriction;
	
	private Boolean monodrug;
	
	@Enumerated(EnumType.STRING)
	private RecordStatus recordStatus;
	
	private Boolean fractionable;
	
	private String comercialName;
	
	//PharmaceuticalForm oneToMany
	
	//RegulatoryCategory oneToOne
	
	//ATC oneToOne
	
	@Enumerated(EnumType.STRING)
	private Tarja tarja;
	
	private IntendedFor intendedFor;
	
	private String preservation;
	
	private Integer expiration;
	
	private String presentationDiffComplement;
	
	//AdministrationForm oneToMany
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = true)
	private Medicine medicineOfReference;
	
	private String publicReport;
}
