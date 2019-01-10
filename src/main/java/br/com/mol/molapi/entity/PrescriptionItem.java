package br.com.mol.molapi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	private Double quantity;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(updatable = false, nullable = false)
	private Medicine medicine;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(updatable = false, nullable = false)
	private Prescription prescription;
}
