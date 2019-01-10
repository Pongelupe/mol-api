package br.com.mol.molapi.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Entity
@Data
public class Contraindication {
	
	@Id
	@Column(length = 36)
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	
	@Column(length = 200)
	private String description;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(updatable = false, nullable = false)
	private Patient patient;
	
	@ManyToMany(fetch = FetchType.LAZY)
	private Set<Medicine> medicines;
}
