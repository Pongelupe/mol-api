package br.com.mol.molapi.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import br.com.mol.molapi.entity.enums.IntendedFor;
import br.com.mol.molapi.entity.enums.PrescriptionRestriction;
import br.com.mol.molapi.entity.enums.RecordStatus;
import br.com.mol.molapi.entity.enums.Tarja;
import lombok.Data;

@Entity
@Data
public class Medicine {

	@Id
	@Column(length = 36)
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	
	@Column(length = 80)
	private String companyName;
	
	@ManyToMany(fetch = FetchType.EAGER)
	private Set<ActivePrincible> activePrincible;
	
	private Long recordNumber;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private TherapeuticalClass therapeuticalClass; 
	
	@Temporal(value = TemporalType.DATE)
	private Date recordPublication;
	
	@Enumerated(EnumType.STRING)
	private PrescriptionRestriction prescriptionRestriction;
	
	private Boolean monodrug;
	
	@Enumerated(EnumType.STRING)
	private RecordStatus recordStatus;
	
	private Boolean fractionable;
	
	private String comercialName;
	
	@ManyToMany(fetch = FetchType.EAGER)
	private Set<PharmaceuticalForm> pharmaceuticalForm;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private RegulatoryCategory regulatoryCategory;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private ATC atc;
	
	@Enumerated(EnumType.STRING)
	private Tarja tarja;
	
	private IntendedFor intendedFor;
	
	private String preservation;
	
	private Integer expiration;
	
	private String presentationDiffComplement;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH, orphanRemoval = true)
	private Set<AdministrationForm> administrationForm;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = true)
	private Medicine medicineOfReference;
	
	private String publicReport;
}
