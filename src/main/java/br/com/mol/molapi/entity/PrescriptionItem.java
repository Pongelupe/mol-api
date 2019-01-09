package br.com.mol.molapi.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class PrescriptionItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Double quantity;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(updatable = false, nullable = false)
	private Medicine medicine; 
}
