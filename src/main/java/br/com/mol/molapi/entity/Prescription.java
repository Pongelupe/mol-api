package br.com.mol.molapi.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity
@Data
public class Prescription {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 200)
	private String observation;
	
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date createdAt;
	
	@Temporal(value = TemporalType.DATE)
	private Date shelfLife;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(updatable = false, nullable = false)
	private Doctor doctor;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH, orphanRemoval = true)
	private List<PrescriptionItem> prescriptonItems;
}
