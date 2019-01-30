package br.com.mol.molapi.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.mol.molapi.entity.enums.PrescriptionType;
import lombok.Data;

@Entity
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Prescription {

	@Id
	@Column(length = 36)
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	@Column(length = 200)
	private String observation;

	@Temporal(value = TemporalType.TIMESTAMP)
	private Date createdAt;

	@Temporal(value = TemporalType.DATE)
	private Date shelfLife;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "doctor_id", updatable = false, nullable = false)
	private Doctor doctor;

	@Column(name = "doctor_id", insertable = false, updatable = false)
	private String doctorId;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "patient_id", updatable = false, insertable = true, nullable = false)
	private Patient patient;

	@Column(name = "patient_id", insertable = false, updatable = false)
	private String patientId;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH, orphanRemoval = true)
	private Set<PrescriptionItem> prescriptionItems;

	private PrescriptionType prescriptionType;
	
	@Column
	@Lob
	private byte[] prescriptionFile;

	@PrePersist
	protected void onCreate() {
		createdAt = new Date();
	}
}
