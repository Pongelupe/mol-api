package br.com.mol.molapi.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class Contraindication {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 200)
	private String description;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(updatable = false, nullable = false)
	private Patient patient;
	
	@ManyToMany(fetch = FetchType.LAZY)
	private List<Medicine> medicines;
}
