package br.com.mol.molapi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Entity
@Data
public class PrescriptionItem {

	@Id
	@Column(length = 36)
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	private Double quantity;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "medicine_id", updatable = false, nullable = true)
	private Medicine medicine;

	@Column(name = "medicine_id", updatable = false, insertable = false)
	private String medicineId;

	@Column(length = 200)
	private String description;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(updatable = false, nullable = true)
	private Prescription prescription;
}
